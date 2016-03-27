package o26.controller;

import o26.model.ITask;

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

public class DataLoader implements Loader {

    private static final String ERROR_CLOSING_READ_STREAM = "Ошибка закрытия потока чтения из файла.";
    private static final String SERVER_OUTPUT_PATH = "serverSide/";

    @Override
    public List<ITask> loadData(String login) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<ITask> result = null;
        try {
            File loginAndId = new File(SERVER_OUTPUT_PATH + "loginAndId");

            if (loginAndId.exists()) {
                fis = new FileInputStream(loginAndId);
                ois = new ObjectInputStream(fis);
                @SuppressWarnings("unchecked")
                Map<String, List<Integer>> idList = (HashMap) ois.readObject();
                List<Integer> id = idList.get(login);

                if (id != null) {
                    result = new TaskXmlParser().parse(SERVER_OUTPUT_PATH + "data.xml", id);
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
    @SuppressWarnings("unchecked")
    public void saveData(List<ITask> tasks, String login, List<Integer> idList) {

        TaskXmlCreator taskXmlCreator = new TaskXmlCreator();

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {

            List<ITask> tmp = new TaskXmlParser().parse(SERVER_OUTPUT_PATH + "data.xml", idList);

            if (tmp != null) {
                for (ITask task : tasks) {
                    if (!tmp.contains(task)) {
                        tmp.add(task);
                    }
                }
                tasks = tmp;
            }

            taskXmlCreator.createTaskXml(tasks, SERVER_OUTPUT_PATH + "data.xml");

            File loginAndId = new File(SERVER_OUTPUT_PATH + "loginAndId");
            Map<String, List<Integer>> idList1;
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
                System.out.println(ERROR_CLOSING_READ_STREAM);
            }
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException iOException) {
                System.out.println(ERROR_CLOSING_READ_STREAM);
            }
        }
    }

    @Override
    public List<Integer> getListId() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<Integer> listId = null;

        try {

            fis = new FileInputStream(SERVER_OUTPUT_PATH + "loginAndId");
            ois = new ObjectInputStream(fis);

            @SuppressWarnings("unchecked")
            Map<String, List<Integer>> idList = (HashMap) ois.readObject();
            listId = new ArrayList<>();

            for (List<Integer> i : idList.values()) {
                listId.addAll(i);
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
                    System.out.println(ERROR_CLOSING_READ_STREAM);
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    System.out.println(ERROR_CLOSING_READ_STREAM);
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