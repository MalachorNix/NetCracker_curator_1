package o26.controller;

import o26.model.Task;
import o26.model.TaskParameter;

import java.util.GregorianCalendar;
import java.util.Map;

public class TaskCreator {

    public Task createTask(Map<TaskParameter, Object> parameters) {
        return new Task(parameters);
    }

    /*
    * Валидирует семантику.
    * Имеет смысл делать это только с датой, так как остальные параметры просто строковое описание.
    * */

    public boolean validate(Map<TaskParameter, Object> parameters) {

        GregorianCalendar calendar = new GregorianCalendar();
        GregorianCalendar value = (GregorianCalendar) parameters.get(TaskParameter.DATE);
        return true;
    }
}