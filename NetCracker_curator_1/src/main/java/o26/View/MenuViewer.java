package o26.View;

import o26.Controller.Journal;

public class MenuViewer implements Viewer{
    private Viewer list, add, edit, delete;
    private Choiser choise;

    @Override
    public void show(Journal journal) {
        journal.load();
        int select;
        System.out.println("Планировщик задач");
        do{
            showCases();
            choise = new Choiser();
            select = choise.doIt(0, 4);
            switch(select){
                case 0:{
                    journal.save();
                    System.out.println("Goodbay!");
                    break;
                }
                case 1:{
                    list = new ListViewer();
                    list.show(journal);
                    break;
                }
                case 2:{
                    add = new AddViewer();
                    add.show(journal);
                    break;
                }
                case 3:{
                    edit = new EditViewer();
                    edit.show(journal);
                    break;
                }
                case 4:{
                    delete = new DeleteViewer();
                    delete.show(journal);
                    break;
                }
                default:{
                    journal.save();
                    break;
                }
            }
        }while(select!=0);
    }
    
    private void showCases(){
        String result = "Меню:\n";
        result += "\t<1> Показать все задачи\n";
        result += "\t<2> Добавить задачу\n";
        result += "\t<3> Редактировать задачу\n";
        result += "\t<4> Удалить задачу\n";
        result += "\t<0> Выход\n";
        System.out.print(result);
    }
}