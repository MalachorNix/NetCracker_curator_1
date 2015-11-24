package o26.view;

import java.util.HashMap;
import java.util.Map;
import o26.controller.Journal;
import o26.model.TaskParameter;

public class AddMenuItem implements MenuItem {
    private Inner in;
    private final String ITEM = "Добавление задач";
    
    @Override
    public void show(Journal journal) {
        Map<TaskParameter, Object> parameters = new HashMap<>();
        in = new Inner();
        parameters.put(TaskParameter.NAME, in.doIt(TaskParameter.NAME));
        parameters.put(TaskParameter.DESCRIPTION, in.doIt(TaskParameter.DESCRIPTION));
        parameters.put(TaskParameter.CONTACTS, in.doIt(TaskParameter.CONTACTS));
        parameters.put(TaskParameter.DATE, in.doIt(TaskParameter.DATE));
        journal.addTask(parameters);
        System.out.println("Задача добавлена!\n");
        journal.journalChanged();
    }
    
    @Override
    public String toString(){
        return this.ITEM;
    }
}