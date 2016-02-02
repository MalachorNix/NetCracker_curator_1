package o26.controller;

import java.util.List;

import o26.model.ITask;
import o26.model.Parameter;
import o26.model.Task;

public class TaskCreator implements ITaskCreator{

    @Override
    public Task createTask(List<Parameter> parameters) {
        return new Task(parameters);
    }

    @Override
    public boolean validate(List<Parameter> parameters, List<ITask> tasks) {
        int id;
        if (tasks != null && tasks.size() >= 0) {
            for (Parameter parameter : parameters) {
                if (parameter.getType().toString().equals(Parameter.TypeParameter.ID.toString())) {
                    id = (int) parameter.getValue();
                    if (tasks.size() > 0) {
                        for (int i = 0; i < tasks.size(); i++) {
                            if ((int) tasks.get(i).getValue(Parameter.TypeParameter.ID) >= id) {
                                id = (int) tasks.get(i).getValue(Parameter.TypeParameter.ID);
                                id++;
                                parameter.setValue(Parameter.TypeParameter.ID, id);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}