package o26.model;

import java.io.Serializable;

public class Parameter implements Serializable{
    public enum TypeParameter{
        NAME {
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
        }, ID {
            public String toString() {
                return "id";
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
        if (type == TypeParameter.DATE) {
            return value;
        } else {
            return value;
        }
    }
}
