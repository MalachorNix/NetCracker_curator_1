package o26.controller;


import java.io.*;

public class UserData implements IUserData{

    @Override
    public int userRegistration(String login, String password) {

        FileWriter fileWriter = null;
        
        if(checkLogin(login) == -1) {
            return -1;
        }
        
        try {
            fileWriter = new FileWriter("users", true);
            fileWriter.write(login + "\n");
            fileWriter.write(password + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл!");
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("Ошибка закрытия потока регистрации "
                            + "пользователя в файл!");
                }
            }
        }
        return 1;
    }
    
    private int checkLogin(String login) {
        FileReader reader = null;
        BufferedReader bufferedReader = null;
        
        try {
            reader = new FileReader("users");
            bufferedReader = new BufferedReader(reader);
            String login1;
            do {
                login1 = bufferedReader.readLine();
                    if (login.equals(login1)) {
                    return -1;
                    }
            } while (bufferedReader.readLine() != null);
        } catch (FileNotFoundException e) {
            System.out.println("Файл logins не найден!");
        } catch (IOException e) {
            System.out.println("Ошибка чтения из файла logins");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Ошибка ввода/вывода при закрытии "
                            + "потока чтения");
                }
            }
            
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.out.println("Ошибка ввода/вывода при закрытии "
                            + "потока чтения");
                }
            }
        }
        return 1;
    }
}