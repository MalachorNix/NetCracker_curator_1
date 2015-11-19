package o26.View;

import java.util.ArrayList;

import o26.Controller.Journal;

public class ListViewer implements Viewer{
    private final String POINT = "Просмотр списка задач";
    
    @Override
    public void show(Journal journal) {
        ArrayList tasks = journal.getTasks();
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
        return this.POINT;
    }
}