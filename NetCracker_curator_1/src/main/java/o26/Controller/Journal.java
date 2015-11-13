package o26.Controller;

import o26.Model.Task;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Journal {
    private ArrayList tasks;
    
    public void addTask(Task task) {
        this.tasks.add(task.clone());
    }

    
    public void deleteTask(int id) {
        this.tasks.remove(id);
    }

    
    public void editTask(int id, Task task) {
        this.tasks.set(id, task.clone());
    }

    
    public ArrayList getTasks() {
        return (ArrayList)this.tasks.clone();
    }

    public void loadTasks() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            fis = new FileInputStream("data");
            ois = new ObjectInputStream(fis);
            this.tasks = (ArrayList)ois.readObject();
        }
        catch(java.io.FileNotFoundException fnfe){
            System.out.println("Journal is empty!");
            this.tasks = new ArrayList<Task>();
        }
        catch (IOException | ClassNotFoundException me) {
            System.out.println("Load fail : "+me.getMessage());
        }
        finally{
            try{
                if(ois!=null) ois.close();
            } catch (IOException ex) {
                System.out.println("Close ois fail : "+ex.getMessage());
            }
            finally{
                try{
                    if(fis!=null) fis.close();
                }
                catch (IOException ex) {
                    System.out.println("Close fis fail : "+ex.getMessage());
                }
            }
        }
    }

    public void saveTasks() {
        if(this.tasks!=null && !this.tasks.isEmpty()){
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;
            try{
                fos = new FileOutputStream("data");
                oos = new ObjectOutputStream(fos);
                oos.writeObject(this.tasks);
            }
            catch(java.io.FileNotFoundException fnfe){
                System.out.println("Save fail : "+fnfe.getMessage());
            }
            catch (IOException ioe) {
                System.out.println("Save fail : "+ioe.getMessage());
            }
            finally{
                try{
                    if(oos!=null) oos.close();
                } catch (IOException ex) {
                    System.out.println("Close oos fail : "+ex.getMessage());
                }
                finally{
                    try{
                        if(fos!=null) fos.close();
                    }
                    catch (IOException ex) {
                        System.out.println("Close fos fail : "+ex.getMessage());
                    }
                }
            }
        }
        else{
            System.out.println("Can't save empty Journal!");
        }
    }
}