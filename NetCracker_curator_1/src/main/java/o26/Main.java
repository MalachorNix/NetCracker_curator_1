package o26;

import o26.Controller.Journal;
import o26.Model.Task;
import o26.Model.TaskParameters;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void showTask(Task task){
        String name = (String) task.getValue(TaskParameters.NAME);
        String description = (String) task.getValue(TaskParameters.DESCRIPTION);
        String contacts = (String) task.getValue(TaskParameters.CONTACTS);
        GregorianCalendar date = (GregorianCalendar) task.getValue(TaskParameters.DATE);
        String result = "Name:\n\t"+name+"\n";
        result += "Description:\n\t"+description+"\n";
        result += "Contacts:\n\t"+contacts+"\n";
        result += "Date:\n\t"+date+"\n";
        System.out.print(result);
    }

    public static void showJournal(Journal journal){
        int countTask = journal.getTasks().size();
        if (countTask != 0) {
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
        } else {
            System.out.println("Список задач пуст!");
        }
    }

    private static void edit(Scanner in, String concurrency, Task task) {
        switch (concurrency) {
            case "1":
                System.out.print("Введите другое название задачи: ");
                String name = in.nextLine();
                task.setValue(TaskParameters.NAME, name);
                break;
            case "2":
                System.out.print("Введите другое описание задачи: ");
                String description = in.nextLine();
                task.setValue(TaskParameters.DESCRIPTION, description);
                break;
            case "3":
                System.out.println("Введите другую дату оповещения: "); //Добавить заполнение даты.
                System.out.print("Введите год: ");
                int year = in.nextInt();
                System.out.print("Введите месяц: ");
                int month = in.nextInt();
                System.out.print("Введите день: ");
                int day = in.nextInt();
                System.out.print("Введите час: ");
                int hour = in.nextInt();
                System.out.print("Введите минуту: ");
                int minute = in.nextInt();
                System.out.print("Введите секунду: ");
                int second= in.nextInt();
                GregorianCalendar newDate = new GregorianCalendar(year, month, day, hour, minute, second);
                task.setValue(TaskParameters.DATE, newDate);
                break;
            case "4":
                System.out.print("Введите другие контакты: ");
                String contacts = in.nextLine();
                task.setValue(TaskParameters.CONTACTS, contacts);
                break;
            case "exit":
                break;
        }

    }

    public static void main(String[] args) throws IOException {

        System.out.println("ПЛАНИРОВЩИК ЗАДАЧ");

        Journal journal = new Journal();
        journal.load();

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
                    Map<TaskParameters, Object> parameters = new HashMap<TaskParameters, Object>();

                    System.out.print("Введите название задачи: ");
                    String name = in.nextLine();
                    parameters.put(TaskParameters.NAME, name);

                    System.out.print("Введите описание задачи: ");
                    String description = in.nextLine();
                    parameters.put(TaskParameters.DESCRIPTION, description);

                    System.out.println("Введите дату оповещения: ");
                    System.out.print("Введите год: ");
                    int year = in.nextInt();
                    System.out.print("Введите месяц: ");
                    int month = in.nextInt();
                    System.out.print("Введите день: ");
                    int day = in.nextInt();
                    System.out.print("Введите час: ");
                    int hour = in.nextInt();
                    System.out.print("Введите минуту: ");
                    int minute = in.nextInt();
                    System.out.print("Введите секунду: ");
                    int second= in.nextInt();
                    GregorianCalendar date = new GregorianCalendar(year, month, day, hour, minute, second);
                    parameters.put(TaskParameters.DATE, date);

                    Scanner sc = new Scanner(System.in);

                    System.out.print("Введите контакты: ");
                    String contacts = sc.nextLine();
                    parameters.put(TaskParameters.CONTACTS, contacts);

                    journal.addTask(parameters);
                    journal.save();
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

                    Scanner anotherScanner = new Scanner(System.in);
                    String a = anotherScanner.nextLine();
                    edit(anotherScanner, a, task1);
                    journal.editTask(id, task1);
                    break;
                case "4":
                    System.out.print("Введите номер задачи: ");
                    int idTask = in.nextInt();
                    journal.deleteTask(idTask);
                    break;
                case "exit":
                    journal.save();
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