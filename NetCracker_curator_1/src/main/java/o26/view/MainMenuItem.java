package o26.view;

import java.util.ArrayList;
import java.util.List;

import o26.controller.Journal;

public class MainMenuItem extends AbstractMenuItem {
    private static final String ITEM = "Меню";
    
    @Override
    public void show(Journal journal) {
        List<AbstractMenuItem> item = new ArrayList<>();
        item.add(new ListMenuItem());
        item.add(new AddMenuItem());
        item.add(new EditMenuItem());
        item.add(new DeleteMenuItem());
        int select;
        System.out.println("\nДобро пожаловать, " + journal.getUserLogin() 
                + "\n");
        do{
            int countPoints = item.size();
            for(int i = 0; i < countPoints; i++){
                System.out.println("<"+(i+1)+"> "+ item.get(i).toString());
            }
            System.out.println("<0> Сохранить изменения и выйти\n");
            select = choice(0, countPoints);
            if(select == 0){
                journal.save();
                System.out.println("До свидания!\n");
            }
            else{
                try {
                    item.get(select-1).show(journal);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Вводите только числа!");
                }
            }
        }while(select!=0);
    }
    
    @Override
    public String toString(){
        return ITEM;
    }
}