package o26.model;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Map.Entry;


public class Task implements Serializable, ITask{

    private Map<TaskParameter, Object> parameters;

    public Task(Map<TaskParameter, Object> parameters) {
        this.parameters = parameters;
    }

    public Object getValue(TaskParameter parameter) {
        return this.parameters.get(parameter);
    }

    public void setValue(TaskParameter parameter, Object value) {
        this.parameters.put(parameter, value);
    }
    
    public Map<TaskParameter, Object> getParameters(){
        return parameters;
    }
    
    @Override
    public String toString(){
        String result = "";
        for(Entry entry: parameters.entrySet()) {
            TaskParameter parameter = (TaskParameter) entry.getKey();
            Object value = entry.getValue();
            if(parameter.equals(TaskParameter.DATE)){
                value = ((GregorianCalendar)value).getTime();
            }
            result += parameter.toString()+":\n\t"+value.toString()+"\n";
        }
        return result;
    }
}