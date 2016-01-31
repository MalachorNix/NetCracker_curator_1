package o26.controller;

import o26.model.ITask;

import java.util.List;
import java.util.Map;

public interface Loader {

    List loadData();

    void saveData(List<ITask> tasks, Map<String, Integer> idList);
}
