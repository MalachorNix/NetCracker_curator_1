package o26.Model;

import java.io.Serializable;
import java.util.Map;


public class Task implements Serializable{

    private Map<TaskParameters, Object> parameters;

    public Task(Map<TaskParameters, Object> parameters) {
        this.parameters = parameters;
    }

    public Object getValue(TaskParameters parameter) {
        return this.parameters.get(parameter);
    }

    public void setValue(TaskParameters parameter, Object value) {
        this.parameters.put(parameter, value);
    }
}