package o26.view;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import o26.controller.Journal;
import o26.model.Parameter;

public abstract class MenuItem {
    public abstract void show(Journal journal);
    protected int choice(int min, int max){
        int choice;
        try{
            boolean notValid;
            do{
                System.out.print("Выберите пункт (от " + min + " до " + max + "): ");
                choice = Integer.parseInt(new Scanner(System.in).nextLine());
                notValid = min > choice || choice > max;
                if(notValid) System.out.println("Данного пункта нет! Попробуйте снова!");
            }while(notValid);
        }
        catch(Exception e){
            choice = -1;
        }
        System.out.println();
        return choice;
    }
    protected Object in(Parameter.TypeParameter parameter){
        String nameParameter = parameter.toString();
        Scanner text = new Scanner(System.in);
        String output;
        Pattern p = Pattern.compile("^\\s+");
        Matcher m;
        System.out.println("Введите "+nameParameter+":\n");
        switch(nameParameter){
            case "время оповещения": {
                return getDate();
            }
            case "название задачи": {
                String name = text.nextLine();
                m = p.matcher(name);
                output = m.replaceFirst("");
                while (output.length() == 0) {
                    System.out.println("Пустое название запрещено.");
                    System.out.println("Введите " + nameParameter + ":\n");
                    name = text.nextLine();
                    m = p.matcher(name);
                    output = m.replaceFirst("");
                }
                return name;
            }
            case "описание задачи": {
                String description = text.nextLine();
                m = p.matcher(description);
                output = m.replaceFirst("");
                while (output.length() == 0) {
                    System.out.println("Пустое описание запрещено.");
                    System.out.println("Введите " + nameParameter + ":\n");
                    description = text.nextLine();
                    m = p.matcher(description);
                    output = m.replaceFirst("");
                }
                return description;
            }
            case "контакты": {
                String contacts = text.nextLine();
                p = Pattern.compile("^\\s+");
                m = p.matcher(contacts);
                output = m.replaceFirst("");
                while (output.length() == 0) {
                    System.out.println("Пустое поле контактов запрещено.");
                    System.out.println("Введите "+nameParameter+":\n");
                    contacts = text.nextLine();
                    m = p.matcher(contacts);
                    output = m.replaceFirst("");
                }
                return contacts;
            }
            default:
                return text.nextLine();
        }
    }
    private GregorianCalendar getDate() {
        try {
            System.out.println("Введите 1, если хотите установить свою дату оповещения.");
            System.out.println("Введите любое число, если хотите, чтобы мы напомнили через 30 минут.");

            GregorianCalendar calendar = null;
            Scanner in = new Scanner(System.in);
            int choice = in.nextInt();

            if (choice == 1) {
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
                int second = in.nextInt();
                calendar = new GregorianCalendar(year, month - 1, day, hour, minute, second);
            } else {
                calendar = new GregorianCalendar();
                calendar.add(Calendar.MINUTE, 30);
            }

            return calendar;

        } catch (Exception e) {
            System.out.println("Вводите только целые числа! Повторите ввод.");
            return getDate();
        }
    }
}
