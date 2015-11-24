package o26.controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import o26.model.Task;
import o26.model.TaskParameter;
import o26.view.NotificationViewer;

public class Notification extends Thread{
    private Journal journal;
    private int actualTaskIndex;
    private Task actualTask;
    private long timeTask;
    
    public void setActual(Journal journal) {
        this.journal = journal;
        ArrayList tasks = (ArrayList) journal.getTasks();
        if(tasks!=null && !tasks.isEmpty()){
            int countTasks = tasks.size();
            int index = 0;
            long time = ((GregorianCalendar)((Task)tasks.get(index)).getValue(TaskParameter.DATE)).getTimeInMillis();
            for (int i = 0; i < countTasks; i++) {
                long temp = ((GregorianCalendar)((Task)tasks.get(i)).getValue(TaskParameter.DATE)).getTimeInMillis();
                index = time > temp ? i : index;
            }
            this.actualTaskIndex = index;
            this.actualTask =  (Task) tasks.get(index);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                timeTask = ((GregorianCalendar) actualTask.getValue(TaskParameter.DATE)).getTimeInMillis();
                if (System.currentTimeMillis() >= timeTask) {
                    new NotificationViewer().show(journal, actualTaskIndex);
                    setActual(journal);
                }
            } catch (Exception e) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}