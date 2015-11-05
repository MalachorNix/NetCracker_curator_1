package com.O26;

import com.O26.Data.*;

import java.util.Date;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author monin
 */
public class Main {

    public static void showTask(Task task){
        String name = task.getName();
        String description = task.getDescription();
        String contacts = task.getContacts();
        Date date = task.getDate();
        String result = "Name:\n\t"+name+"\n";
        result += "Description:\n\t"+description+"\n";
        result += "Contacts:\n\t"+contacts+"\n";
        result += "Date:\n\t"+date+"\n";
        System.out.print(result);
    }

    public static void showJournal(Journal journal){
        int countTask = journal.getTasks().size();
        for(int i = 0; i < countTask; i++){
            int lengthId = i+"".length();
            String breakingLine = "";
            int lengthLine = 40 - lengthId - 2;

            for(int j = 0; j < lengthLine; j++) {
                breakingLine+="~";
            }
            breakingLine = "<"+i+">"+breakingLine;

            System.out.println(breakingLine);
            showTask((Task)journal.getTasks().get(i));
            System.out.println(breakingLine);
        }
    }

    private static void edit(Scanner in, String concurency, Task task) {
        switch (concurency) {
            case "1":
                System.out.print("Введите другое название задачи: ");
                String name = in.nextLine();
                task.setName(name);
                break;
            case "2":
                System.out.print("Введите другое описание задачи: ");
                String description = in.nextLine();
                task.setDescription(description);
                break;
            case "3":
                System.out.println("Введите другую дату оповещения: "); //Добавить заполнение даты.
                Date date1 = new Date();
                task.setDate(date1);
                break;
            case "4":
                System.out.print("Введите другие контакты: ");
                String contacts = in.nextLine();
                task.setContacts(contacts);
                break;
            case "exit":
                break;
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("ПЛАНИРОВЩИК ЗАДАЧ");

        Journal journal = new Journal();
        journal.loadTasks();

        Scanner in = new Scanner(System.in);
        String s;

        System.out.println("\nНажмите 1, чтобы посмотреть список имеющихся задач.");
        System.out.println("Нажмите 2, чтобы создать новую задачу.");
        System.out.println("Нажмите 3, чтобы редактировать имеющуюся задачу.");
        System.out.println("Нажмите 4, чтобы удалить имеющуюся задачу.");
        System.out.println("Наберите exit, чтобы закрыть программу.");

        while (true) {
            s = in.nextLine();
            switch (s) {
                case "1":
                    showJournal(journal);
                    break;
                case "2":
                    System.out.print("Введите название задачи: ");
                    String name = in.nextLine();
                    System.out.print("Введите описание задачи: ");
                    String description = in.nextLine();
                    System.out.println("Введите дату оповещения: ");
                    Date date = new Date(); // Добавить установку даты оповещения.
                    System.out.print("Введите контакты:");
                    String contacts = in.nextLine();
                    Task task = new Task(name, description, contacts, date);
                    journal.addTask(task);
                    journal.saveTasks();
                    break;
                case "3":
                    System.out.print("Введите номер задачи для редактирования: ");
                    Task task1;
                    int id = in.nextInt();

                    task1 = (Task) journal.getTasks().get(id);

                    System.out.println("Нажмите 1, чтобы редактировать название задачи.");
                    System.out.println("Нажмите 2, чтобы редактировать описание задачи.");
                    System.out.println("Нажмите 3, чтобы редактировать дату оповещения задачи.");
                    System.out.println("Нажмите 4, чтобы редактировать контакты.");
                    System.out.println("Наберите exit, чтобы отменить редактирование.");

                    Scanner scanner = new Scanner(System.in);
                    String a = scanner.nextLine();
                    edit(scanner, a, task1);
                    journal.editTask(id, task1);
                    break;
                case "4":
                    System.out.print("Введите номер задачи: ");
                    int idTask = in.nextInt();
                    journal.deleteTask(idTask);
                    break;
                case "exit":
                    journal.saveTasks();
                    System.exit(0);
                    break;
            }
                System.out.println("\nНажмите 1, чтобы посмотреть список имеющихся задач.");
                System.out.println("Нажмите 2, чтобы создать новую задачу.");
                System.out.println("Нажмите 3, чтобы редактировать имеющуюся задачу.");
                System.out.println("Нажмите 4, чтобы удалить имеющуюся задачу.");
                System.out.println("Наберите exit, чтобы закрыть программу.");
        }
    }
}
