package o26.view;


import o26.controller.Journal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserItem extends MenuItem {

    private static final String ITEM = "Экран входа пользователя";

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
        String login, password, password1;
        try {
            do {
                System.out.print("Выберите действие: ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Ввод логина/пароля");
                        login = inputLogin();
                        password = inputPassword();
                        break;
                    case 2:
                        System.out.println("Регистрация");
                        registration(journal);
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        System.out.println("Такого действия нет!");
                }
            } while (choice != 1);
        } catch (InputMismatchException e) {
            System.out.println("Вводите только целые числа.");
            choice(journal);
        }
    }

    private void registration(Journal journal) {
        String login;
        String password;
        String password1;
        int check;

        login = inputLogin();

        do {
            password = inputPassword();
            password1 = inputPassword();

            check = journal.validatePasswords(password, password1);
            if (check == -1) {
                System.out.println("Пароли не совпадают. Попробуйте снова.");
            }
        } while (check != 1);

        if (journal.registration(login, password) == -1) {
            System.out.println("Данный логин занят. Попробуйте другой.");
        } else {
            System.out.println("Регистрация прошла успешно!");
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


    @Override
    public String toString() {
        return ITEM;
    }
}