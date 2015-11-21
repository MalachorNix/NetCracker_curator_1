package o26.controller;

import o26.model.Task;
import o26.model.TaskParameters;
import o26.view.Viewer;

import java.util.ArrayList;
import java.util.Map;
import o26.view.MenuViewer;

public class Journal {
    private ArrayList tasks;
    private DataLoader dataLoader;
    private TaskCreator taskCreator;
    private Viewer view;
    private Notification notification;
    
    public void addTask(Map <TaskParameters, Object> parameters) {
        if (taskCreator.validate(parameters)) {
            tasks.add(taskCreator.createTask(parameters));
        } else {
            System.out.println("Задачу создать нельзя!");
        }
    }

    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public void editTask(int id, Map <TaskParameters, Object> parameters) {
        for(Map.Entry entry : parameters.entrySet()){
            TaskParameters parameter = (TaskParameters)entry.getKey();
            Object value = entry.getValue();
            ((Task) tasks.get(id)).setValue(parameter, value);
        }
    }

    public ArrayList getTasks() {
        return this.tasks;
    }

    public void save() {
        dataLoader.saveData(tasks);
    }

    public void load() {
        tasks = dataLoader.loadData();
    }
    
    public void showMenu() {
        view = new MenuViewer();
        this.view.show(this);
    }

    public void notificationStart() {
        notification = new Notification();
        journalChanged();
        notification.start();
    }
    
    public void journalChanged() {
        notification.setActual(this);
    }
    
    public void setDataLoader(DataLoader dataLoader){
        this.dataLoader = dataLoader;
    }

    public void setTaskCreator(TaskCreator taskCreator) {
        this.taskCreator = taskCreator;
    }
}