package o26.controller;

import o26.model.ITask;
import o26.model.Parameter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataLoader implements Loader{

    @Override
    public List<ITask> loadData(String login) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<ITask> result = null;
        try {
            File loginAndId = new File("loginAndId");
            
            if(loginAndId.exists()) {
                fis = new FileInputStream(loginAndId);
                ois = new ObjectInputStream(fis);
                @SuppressWarnings("unchecked")
                Map<String, List<Integer>> idList = (HashMap) ois.readObject();
                List<Integer> id = idList.get(login);
                
                if (id != null) {
                    fis = new FileInputStream("data");
                    ois = new ObjectInputStream(fis);
                    @SuppressWarnings("unchecked")
                    List<ITask> tmp = (ArrayList) ois.readObject();
                    result = new ArrayList<>();
                    
                    for (ITask task : tmp) {
                        for (Integer tmpId : id) {
                            if (((Integer) task.getValue(Parameter.TypeParameter.ID)).equals(tmpId)) {
                                result.add(task);
                            }
                        }
                    }
                }
            }            
        } catch (FileNotFoundException e) {
            result = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка загрузки: " + e.getMessage());
        } finally {
            try {
                if (ois != null) ois.close();
            } catch (IOException e) {
                System.out.println("Ошибка закрытия потока чтения задач: " 
                        + e.getMessage());
            } finally {
                try {
                    if (fis != null) fis.close();
                } catch (IOException e) {
                    System.out.println("Ошибка закрытия потока чтения "
                            + "задач из файла: " 
                            + e.getMessage());
                }
            }
        }
        if (result == null) {
            result = new ArrayList<>();
            return result;
        } else {
            return result;
        }
    }

    @Override
    public void saveData(List<ITask> tasks, String login, List<Integer> idList) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            File data = new File("data");
            if (data.exists()) {
                fis = new FileInputStream(data);
                ois = new ObjectInputStream(fis);
                List<ITask> tmp = (ArrayList) ois.readObject();

                if (tmp != null) {
                    for(ITask task: tasks) {
                        if(!tmp.contains(task)) {
                            tmp.add(task);
                        }
                    }
                    tasks = tmp;
                }
            }
            
            fos = new FileOutputStream(data);
            oos = new ObjectOutputStream(fos);
            oos.writeObject((tasks == null) ? new ArrayList<>() : tasks);
            
            File loginAndId = new File("loginAndId");
            Map<String, List<Integer>> idList1 = null;
            if (loginAndId.exists()) {
                fis = new FileInputStream(loginAndId);
                ois = new ObjectInputStream(fis);
                idList1 = (HashMap) ois.readObject();
                idList1.remove(login, idList1.get(login));
                idList1.put(login, idList);
                fos = new FileOutputStream(loginAndId);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(idList1);
            } else {
                idList1 = new HashMap<>();
                idList1.put(login, idList);
                fos = new FileOutputStream(loginAndId);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(idList1);
            }
        } catch (IOException ioe) {
            System.out.println("Ошибка сохранения: " + ioe.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Ошибка чтения из файла!");
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                System.out.println("Ошибка закрытия потока записи задач: " 
                        + ex.getMessage());
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException ex) {
                    System.out.println("Ошибка закрытия потока записи в файл: " 
                            + ex.getMessage());
                }
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException iOException) {
                System.out.println("Ошибка закрытия потока чтения из файла.");
            }
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException iOException) {
                System.out.println("Ошибка закрытия потока чтения из файла.");
            }
        }
    }
    
    @Override
    public List<Integer> getListId() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<Integer> listId = null;
        try {
            File loginAndId = new File("loginAndId");
            
            if(loginAndId.exists()) {
                fis = new FileInputStream(loginAndId);
                ois = new ObjectInputStream(fis);
                Map<String, List<Integer>> idList = (HashMap) ois.readObject();
                listId = new ArrayList<>();
                
                for(List<Integer> i: idList.values()) {
                    listId.addAll(i);
                }
            }
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Ошибка загрузки: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка чтения из файла!");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    System.out.println("Ошибка закрытия потока чтения из файла.");
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    System.out.println("Ошибка закрытия потока чтения из файла.");
                }
            }
        }
        
        if (listId == null) {
            return new ArrayList<>();
        } else {
            return listId;
        }
    }
}