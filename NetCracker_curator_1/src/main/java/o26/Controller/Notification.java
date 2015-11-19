package o26.Controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import o26.Model.Task;
import o26.Model.TaskParameters;
import o26.View.NotificationViewer;

public class Notification extends Thread{
    private Journal journal;
    private int actualTaskIndex;
    private int checkTaskIndex;
    private long timeTask;
    Task actualTask;
    public Notification(Journal journal){
        this.journal = journal;
    }
    public int getActualTask(ArrayList tasks) {
        int index = 0;
        long time = ((GregorianCalendar)((Task)tasks.get(0)).getValue(TaskParameters.DATE)).getTimeInMillis();

        for (int i = 1; i < tasks.size(); i++) {
            long temp = ((GregorianCalendar)((Task)tasks.get(i)).getValue(TaskParameters.DATE)).getTimeInMillis();
            if (time > temp){
                time = temp;
                index = i;
            }
        }
        return index;
    }
    
    @Override
    public void run(){
        if(actualTaskIndex!=checkTaskIndex){
            checkTaskIndex = actualTaskIndex;
            timeTask = ((GregorianCalendar)actualTask.getValue(TaskParameters.DATE)).getTimeInMillis();
        }
        if(System.currentTimeMillis()>=timeTask){
            new NotificationViewer().show(journal);
        }
    }
    public void setActualTaskIndex(Journal journal){
        actualTaskIndex = getActualTask(journal.getTasks());
        actualTask = (Task)journal.getTasks().get(actualTaskIndex);
    }
}
