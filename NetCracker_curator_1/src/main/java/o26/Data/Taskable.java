/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package o26.Data;

import java.util.Date;

/**
 *
 * @author monin
 */
public interface Taskable {
    String getName();
    String getDescription();
    String getContacts();
    Date getDate();
    void setName(String newName);
    void setDescription(String newDescription);
    void setContacts(String newContacts);
    void setDate(Date newDate);
}
