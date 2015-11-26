package o26.controller;

import java.util.List;
import o26.model.Task;

public interface ITaskCreator {

    Task createTask(List parameters);

    boolean validate(List parameters);
}
