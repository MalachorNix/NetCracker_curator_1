package o26.controller;

import java.util.List;
import o26.model.Task;

public class TaskCreator implements ITaskCreator{

    @Override
    public Task createTask(List parameters) {
        return new Task(parameters);
    }

    /*
    * Валидирует семантику.
    * Имеет смысл делать это только с датой, так как остальные параметры просто строковое описание.
    * */

    @Override
    public boolean validate(List parameters) {
        return true;
    }
}