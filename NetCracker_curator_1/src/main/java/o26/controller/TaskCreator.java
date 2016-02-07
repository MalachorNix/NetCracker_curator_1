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
        int id;
        if (listId != null) {
            for (Parameter parameter : parameters) {
                if (parameter.getType().toString().equals(Parameter.TypeParameter.ID.toString())) {
                    id = (int) parameter.getValue();
                    if (listId.size() > 0) {
                        while(listId.contains(id)) {
                        id++;
                        }
                    }
                    listId.add(id);
                    parameter.setValue(Parameter.TypeParameter.ID, id);
                }
            }
        }
        return true;
    }
}