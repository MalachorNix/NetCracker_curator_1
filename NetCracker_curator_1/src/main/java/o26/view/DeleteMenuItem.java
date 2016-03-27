package o26.view;

import o26.controller.Journal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DeleteMenuItem extends AbstractMenuItem {
    private static final String ITEM = "Удаление задач";
    
    @Override
    public void show(Journal journal, ObjectInputStream inputStream, ObjectOutputStream outputStream) {
        int selectedId;
        do{
            System.out.println("Удаление задачи");
            System.out.println("\t<Id> Удалить задачу с индексом Id");
            System.out.println("\t<0> Вернуться в меню");

            int countTasks = journal.getTasks().size();
            selectedId = choice(0, countTasks);

            if (selectedId != 0) {
                System.out.println(journal.getTasks().get(selectedId-1).toString());
                System.out.println("Вы уверены, что хотите удалить эту задачу?");
                System.out.println("\t<1> Да");
                System.out.println("\t<0> Нет");
                if(choice(0, 1) != 0){
                    journal.deleteTask(selectedId - 1);
                    System.out.println("Задача удалена!\n");
                    journal.journalChanged();
                }
            }
        } while(selectedId != 0);
    }
    
    @Override
    public String toString(){
        return ITEM;
    }
}