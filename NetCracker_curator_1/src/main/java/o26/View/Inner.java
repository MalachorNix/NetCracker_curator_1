package o26.View;

import java.util.GregorianCalendar;
import java.util.Scanner;
import o26.Model.TaskParameters;

public class Inner {
    public Object doIt(TaskParameters parameter){
        String nameParameter = parameter.toString();
        System.out.println("Введите "+nameParameter+":\n");
        switch(nameParameter){
            case "DATE":{
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
                    int second= in.nextInt();
                    return new GregorianCalendar(year, month-1, day, hour, minute, second);
            }
            default:{
                return new Scanner(System.in).nextLine();
            }
        }
    }
}
