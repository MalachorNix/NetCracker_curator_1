package o26.Controller;

import o26.Model.Task;
import o26.Model.TaskParameters;

import java.util.Map;

public class TaskCreator {

    public Task createTask(Map<TaskParameters, Object> parameters) {
        Task task = new Task(parameters);
        return task;
    }

//    Валидирует семантику.
    public boolean validate(Map<TaskParameters, Object> map) {
        return true;
    }
}