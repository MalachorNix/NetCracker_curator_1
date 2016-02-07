package o26.controller;

import java.util.List;

import o26.model.Parameter;
import o26.model.Task;

public interface ITaskCreator {

    Task createTask(List<Parameter> parameters);

    boolean validate(List<Parameter> parameters, List<Integer> listId);
}
