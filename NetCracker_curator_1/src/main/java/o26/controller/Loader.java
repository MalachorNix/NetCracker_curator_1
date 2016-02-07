package o26.controller;

import o26.model.ITask;

import java.util.List;

public interface Loader {

    List loadData(String login);

    void saveData(List<ITask> tasks, String login, List<Integer> idList);
    
    List<Integer> getListId();
}
