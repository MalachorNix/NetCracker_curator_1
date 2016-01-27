package o26.controller;


import java.io.*;

public class UserData implements IUserData{

    @Override
    public void userRegistration(String login, String password) {
//        FileOutputStream fos = null;
//        ObjectOutputStream oos = null;
        FileWriter fileWriter = null;

        try {
            /*fos = new FileOutputStream("users", true);
            oos = new ObjectOutputStream(fos);
            oos.writeChars(login);
            oos.writeChars(password);*/
            /*oos.writeObject(login);
            oos.writeObject(password);*/
            fileWriter = new FileWriter("users", true);
            fileWriter.write(login + "\n");
            fileWriter.write(password + "\n");
            System.out.println("Регистрация прошла успешно");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл!");
        } finally {
            /*if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    System.out.println("Ошибка закрытия потока регистрации пользователя в файл!");
                }
            }
            if (oos != null) {
                System.out.println("Ошибка закрытия потока регистрации пользователя!");
            }*/
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("Ошибка закрытия потока регистрации пользователя в файл!");
                }
            }
        }
    }
}