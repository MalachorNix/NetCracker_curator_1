package o26.view;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserItem extends AbstractMenuItem {

    private static final String ITEM = "Экран входа пользователя";
    private static final Console CONSOLE = System.console();

    @Override
    public void show(ObjectInputStream inputStream, ObjectOutputStream outputStream) {
        salutation();
        choice(inputStream, outputStream);
    }

    private void salutation() {
        System.out.println("Добро пожаловать в планировщик задач!");
        System.out.println("Чтобы войти в программу, нажмите 1.");
        System.out.println("Чтобы зарегистрироваться, нажмите 2.");
        System.out.println("Чтобы выйти из программы, нажмите 0.\n");
    }

    private void choice(ObjectInputStream inputStream, ObjectOutputStream outputStream) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        try {
            do {
                System.out.print("Выберите действие: ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("\nВвод логина/пароля");
                        break;
                    case 2:
                        System.out.println("\nРегистрация");
                        registration(inputStream, outputStream);
                        break;
                    case 0:
                        try {
                            outputStream.writeInt(0);
                            outputStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Такого действия нет!");
                        break;
                }
            } while (choice != 1);
            if (!login(inputStream, outputStream)) {
                System.out.println("Неправильный логин или пароль!");
                salutation();
                choice(inputStream, outputStream);
            }
        } catch (InputMismatchException e) {
            System.out.println("Вводите только целые числа.");
            choice(inputStream, outputStream);
        }
    }

    private boolean login(ObjectInputStream inputStream, ObjectOutputStream outputStream) {
        String password;
        String login = inputLogin();

        if (CONSOLE == null) {
            password = inputPassword();
        } else {
            password = hidePassword();
        }

        try {
            outputStream.writeInt(1);
            outputStream.flush();
            outputStream.writeUTF(login);
            outputStream.flush();
            outputStream.writeUTF(password);
            outputStream.flush();
            // TODO: 26.03.2016 избавиться от юзеритем в стеке
            if (inputStream.readBoolean()) {
                AbstractMenuItem menuItem = new MainMenuItem();
                menuItem.show(inputStream, outputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO: 26.03.2016 Что-то потом с этим придумать
        return false;
    }

    private void registration(ObjectInputStream inputStream, ObjectOutputStream outputStream) {
        String login;
        String password;
        String password1;
        boolean check = false;

        login = inputLogin();

        do {
            if (CONSOLE == null) {
                password = inputPassword();
                password1 = inputPassword();
            } else {
                password = hidePassword();
                password1 = hidePassword();
            }

            try {
                outputStream.writeInt(4);
                outputStream.flush();
                outputStream.writeUTF(password);
                outputStream.flush();
                outputStream.writeUTF(password1);
                outputStream.flush();
                check = inputStream.readBoolean();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!check) {
                System.out.println("Пароли не совпадают. Попробуйте снова.");
            }
        } while (!check);

        boolean success = false;
        try {
            outputStream.writeInt(5);
            outputStream.flush();
            outputStream.writeUTF(login);
            outputStream.flush();
            outputStream.writeUTF(password);
            outputStream.flush();
            success = inputStream.readBoolean();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (success) {
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