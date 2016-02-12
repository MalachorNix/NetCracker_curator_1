package o26.controller;

import java.util.List;

import o26.model.Parameter;
import o26.model.Task;

public class TaskCreator implements ITaskCreator{

    @Override
    public Task createTask(List<Parameter> parameters) {
        return new Task(parameters);
    }

    @Override
    public boolean validate(List<Parameter> parameters, List<Integer> listId) {
        return true;
    }
}