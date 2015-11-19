package o26.View;

import o26.Controller.Journal;

public class DeleteViewer implements Viewer{
    private Choiser choise;
    private final String POINT = "Удаление задач";
    
    @Override
    public void show(Journal journal) {
        int selectedId;
        do{
            System.out.println("Удаление задачи");
            System.out.println("\t<Id> Удалить задачу с индексом Id");
            System.out.println("\t<0> Вернуться в меню");
            int countTasks = journal.getTasks().size();
            choise = new Choiser();
            selectedId = choise.doIt(0, countTasks);
            switch(selectedId){
                case 0:{
                    break;
                }
                default:{
                    System.out.println(journal.getTasks().get(selectedId-1).toString());
                    System.out.println("Вы уверены, что хотите удалить эту задачу?");
                    System.out.println("\t<1> Да");
                    System.out.println("\t<0> Нет");
                    if(choise.doIt(0, 1)!=0){
                        journal.deleteTask(selectedId-1);
                        System.out.println("Задача удалена!\n");
                    }
                    break;
                }
            }
        }while(selectedId!=0);
    }
    
    @Override
    public String toString(){
        return this.POINT;
    }
}