package o26.model;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Map.Entry;


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
    
    public Map<TaskParameters, Object> getParameters(){
        return parameters;
    }
    
    @Override
    public String toString(){
        String result = "";
        for(Entry entry: parameters.entrySet()) {
            TaskParameters parameter = (TaskParameters) entry.getKey();
            Object value = entry.getValue();
            if(parameter.equals(TaskParameters.DATE)){
                value = ((GregorianCalendar)value).getTime();
            }
            result += parameter.toString()+":\n\t"+value.toString()+"\n";
        }
        return result;
    }
}