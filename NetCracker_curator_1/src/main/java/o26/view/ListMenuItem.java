package o26.view;

import java.util.ArrayList;
import java.util.List;

import o26.controller.Journal;
import o26.model.ITask;

public class ListMenuItem extends AbstractMenuItem {
    private static final String ITEM = "Просмотр списка задач";
    
    @Override
    @SuppressWarnings("unchecked")
    public void show(Journal journal) {
        List<ITask> tasks = (ArrayList) journal.getTasks();
        int countTasks = tasks.size();

        if (countTasks == 0) {
            System.out.println("Список задач пуст!");
        } else {
            StringBuilder breakingLine = new StringBuilder("");
            StringBuilder border = new StringBuilder("~");
            for(int i = 0; i < countTasks; i++){
                int lengthId = i+"".length();
                int lengthLine = 40 - lengthId - 2;
                for(int j = 0; j < lengthLine; j++) {
                    breakingLine.append(border);
                }
                String task = tasks.get(i).toString();
                System.out.println("<"+(i+1)+">"+breakingLine);
                System.out.println(task);
                System.out.println("<"+(i+1)+">"+breakingLine);
            }
            System.out.println();
        }
    }
    
    @Override
    public String toString(){
        return ITEM;
    }
}