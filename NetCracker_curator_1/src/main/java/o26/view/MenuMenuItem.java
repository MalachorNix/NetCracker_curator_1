package o26.view;

import java.util.ArrayList;

import o26.controller.Journal;

public class MenuMenuItem implements MenuItem {
    private ArrayList item;
    private Choiser choise;
    private final String ITEM = "Меню";
    
    @Override
    public void show(Journal journal) {
        journal.load();
        journal.notificationStart();
        item = new ArrayList<>();
        item.add(new ListMenuItem());
        item.add(new AddMenuItem());
        item.add(new EditMenuItem());
        item.add(new DeleteMenuItem());
        int select;
        System.out.println("Планировщик задач");
        do{
            int countPoints = item.size();
            for(int i = 0; i < countPoints; i++){
                System.out.println("<"+(i+1)+"> "+ item.get(i).toString());
            }
            System.out.println("<0> Сохранить изменения и выйти\n");
            choise = new Choiser();
            select = choise.doIt(0, countPoints);
            if(select!=0){
                try {
                    ((MenuItem) item.get(select-1)).show(journal);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Вводите только числа!");
                }
            }
            else{
                journal.save();
                System.out.println("Goodbye!\n");
                System.exit(0);
            }
        }while(select!=0);
    }
    
    @Override
    public String toString(){
        return this.ITEM;
    }
}