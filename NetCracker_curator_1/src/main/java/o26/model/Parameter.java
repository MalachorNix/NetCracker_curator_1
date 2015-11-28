package o26.model;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Parameter implements Serializable{
    public enum TypeParameter{
        NAME {
            public boolean validate(Object value) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String toString() {
                return "название задачи";
            }
        } , DESCRIPTION {
            @Override
            public String toString() {
                return "описание задачи";
            }
        } , DATE {
            @Override
            public String toString() {
                return "время оповещения";
            }
        }, CONTACTS {
            @Override
            public String toString() {
                return "контакты";
            }
        }
    }
    private TypeParameter type;
    private Object value;
    
    public Parameter(){
        this.type = null;
        this.value = null;
    }
    
    public Parameter(TypeParameter type, Object value){
        this.type = type;
        this.value = value;
    }
    
    public void setValue(TypeParameter type, Object value){
        this.type = type;
        this.value = value;
    }
    
    public TypeParameter getType(){
        return type;
    }
    
    public Object getValue(){
        switch(type){
            case DATE:{
                return value;
            }
            default: return value;
        }
    }
}
