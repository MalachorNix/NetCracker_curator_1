package o26.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class DataLoader {

    public ArrayList loadData() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        ArrayList result = null;
        try {
            fis = new FileInputStream("data");
            ois = new ObjectInputStream(fis);
            result = (ArrayList) ois.readObject();
        } catch (FileNotFoundException e) {
            result = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка загрузки: " + e.getMessage());
        } finally {
            try {
                if (ois != null) ois.close();
            } catch (IOException e) {
                System.out.println("Ошибка закрытия потока чтения задач: " + e.getMessage());
            } finally {
                try {
                    if (fis != null) fis.close();
                } catch (IOException e) {
                    System.out.println("Ошибка закрытия потока чтения задач из файла: " + e.getMessage());
                }
            }
        }
        return result;
    }

    public void saveData(ArrayList tasks) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("data");
            oos = new ObjectOutputStream(fos);
            oos.writeObject((tasks!=null)?tasks:new ArrayList<>());
        } catch (IOException ioe) {
            System.out.println("Ошибка сохранения: " + ioe.getMessage());
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                System.out.println("Ошибка закрытия потока записи задач: " + ex.getMessage());
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException ex) {
                    System.out.println("Ошибка закрытия потока записи в файл: " + ex.getMessage());
                }
            }
        }
    }
}