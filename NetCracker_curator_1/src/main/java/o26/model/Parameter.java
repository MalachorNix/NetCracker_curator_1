package o26.model;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Parameter implements Serializable{
    static public enum TypeParameter{
        NAME
                {

//            @Override
            public boolean validate(Object value) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        
        } , DESCRIPTION , DATE, CONTACTS;
//        public abstract boolean validate(Object value);
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
                return (GregorianCalendar)value;
            }
            default: return (String)value;
        }
    }
}
