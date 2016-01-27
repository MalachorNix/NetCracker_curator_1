package o26.view;


import o26.controller.Journal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserItem extends MenuItem {

    private static final String ITEM = "Экран входа пользователя";

    @Override
    public void show(Journal journal) {
        System.out.println("Добро пожаловать в планировщик задач!");
        System.out.println("Чтобы войти в программу, нажмите 1.");
        System.out.println("Чтобы зарегистрироваться, нажмите 2.");
        System.out.println("Чтобы выйти из программы, нажмите 0.");

        choice();


    }

    private void choice() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        try {
            do {
                System.out.print("Выберите действие: ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Ввод логина/пароля");
                        login();
                        break;
                    case 2:
                        System.out.println("Регистрация");
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        System.out.println("Такого действия нет!");
                }
            } while (choice != 1);
        } catch (InputMismatchException e) {
            System.out.println("Вводите только целые числа.");
            choice();
        }
    }

    private void login() {

    }

    @Override
    public String toString() {
        return ITEM;
    }
}