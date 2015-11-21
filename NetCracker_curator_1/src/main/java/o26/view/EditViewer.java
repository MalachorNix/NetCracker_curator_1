package o26.view;

import java.util.Map;

import o26.controller.Journal;
import o26.model.Task;
import o26.model.TaskParameters;

public class EditViewer implements Viewer{
    private Choiser choise;
    private Inner in;
    private final String POINT = "Редактирование задач";
    
    @Override
    public void show(Journal journal) {
        int selectedId;
        do{
            System.out.println("Редактирование задачи");
            System.out.println("\t<Id> Редактировать задачу с индексом Id");
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
                    System.out.println("Данную задачу вы хотите редактировать?");
                    System.out.println("\t<1> Да");
                    System.out.println("\t<0> Нет");
                    if(choise.doIt(0, 1)!=0){
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
        return this.POINT;
    }
    
    private Map<TaskParameters, Object> editParameters(Map<TaskParameters, Object> parameters){
        int select;
        do{
            System.out.println("Редактирвоание параметров");
            System.out.println("\t<1> "+TaskParameters.NAME.toString());
            System.out.println("\t<2> "+TaskParameters.DESCRIPTION.toString());
            System.out.println("\t<3> "+TaskParameters.CONTACTS.toString());
            System.out.println("\t<4> "+TaskParameters.DATE.toString());
            System.out.println("\t<0> Сохранить изменения и вернуться к выбору задачи");
            select = choise.doIt(0, 4);
            in = new Inner();
            switch(select){
                case 1:{
                    parameters.replace(TaskParameters.NAME, in.doIt(TaskParameters.NAME));
                    break;
                }
                case 2:{
                    parameters.replace(TaskParameters.DESCRIPTION, in.doIt(TaskParameters.DESCRIPTION));
                    break;
                }
                case 3:{
                    parameters.replace(TaskParameters.CONTACTS, in.doIt(TaskParameters.CONTACTS));
                    break;
                }
                case 4:{
                    parameters.replace(TaskParameters.DATE, in.doIt(TaskParameters.DATE));
                    break;
                }
                default:{
                    break;
                }
            }
            if(select!=0){
                System.out.println("Параметер изменен!\n");
            }
        }while(select!=0);
        return parameters;
    }
}