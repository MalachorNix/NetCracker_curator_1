package o26.controller;

import o26.model.Task;
import o26.model.TaskParameter;
import o26.view.MenuItem;

import java.util.ArrayList;
import java.util.Map;
import o26.view.MenuMenuItem;

public class Journal {
    private ArrayList tasks;
    private Loader loader;
    private TaskCreator taskCreator;
    private MenuItem view;
    private Notification notification;
    
    public void addTask(Map <TaskParameter, Object> parameters) {
        if (taskCreator.validate(parameters)) {
            tasks.add(taskCreator.createTask(parameters));
        } else {
            System.out.println("Задачу создать нельзя!");
        }
    }

    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public void editTask(int id, Map <TaskParameter, Object> parameters) {
        for(Map.Entry entry : parameters.entrySet()){
            TaskParameter parameter = (TaskParameter)entry.getKey();
            Object value = entry.getValue();
            ((Task) tasks.get(id)).setValue(parameter, value);
        }
    }

    public ArrayList getTasks() {
        return this.tasks;
    }

    public void save() {
        loader.saveData(tasks);
    }

    public void load() {
        tasks = loader.loadData();
    }
    
    public void showMenu() {
        view = new MenuMenuItem();
        this.view.show(this);
    }

    public void notificationStart() {
        journalChanged();
        notification.start();
    }
    
    public void journalChanged() {
        notification.setActual(this);
    }
    
    public void setLoader(Loader loader){
        this.loader = loader;
    }

    public void setTaskCreator(TaskCreator taskCreator) {
        this.taskCreator = taskCreator;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}