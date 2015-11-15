package o26.Controller;

import o26.Model.Task;

import java.util.ArrayList;

public class Journal {
    private ArrayList tasks;
    private DataLoader dataLoader = new DataLoader();
//    private TaskCreator taskCreator = new TaskCreator();
    
    public void addTask(Task task) {
        this.tasks.add(task.clone());
    }

    public void deleteTask(int id) {
        this.tasks.remove(id);
    }

    public void editTask(int id, Task task) {
        this.tasks.set(id, task.clone());
    }

    public ArrayList getTasks() {
        return (ArrayList)this.tasks.clone();
    }

    public void save(){
        if (this.tasks != null && !this.tasks.isEmpty()) {
            dataLoader.saveData(tasks);
        }
    }

    public void load(){
        this.tasks = dataLoader.loadData(this.tasks);
    }
}