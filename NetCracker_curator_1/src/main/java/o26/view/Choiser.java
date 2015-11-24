package o26.view;

import java.util.Scanner;

public class Choiser {
    public int doIt(int min, int max){
        int choice;
        try{
            boolean notValid;
            do{
                System.out.print("Выберите пункт (от "+min+" до "+max+"): ");
                choice = Integer.parseInt(new Scanner(System.in).nextLine());
                notValid = min > choice || choice > max;
                if(notValid) System.out.println("Данного пункта нет! Попробуйте снова!");
            }while(notValid);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            choice = -1;
        }
        System.out.println();
        return choice;
    }
}
