package o26.controller;

import o26.model.Task;
import o26.model.TaskParameter;
import o26.view.MenuItem;

import java.util.ArrayList;
import java.util.Map;
import o26.view.MenuMenuItem;

public class Journal {
    private ArrayList tasks;
    private DataLoader dataLoader;
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
        dataLoader.saveData(tasks);
    }

    public void load() {
        tasks = dataLoader.loadData();
    }
    
    public void showMenu() {
        view = new MenuMenuItem();
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