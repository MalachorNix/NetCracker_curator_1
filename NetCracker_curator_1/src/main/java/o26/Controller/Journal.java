package o26.Controller;

import o26.Model.Task;
import o26.Model.TaskParameters;
import o26.View.Viewer;

import java.util.ArrayList;
import java.util.Map;
import o26.View.MenuViewer;

public class Journal {
    private ArrayList tasks;
    private DataLoader dataLoader;
    private TaskCreator taskCreator;
    private Viewer view;
    private Notification notification;
    
    public void addTask(Map <TaskParameters, Object> parameters) {
        taskCreator = new TaskCreator();
        if (taskCreator.validate(parameters)) {
            this.tasks.add(taskCreator.createTask(parameters));
        } else {
            System.out.println("Задачу создать нельзя!");
        }
    }

    public void deleteTask(int id) {
        this.tasks.remove(id);
    }

    public void editTask(int id, Map <TaskParameters, Object> parameters) {
        for(Map.Entry entry : parameters.entrySet()){
            TaskParameters parameter = (TaskParameters)entry.getKey();
            Object value = entry.getValue();
            ((Task) this.tasks.get(id)).setValue(parameter, value);
        }
    }

    public ArrayList getTasks() {
        return this.tasks;
    }

    public void save(){
        dataLoader = new DataLoader();
        if (this.tasks != null && !this.tasks.isEmpty()) {
            dataLoader.saveData(tasks);
        }
    }

    public void load(){
        dataLoader = new DataLoader();
        this.tasks = dataLoader.loadData(this.tasks);
    }
    
    public void showMenu(){
        view = new MenuViewer();
        this.view.show(this);
    }
    
    public void notificationStart(){
        notification = new Notification();
        journalChanged();
        notification.start();
    }
    
    public void journalChanged(){
        notification.setActual(this);
    }
}