package o26.controller;

import o26.model.Task;
import o26.model.TaskParameters;

import java.util.GregorianCalendar;
import java.util.Map;

public class TaskCreator {

    public Task createTask(Map<TaskParameters, Object> parameters) {
        return new Task(parameters);
    }

    /*
    * Валидирует семантику.
    * Имеет смысл делать это только с датой, так как остальные параметры просто строковое описание.
    * */

    public boolean validate(Map<TaskParameters, Object> parameters) {

        GregorianCalendar calendar = new GregorianCalendar();
        GregorianCalendar value = (GregorianCalendar) parameters.get(TaskParameters.DATE);
        return true;
    }
}