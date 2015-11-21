package o26;

import o26.controller.DataLoader;
import o26.controller.Journal;
import o26.controller.TaskCreator;

public class Main {

    public static void main(String[] args){
        DataLoader dataLoader = new DataLoader();
        TaskCreator taskCreator = new TaskCreator();

        Journal journal = new Journal();
        journal.setDataLoader(dataLoader);
        journal.setTaskCreator(taskCreator);

        journal.showMenu();
    }
}