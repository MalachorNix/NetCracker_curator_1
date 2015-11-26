package o26.model;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.List;


public class Task implements Serializable, ITask{

    private List<Parameter> parameters;

    public Task(List parameters) {
        this.parameters = parameters;
    }

    @Override
    public Object getValue(Parameter.TypeParameter type) {
        int count = parameters.size();
        for(int i = 0; i < count; i++){
            Parameter parameter = parameters.get(i);
            if(type.equals(parameter.getType())) return parameter.getValue();
        }
        return null;
    }

    @Override
    public void setValue(Parameter.TypeParameter type, Object value) {
        int count = parameters.size();
        for(int i = 0; i < count; i++){
            Parameter parameter = parameters.get(i);
            if(type.equals(parameter.getType())){
                parameter.setValue(type, value);
                break;
            }
        }
    }
    
    @Override
    public List getParameters(){
        return parameters;
    }
    
    @Override
    public String toString(){
        String result;
        StringBuilder stringBuilder= new StringBuilder("");
        int count = parameters.size();
        for(int i = 0; i < count; i++) {
            Parameter parameter = parameters.get(i);
            Object value = parameter.getValue();
            if(parameter.getType().equals(Parameter.TypeParameter.DATE)){
                value = ((GregorianCalendar)value).getTime();
            }
            stringBuilder.append(parameter.getType().name()).append(":\n\t").append(value.toString()).append("\n");
        }
        result = stringBuilder.toString();
        return result;
    }
}