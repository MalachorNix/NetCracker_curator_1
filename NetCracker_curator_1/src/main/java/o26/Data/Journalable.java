/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package o26.Data;

import java.util.ArrayList;

/**
 *
 * @author monin
 */
public interface Journalable {
    void addTask(Task task);
    void deleteTask(int id);
    void editTask(int id, Task task);
    ArrayList<Task> getTasks();
    void loadTasks();
    void saveTasks();
}
