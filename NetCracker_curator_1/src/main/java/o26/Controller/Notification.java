package o26.Controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import o26.Model.Task;
import o26.Model.TaskParameters;
import o26.View.NotificationViewer;

public class Notification extends Thread{
    private Journal journal;
    private int actualTaskIndex;
    private Task actualTask;
    private long timeTask;
    
    public void setActual(Journal journal) {
        this.journal = journal;
        ArrayList tasks = journal.getTasks();
        if(tasks!=null && !tasks.isEmpty()){
            int countTasks = tasks.size();
            int index = 0;
            long time = ((GregorianCalendar)((Task)tasks.get(index)).getValue(TaskParameters.DATE)).getTimeInMillis();
            for (int i = 0; i < countTasks; i++) {
                long temp = ((GregorianCalendar)((Task)tasks.get(i)).getValue(TaskParameters.DATE)).getTimeInMillis();
                index = (time > temp) ? i : index;
            }
            this.actualTaskIndex = index;
            this.actualTask =  (Task) tasks.get(index);
        }
    }
    
    @Override
    public void run(){
        while(true){
            timeTask = ((GregorianCalendar)actualTask.getValue(TaskParameters.DATE)).getTimeInMillis();
            if(System.currentTimeMillis()>=timeTask){
                new NotificationViewer().show(journal, actualTaskIndex);
                setActual(journal);
            }
            
        }
    }
}
