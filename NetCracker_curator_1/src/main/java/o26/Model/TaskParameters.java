package o26.Model;

public enum TaskParameters {
    NAME , DESCRIPTION , DATE, CONTACTS;

    
    @Override
    public String toString(){
        switch(this){
            case NAME: return "NAME";
            case DESCRIPTION: return "DESCRIPTION";
            case DATE: return "DATE";
            case CONTACTS: return "CONTACTS";
            default: return "";
        }
    }
}