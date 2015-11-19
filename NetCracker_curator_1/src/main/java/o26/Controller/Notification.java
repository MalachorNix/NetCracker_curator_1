package o26.Controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import o26.Model.Task;
import o26.Model.TaskParameters;
import o26.View.NotificationViewer;

public class Notification extends Thread{
    private Journal journal;
    private int actualTaskIndex;
    private int checkTaskIndex = -1;
    private long timeTask;
    Task actualTask;
    
    public Notification(Journal journal){
        this.journal = journal;
    }
    
    public int getActualTask(ArrayList tasks) {
        int index = 0;
        long time = ((GregorianCalendar)((Task)tasks.get(index)).getValue(TaskParameters.DATE)).getTimeInMillis();
        if(tasks.size()>1){
            for (int i = 1; i < tasks.size(); i++) {
                long temp = ((GregorianCalendar)((Task)tasks.get(i)).getValue(TaskParameters.DATE)).getTimeInMillis();
                if (time > temp){
                    time = temp;
                    index = i;
                }
            }
        }
        return index;
    }
    
    @Override
    public void run(){
        while(true){
            if(actualTaskIndex!=checkTaskIndex){
                checkTaskIndex = actualTaskIndex;
                timeTask = ((GregorianCalendar)actualTask.getValue(TaskParameters.DATE)).getTimeInMillis();
            }
            if(System.currentTimeMillis()>=timeTask){
                new NotificationViewer().show(journal, actualTaskIndex);
                journal.deleteTask(actualTaskIndex);
                setActualTaskIndex(journal);
            }
            
        }
    }
    
    public void setActualTaskIndex(Journal journal){
        actualTaskIndex = getActualTask(journal.getTasks());
        actualTask = (Task)journal.getTasks().get(actualTaskIndex);
        timeTask = ((GregorianCalendar)actualTask.getValue(TaskParameters.DATE)).getTimeInMillis();
    }
}
