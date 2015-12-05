package o26.controller;

import o26.model.ITask;
import o26.model.Parameter;
import o26.model.Task;
import o26.view.NotificationViewer;

import java.util.GregorianCalendar;
import java.util.List;

public class Notification implements Runnable, INotification {
    private Journal journal;
    private NotificationViewer notificationViewer;
    private int actualTaskIndex;
    private Task actualTask;

    @Override
    public void setNotificationViewer(NotificationViewer notificationViewer){
        this.notificationViewer = notificationViewer;
    }
    
    @Override
    public void setActual(Journal journal) {
        this.journal = journal;
        List<ITask> tasks = journal.getTasks();
        if(tasks!=null && !tasks.isEmpty()){
            int countTasks = tasks.size();
            int index = 0;
            long time = ((GregorianCalendar)(tasks.get(index)).getValue(Parameter.TypeParameter.DATE)).getTimeInMillis();
            for (int i = 0; i < countTasks; i++) {
                long temp = ((GregorianCalendar)(tasks.get(i)).getValue(Parameter.TypeParameter.DATE)).getTimeInMillis();
                index = time > temp ? i : index;
            }
            this.actualTaskIndex = index;
            this.actualTask =  (Task) tasks.get(index);
        }
    }

    @Override
    public void run() {
        long timeTask;
        while (true) {
            timeTask = ((GregorianCalendar) actualTask.getValue(Parameter.TypeParameter.DATE)).getTimeInMillis();
            if (System.currentTimeMillis() >= timeTask) {
                notificationViewer.show(journal, actualTaskIndex);
                setActual(journal);
            }
        }
    }

    @Override
    public void start() {
        new Thread(this).start();
    }
}