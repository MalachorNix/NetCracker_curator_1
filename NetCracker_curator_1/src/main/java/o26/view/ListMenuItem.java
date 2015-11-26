package o26.view;

import java.util.ArrayList;

import o26.controller.Journal;

public class ListMenuItem extends MenuItem {
    private final String ITEM = "Просмотр списка задач";
    
    @Override
    public void show(Journal journal) {
        ArrayList tasks = (ArrayList) journal.getTasks();
        int countTasks = tasks.size();
        if(countTasks!=0){
            for(int i = 0; i < countTasks; i++){
                int lengthId = i+"".length();
                String breakingLine = "";
                int lengthLine = 40 - lengthId - 2;
                for(int j = 0; j < lengthLine; j++) {
                    breakingLine+="~";
                }
                String task = tasks.get(i).toString();
                System.out.println("<"+(i+1)+">"+breakingLine);
                System.out.println(task);
                System.out.println("<"+(i+1)+">"+breakingLine);
            }
            System.out.println();
        }
        else System.out.println("Список задач пуст!");
    }
    
    @Override
    public String toString(){
        return this.ITEM;
    }
}