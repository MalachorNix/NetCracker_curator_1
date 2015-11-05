/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.O26.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author monin
 */
public class Task implements Taskable, Serializable{
    private String name;
    private String description;
    private String contatcs;
    private Date date;

    @Override
    public String getName(){
        return this.name;
    }
    @Override
    public String getDescription(){
        return this.description;
    }
    @Override
    public String getContacts(){
        return this.contatcs;
    }
    @Override
    public Date getDate(){
        return (Date)this.date.clone();
    }
    @Override
    public void setName(String newName){
        this.name = newName;
    }
    @Override
    public void setDescription(String newDescription){
        this.description = newDescription;
    }
    @Override
    public void setContacts(String newContacts){
        this.contatcs = newContacts;
    }
    @Override
    public void setDate(Date newDate){
        this.date = (Date)newDate.clone();
    }
    public Task(String name, String description, String contacts, Date date){
        this.name = name;
        this.description = description;
        this.contatcs = contacts;
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
