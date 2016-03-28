package o26.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DeleteMenuItem extends AbstractMenuItem {
    private static final String ITEM = "Удаление задач";
    
    @Override
    public void show(ObjectInputStream inputStream, ObjectOutputStream outputStream) {
        try {
            int selectedId;
            do{
                System.out.println("Удаление задачи");
                System.out.println("\t<Id> Удалить задачу с индексом Id");
                System.out.println("\t<0> Вернуться в меню");

                outputStream.writeInt(9);
                outputStream.flush();

                int countTasks = inputStream.readInt();

                selectedId = choice(0, countTasks);

                outputStream.writeInt(selectedId);
                outputStream.flush();

                if (selectedId != 0) {
                    System.out.println(inputStream.readUTF());
                    System.out.println("Вы уверены, что хотите удалить эту задачу?");
                    System.out.println("\t<1> Да");
                    System.out.println("\t<0> Нет");

                    int choice = choice(0, 1);

                    outputStream.writeInt(choice);
                    outputStream.flush();

                    if(choice != 0){
                        System.out.println("Задача удалена!\n");
                    }
                }
            } while(selectedId != 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String toString(){
        return ITEM;
    }
}