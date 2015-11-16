package o26.Controller;

import o26.Model.Task;
import o26.Model.TaskParameters;

import java.util.ArrayList;
import java.util.Map;

public class Journal {
    private ArrayList tasks;
    private DataLoader dataLoader = new DataLoader();
    private TaskCreator taskCreator;
    
    public void addTask(Map <TaskParameters, Object> parameters) {
        taskCreator = new TaskCreator();
        this.tasks.add(taskCreator.createTask(parameters));
    }

    public void deleteTask(int id) {
        this.tasks.remove(id);
    }

    public void editTask(int id, Task task) {
        this.tasks.set(id, task);
    }

    public ArrayList getTasks() {
        return this.tasks;
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