package o26.controller;

import o26.model.Task;
import o26.model.TaskParameter;

import java.util.Map;

public interface ITaskCreator {

    Task createTask(Map<TaskParameter, Object> parameters);

    boolean validate(Map<TaskParameter, Object> parameters);
}
