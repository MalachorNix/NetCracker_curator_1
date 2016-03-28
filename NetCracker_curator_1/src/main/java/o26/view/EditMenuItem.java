package o26.view;

import o26.model.Parameter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EditMenuItem extends AbstractMenuItem {
    private static final String ITEM = "Редактирование задач";

    @Override
    public void show(ObjectInputStream inputStream, ObjectOutputStream outputStream) {
        try {
            int selectedId;

            do {
                System.out.println("Редактирование задачи");
                System.out.println("\t<Id> Редактировать задачу с индексом Id");
                System.out.println("\t<0> Вернуться в меню");

                outputStream.writeInt(8);
                outputStream.flush();
                int countTasks = inputStream.readInt();

                selectedId = choice(0, countTasks);

                outputStream.writeInt(selectedId);
                outputStream.flush();

                if (selectedId != 0) {
                    System.out.println(inputStream.readUTF());
                    System.out.println("Данную задачу вы хотите редактировать?");
                    System.out.println("\t<1> Да");
                    System.out.println("\t<0> Нет");

                    int choice = choice(0, 1);

                    outputStream.writeInt(choice);
                    outputStream.flush();

                    if (choice != 0) {
                        List<Parameter> parameters = (ArrayList) inputStream.readObject();
                        List<Parameter> editParameters = editParameters(parameters);
                        outputStream.writeObject(editParameters);
                        outputStream.flush();
                        System.out.println("Задача изменена!\n");
                    }
                }
            } while (selectedId != 0);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return ITEM;
    }

    private List<Parameter> editParameters(List<Parameter> parameters) {
        int select;
        int count = parameters.size();
        do {
            System.out.println("Редактирование параметров");
            for (int i = 0; i < count; i++) {
                System.out.println("\t<" + (i + 1) + "> " + parameters.get(i).getType().toString());
            }
            System.out.println("\t<0> Сохранить изменения и вернуться к выбору задачи");
            select = choice(0, count);
            if (select == 0) {
                return parameters; //Костыли велосипеды
            }
            Parameter parameter = parameters.get(select - 1);
            parameter.setValue(parameter.getType(), inputParameter(parameter.getType()));
            if (select != 0) {
                System.out.println("Параметр изменен!\n");
            }
        } while (select != 0);
        return parameters;
    }
}