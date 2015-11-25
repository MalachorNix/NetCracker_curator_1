package o26.view;

import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import o26.model.TaskParameter;

public class Inner {

    public Object doIt(TaskParameter parameter){

        String nameParameter = parameter.toString();
        Scanner text = new Scanner(System.in);
        String output;
        Pattern p;
        Matcher m;

        System.out.println("Введите "+nameParameter+":\n");

        switch(nameParameter){

            case "DATE": {
                Scanner in = new Scanner(System.in);
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
                return new GregorianCalendar(year, month - 1, day, hour, minute, second);
            }

            case "NAME": {

                String name = text.nextLine();

                p = Pattern.compile("^\\s+");
                m = p.matcher(name);
                output = m.replaceFirst("");

                while (output.length() == 0) {
                    System.out.println("Пустое название запрещено.");
                    System.out.println("Введите " + nameParameter + ":\n");
                    name = text.nextLine();
                    p = Pattern.compile("^\\s+");
                    m = p.matcher(name);
                    output = m.replaceFirst("");
                }

                return name;
            }

            case "DESCRIPTION": {

                String description = text.nextLine();
                p = Pattern.compile("^\\s+");
                m = p.matcher(description);
                output = m.replaceFirst("");

                while (output.length() == 0) {
                    System.out.println("Пустое описание запрещено.");
                    System.out.println("Введите " + nameParameter + ":\n");
                    description = text.nextLine();
                    p = Pattern.compile("^\\s+");
                    m = p.matcher(description);
                    output = m.replaceFirst("");
                }

                return description;
            }

            case "CONTACTS": {

                String contacts = text.nextLine();
                p = Pattern.compile("^\\s+");
                m = p.matcher(contacts);
                output = m.replaceFirst("");

                while (output.length() == 0) {
                    System.out.println("Пустое поле контактов запрещено.");
                    System.out.println("Введите "+nameParameter+":\n");
                    contacts = text.nextLine();
                    p = Pattern.compile("^\\s+");
                    m = p.matcher(contacts);
                    output = m.replaceFirst("");
                }

                return contacts;
            }
            default:
                return text.nextLine();
        }
    }
}
