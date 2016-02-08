package o26.view;


import java.io.Console;
import o26.controller.Journal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserItem extends AbstractMenuItem {

    private static final String ITEM = "Экран входа пользователя";
    private static final Console CONSOLE = System.console();
    
    @Override
    public void show(Journal journal) {
        salutation();
        choice(journal);
    }
    
    private void salutation() {
        System.out.println("Добро пожаловать в планировщик задач!");
        System.out.println("Чтобы войти в программу, нажмите 1.");
        System.out.println("Чтобы зарегистрироваться, нажмите 2.");
        System.out.println("Чтобы выйти из программы, нажмите 0.");
    }

    private void choice(Journal journal) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        try {
            do {
                System.out.print("Выберите действие: ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Ввод логина/пароля");
                        break;
                    case 2:
                        System.out.println("Регистрация");
                        registration(journal);
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Такого действия нет!");
                        break;
                }
            } while (choice != 1);
            if(!login(journal)) {
                System.out.println("Неправильный логин или пароль!");
                salutation();
//                journal = new Journal();
                choice(journal);
            }
        } catch (InputMismatchException e) {
            System.out.println("Вводите только целые числа.");
            choice(journal);
        }
    }
    
    private boolean login(Journal journal) {
        String password;
        String login = inputLogin();
        
        if (CONSOLE == null) {
            password = inputPassword();
        } else {
            password = hidePassword();
        }
        
        journal.login(login, password);
        return false;
    }

    private void registration(Journal journal) {
        String login;
        String password;
        String password1;
        boolean check;

        login = inputLogin();

        do {            
            if (CONSOLE == null) {
            password = inputPassword();
            password1 = inputPassword();
            } else {
            password = hidePassword();
            password1 = hidePassword();
            }
            check = journal.validatePasswords(password, password1);
            if (!check) {
                System.out.println("Пароли не совпадают. Попробуйте снова.");
            }
        } while (!check);

        if (journal.registration(login, password)) {
            System.out.println("Регистрация прошла успешно!");
        } else {
            System.out.println("Данный логин занят. Попробуйте другой.");
        }
        salutation();
    }

    private String inputLogin() {
        Scanner scanner = new Scanner(System.in);
        String login;

        System.out.print("Введите свой логин: ");
        login = scanner.nextLine();
        return login;
    }

    private String inputPassword() {
        String password;
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите свой пароль: ");
        password = scanner.nextLine();
        
        return password;
    }
    
    private String hidePassword() {
        String password;
        
        char[] pass = CONSOLE.readPassword("Введите свой пароль: ");
        password = new String(pass);
        
        return password;
    }


    @Override
    public String toString() {
        return ITEM;
    }
}