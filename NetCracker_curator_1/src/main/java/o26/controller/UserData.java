package o26.controller;


import java.io.*;

public class UserData implements IUserData{

    @Override
    public boolean userRegistration(String login, String password) {

        FileWriter fileWriter = null;
        
        if(!checkLogin(login)) {
            return false;
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
        return true;
    }
    
    @Override
    public boolean checkLogin(String login) {
        FileReader reader = null;
        BufferedReader bufferedReader = null;
        
        try {
            reader = new FileReader("users");
            bufferedReader = new BufferedReader(reader);
            String login1;
            do {
                login1 = bufferedReader.readLine();
                    if (login.equals(login1)) {
                    return false;
                    }
            } while (bufferedReader.readLine() != null);
        } catch (FileNotFoundException e) {
            System.out.println("Такого пользователя нет!");
        } catch (IOException e) {
            System.out.println("Ошибка чтения из файла users");
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
        return true;
    }
    
    @Override
    public boolean checkPassword(String login, String password) {
        FileReader reader = null;
        BufferedReader bufferedReader = null;
        
        try {
            reader = new FileReader("users");
            bufferedReader = new BufferedReader(reader);
            while(!login.equals(bufferedReader.readLine()));
            if (password.equals(bufferedReader.readLine())) {
                return true;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Такого пользователя нет!");
        } catch (IOException e) {
            System.out.println("Ошибка чтения из файла users");
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
        
        return false;
    }
}