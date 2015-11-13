package o26.Model;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable{
    private String name;
    private String description;
    private String contacts;
    private Date date;

    
    public String getName(){
        return this.name;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public String getContacts(){
        return this.contacts;
    }
    
    public Date getDate(){
        return (Date)this.date.clone();
    }
    
    public void setName(String newName){
        this.name = newName;
    }
    
    public void setDescription(String newDescription){
        this.description = newDescription;
    }
    
    public void setContacts(String newContacts){
        this.contacts = newContacts;
    }
    
    public void setDate(Date newDate){
        this.date = (Date)newDate.clone();
    }
    public Task(String name, String description, String contacts, Date date){
        this.name = name;
        this.description = description;
        this.contacts = contacts;
        this.date = (Date)date.clone();
    }

    @Override
    public Task clone(){
        String name = this.getName();
        String description = this.getDescription();
        String contacts = this.getContacts();
        Date date = this.getDate();
         return new Task(name, description, contacts, date);
    }
}
