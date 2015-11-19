package o26.View;

import java.util.Scanner;

public class Choiser {
    public int doIt(int min, int max){
        int choise;
        try{
            boolean notValid;
            do{
                System.out.print("Выберите пункт (от "+min+" до "+max+"): ");
                choise = Integer.parseInt(new Scanner(System.in).nextLine());
                notValid = min > choise || choise > max;
                if(notValid) System.out.println("Данного пункта нет! Попробуйте снова!");
            }while(notValid);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            choise = -1;
        }
        System.out.println();
        return choise;
    }
}
