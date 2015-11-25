package o26.view;

import java.util.GregorianCalendar;
import java.util.Scanner;
import o26.model.TaskParameter;

public class Inner {

    public Object doIt(TaskParameter parameter){

        String nameParameter = parameter.toString();
        Scanner text = new Scanner(System.in);

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

                while (name.length() == 0) {
                    System.out.println("Пустое название запрещено.");
                    System.out.println("Введите " + nameParameter + ":\n");
                    name = text.nextLine();
                }

                return name;
            }

            case "DESCRIPTION": {

                String description = text.nextLine();

                while (description.length() == 0) {
                    System.out.println("Пустое описание запрещено.");
                    System.out.println("Введите " + nameParameter + ":\n");
                    description = text.nextLine();
                }

                return description;
            }

            case "CONTACTS": {

                String contacts = text.nextLine();

                while (contacts.length() == 0) {
                    System.out.println("Пустое поле контактов запрещено.");
                    System.out.println("Введите "+nameParameter+":\n");
                    contacts = text.nextLine();
                }

                return contacts;
            }
            default:
                return text.nextLine();
        }
    }
}
