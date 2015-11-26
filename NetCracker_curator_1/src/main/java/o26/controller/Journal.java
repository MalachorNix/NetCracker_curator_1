package o26.controller;

import o26.model.Task;
import o26.view.MenuItem;

import java.util.List;
import o26.model.Parameter;

public class Journal {
    private List tasks;
    private Loader loader;
    private ITaskCreator taskCreator;
    private MenuItem view;
    private INotification notification;
    
    public void addTask(List parameters) {
        if (taskCreator.validate(parameters)) {
            tasks.add(taskCreator.createTask(parameters));
        } else {
            System.out.println("Задачу создать нельзя!");
        }
    }

    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public void editTask(int id, List parameters) {
        int count = parameters.size();
        for(int i = 0; i < count; i++){
            Parameter parameter = (Parameter) parameters.get(i);
            ((Task) tasks.get(id)).setValue(parameter.getType(), parameter.getValue());
        }
    }

    public List getTasks() {
        return this.tasks;
    }

    public void save() {
        loader.saveData(tasks);
    }

    public void load() {
        tasks = loader.loadData();
    }
    
    public void showMenu() {
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

    public void setTaskCreator(ITaskCreator taskCreator) {
        this.taskCreator = taskCreator;
    }

    public void setNotification(INotification notification) {
        this.notification = notification;
    }

    public void setView(MenuItem view) {
        this.view = view;
    }
}