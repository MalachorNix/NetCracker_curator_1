package o26.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;


public class Task implements Serializable, ITask{

    private List<Parameter> parameters;
    
    public Task(List<Parameter> parameters) {
        this.parameters = parameters;
    }
    
   @Override
    public Object getValue(Parameter.TypeParameter type) {
        for (Parameter parameter : parameters) {
            if (type == parameter.getType()) return parameter.getValue();
        }
        return null;
    }

    @Override
    public void setValue(Parameter.TypeParameter type, Object value) {
        for (Parameter parameter : parameters) {
            if (type.equals(parameter.getType())) {
                parameter.setValue(type, value);
                break;
            }
        }
    }
    
    @Override
    public List<Parameter> getParameters(){
        return parameters;
    }
    
    @Override
    public String toString(){
        String result;
        StringBuilder stringBuilder= new StringBuilder("");
        for (Parameter parameter : parameters) {
            Object value = parameter.getValue();
            if (parameter.getType().equals(Parameter.TypeParameter.DATE)) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMMM y 'г.' HH:mm:ss");
                value = simpleDateFormat.format(((GregorianCalendar) value).getTime());
            }
            stringBuilder.append(parameter.getType().toString()).append(":\n\t").append(value.toString()).append("\n");
        }
        result = stringBuilder.toString();
        return result;
    }
}