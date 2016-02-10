package o26.view;

import java.util.ArrayList;
import java.util.List;
import o26.controller.Journal;
import o26.model.Parameter;

public class AddMenuItem extends AbstractMenuItem {
    private static final String ITEM = "Добавление задач";
    
    @Override
    public void show(Journal journal) {
        List<Parameter> parameters = new ArrayList<>();
        Object name =  inputParameter(Parameter.TypeParameter.NAME);
        Object description = inputParameter(Parameter.TypeParameter.DESCRIPTION);
        Object contacts = inputParameter(Parameter.TypeParameter.CONTACTS);
        Object date = inputParameter(Parameter.TypeParameter.DATE);
      
        parameters.add(new Parameter(Parameter.TypeParameter.NAME, name));
        parameters.add(new Parameter(Parameter.TypeParameter.DESCRIPTION, description));
        parameters.add(new Parameter(Parameter.TypeParameter.CONTACTS, contacts));
        parameters.add(new Parameter(Parameter.TypeParameter.DATE, date));
        parameters.add(new Parameter(Parameter.TypeParameter.ID, 0));
        journal.addTask(parameters, journal.getTasks());
        System.out.println("Задача добавлена!\n");
        journal.journalChanged();
    }
    
    @Override
    public String toString(){
        return ITEM;
    }
}