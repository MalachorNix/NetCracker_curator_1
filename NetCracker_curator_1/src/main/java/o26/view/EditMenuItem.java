package o26.view;

import o26.controller.Journal;
import o26.model.Parameter;

import java.util.List;

public class EditMenuItem extends AbstractMenuItem {
    private static final String ITEM = "Редактирование задач";
    
    @Override
    public void show(Journal journal) {
        int selectedId;
        do{
            System.out.println("Редактирование задачи");
            System.out.println("\t<Id> Редактировать задачу с индексом Id");
            System.out.println("\t<0> Вернуться в меню");
            int countTasks = journal.getTasks().size();
            selectedId = choice(0, countTasks);
            switch(selectedId){
                case 0:{
                    break;
                }
                default:{
                    System.out.println(journal.getTasks().get(selectedId-1).toString());
                    System.out.println("Данную задачу вы хотите редактировать?");
                    System.out.println("\t<1> Да");
                    System.out.println("\t<0> Нет");
                    if(choice(0, 1)!=0){
                        journal.editTask(selectedId-1,
                                editParameters((journal.getTasks().get(selectedId-1)).getParameters())
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
        return ITEM;
    }
    
    private List<Parameter> editParameters(List<Parameter> parameters){
        int select;
        int count = parameters.size();
        do{
            System.out.println("Редактирование параметров");
            for(int i = 0; i < count; i++){
                System.out.println("\t<"+(i+1)+"> "+ parameters.get(i).getType().toString());
            }
            System.out.println("\t<0> Сохранить изменения и вернуться к выбору задачи");
            select = choice(0, count);
            Parameter parameter = parameters.get(select-1);
            parameter.setValue(parameter.getType(), in(parameter.getType()));
            if(select!=0){
                System.out.println("Параметр изменен!\n");
            }
        }while(select!=0);
        return parameters;
    }
}