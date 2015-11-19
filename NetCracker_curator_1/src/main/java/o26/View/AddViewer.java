package o26.View;

import java.util.HashMap;
import java.util.Map;
import o26.Controller.Journal;
import o26.Model.TaskParameters;

public class AddViewer implements Viewer{
    private Inner in;
    @Override
    public void show(Journal journal) {
        Map<TaskParameters, Object> parameters = new HashMap<>();
        in = new Inner();
        parameters.put(TaskParameters.NAME, in.doIt(TaskParameters.NAME));
        parameters.put(TaskParameters.DESCRIPTION, in.doIt(TaskParameters.DESCRIPTION));
        parameters.put(TaskParameters.CONTACTS, in.doIt(TaskParameters.CONTACTS));
        parameters.put(TaskParameters.DATE, in.doIt(TaskParameters.DATE));
        journal.addTask(parameters);
        System.out.println("Задача добавлена!\n");
    } 
}