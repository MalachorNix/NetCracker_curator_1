package o26.view;

import java.util.Map;

import o26.controller.Journal;
import o26.model.Task;
import o26.model.TaskParameter;

public class EditMenuItem implements MenuItem {
    private Choiser choice;
    private Inner in;
    private final String ITEM = "Редактирование задач";
    
    @Override
    public void show(Journal journal) {
        int selectedId;
        do{
            System.out.println("Редактирование задачи");
            System.out.println("\t<Id> Редактировать задачу с индексом Id");
            System.out.println("\t<0> Вернуться в меню");
            int countTasks = journal.getTasks().size();
            choice = new Choiser();
            selectedId = choice.doIt(0, countTasks);
            switch(selectedId){
                case 0:{
                    break;
                }
                default:{
                    System.out.println(journal.getTasks().get(selectedId-1).toString());
                    System.out.println("Данную задачу вы хотите редактировать?");
                    System.out.println("\t<1> Да");
                    System.out.println("\t<0> Нет");
                    if(choice.doIt(0, 1)!=0){
                        journal.editTask(selectedId-1,
                                editParameters(((Task) journal.getTasks().get(selectedId-1)).getParameters())
                        );
                        System.out.println("Задача изменена!\n");
                        journal.journalChanged();
                    }
                    break;
                }
            }
        }while(selectedId!=0);
    }
    
    @Override
    public String toString(){
        return this.ITEM;
    }
    
    private Map<TaskParameter, Object> editParameters(Map<TaskParameter, Object> parameters){
        int select;
        do{
            System.out.println("Редактирование параметров");
            System.out.println("\t<1> "+ TaskParameter.NAME.toString());
            System.out.println("\t<2> "+ TaskParameter.DESCRIPTION.toString());
            System.out.println("\t<3> "+ TaskParameter.CONTACTS.toString());
            System.out.println("\t<4> "+ TaskParameter.DATE.toString());
            System.out.println("\t<0> Сохранить изменения и вернуться к выбору задачи");
            select = choice.doIt(0, 4);
            in = new Inner();
            switch(select){
                case 1:{
                    parameters.replace(TaskParameter.NAME, in.doIt(TaskParameter.NAME));
                    break;
                }
                case 2:{
                    parameters.replace(TaskParameter.DESCRIPTION, in.doIt(TaskParameter.DESCRIPTION));
                    break;
                }
                case 3:{
                    parameters.replace(TaskParameter.CONTACTS, in.doIt(TaskParameter.CONTACTS));
                    break;
                }
                case 4:{
                    parameters.replace(TaskParameter.DATE, in.doIt(TaskParameter.DATE));
                    break;
                }
                default:{
                    break;
                }
            }
            if(select!=0){
                System.out.println("Параметр изменен!\n");
            }
        }while(select!=0);
        return parameters;
    }
}