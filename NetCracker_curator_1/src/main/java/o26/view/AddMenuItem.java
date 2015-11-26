package o26.view;

import java.util.ArrayList;
import java.util.List;
import o26.controller.Journal;
import o26.model.Parameter;

public class AddMenuItem extends MenuItem {
    private final String ITEM = "Добавление задач";
    
    @Override
    public void show(Journal journal) {
        List parameters = new ArrayList<>();
        parameters.add(new Parameter(Parameter.TypeParameter.NAME, in(Parameter.TypeParameter.NAME)));
        parameters.add(new Parameter(Parameter.TypeParameter.DESCRIPTION, in(Parameter.TypeParameter.DESCRIPTION)));
        parameters.add(new Parameter(Parameter.TypeParameter.CONTACTS, in(Parameter.TypeParameter.CONTACTS)));
        parameters.add(new Parameter(Parameter.TypeParameter.DATE, in(Parameter.TypeParameter.DATE)));
        journal.addTask(parameters);
        System.out.println("Задача добавлена!\n");
        journal.journalChanged();
    }
    
    @Override
    public String toString(){
        return this.ITEM;
    }
}