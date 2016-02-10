package o26.controller;


import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class UserData implements IUserData{

    @Override
    @SuppressWarnings("unchecked")
    public boolean userRegistration(String login, String password) {

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        HashMap<String, String> userData;

        if(!checkLogin(login)) {
            return false;
        }

        try {
            File usersFile = new File("secretUsers");

            if (usersFile.exists()) {
                fileInputStream = new FileInputStream(usersFile);
                userData = (HashMap<String, String>) Decryptor.decrypt(fileInputStream);
                if (userData != null) {
                    userData.put(login, password);
                }
            } else {
                userData = new HashMap<>();
                userData.put(login, password);
            }
            fileOutputStream = new FileOutputStream(usersFile);

            Encryptor.encrypt(userData, fileOutputStream);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл!");
        } catch (NoSuchPaddingException | InvalidKeyException | NoSuchAlgorithmException e) {
            System.out.println("Ошибка шифрования данных!");
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                System.out.println("Ошибка работы с файлом пользовтелей");
            }
        }
        return true;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public boolean checkLogin(String login) {
        FileInputStream fileInputStream = null;
        Map<String, String> userData;
        
        try {
            File usersFile = new File("secretUsers");

            if (usersFile.exists()) {
                fileInputStream = new FileInputStream(usersFile);
                userData = (HashMap) Decryptor.decrypt(fileInputStream);
                if (userData != null && userData.containsKey(login)) {
                    return false;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Такого пользователя нет!");
        } catch (IOException e) {
            System.out.println("Ошибка чтения из файла users");
        } catch (NoSuchPaddingException | InvalidKeyException | NoSuchAlgorithmException e) {
            System.out.println("Ошибка шифрования файла");
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                System.out.println("Ошибка работы с файлом пользовтелей");
            }
        }
        return true;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public boolean checkPassword(String login, String password) {
        FileInputStream fileInputStream = null;
        Map<String, String> userData;
        
        try {
            File usersFile = new File("secretUsers");

            if (usersFile.exists()) {
                fileInputStream = new FileInputStream(usersFile);
                userData = (HashMap) Decryptor.decrypt(fileInputStream);
                if (userData != null && userData.containsKey(login)) {
                    if (userData.get(login).equals(password))
                        return true;
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Такого пользователя нет!");
        } catch (IOException e) {
            System.out.println("Ошибка чтения из файла users");
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
            System.out.println("Ошибка шифрования файла");
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                System.out.println("Ошибка работы с файлом пользовтелей");
            }
        }
        
        return false;
    }
}