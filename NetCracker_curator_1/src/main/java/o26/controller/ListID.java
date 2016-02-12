package o26.controller;

import o26.model.Parameter;

import java.util.List;

public final class ListID {

    private static ListID instance;

    private final List<Integer> listID;

    private ListID() {
        listID = new DataLoader().getListId();
    }

    public static ListID getInstance() {
        if (instance == null) {
            instance = new ListID();
        }
        return instance;
    }

    public void addID(List<Parameter> parameters) {
        int id;
        for (Parameter parameter : parameters) {
            if (parameter.getType().toString().equals(Parameter.TypeParameter.ID.toString())) {
                id = (int) parameter.getValue();
                if (!listID.isEmpty()) {
                    while (listID.contains(id)) {
                        id++;
                    }
                }
                listID.add(id);
                parameter.setValue(Parameter.TypeParameter.ID, id);
            }
        }
    }

    public void removeID(int id) {
        listID.remove(id);
    }
}