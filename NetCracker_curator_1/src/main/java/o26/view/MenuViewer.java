package o26.view;

import java.util.ArrayList;

import o26.controller.Journal;

public class MenuViewer implements Viewer{
    private ArrayList points;
    private Choiser choise;
    private final String POINT = "Меню";
    
    @Override
    public void show(Journal journal) {
        journal.load();
        journal.notificationStart();
        points = new ArrayList<>();
        points.add(new ListViewer());
        points.add(new AddViewer());
        points.add(new EditViewer());
        points.add(new DeleteViewer());
        int select;
        System.out.println("Планировщик задач");
        do{
            int countPoints = points.size();
            for(int i = 0; i < countPoints; i++){
                System.out.println("<"+(i+1)+"> "+points.get(i).toString());
            }
            System.out.println("<0> Сохранить изменения и выйти\n");
            choise = new Choiser();
            select = choise.doIt(0, countPoints);
            if(select!=0){
                ((Viewer)points.get(select-1)).show(journal);
            }
            else{
                journal.save();
                System.out.println("Goodbay!\n");
                System.exit(0);
            }
        }while(select!=0);
    }
    
    @Override
    public String toString(){
        return this.POINT;
    }
}