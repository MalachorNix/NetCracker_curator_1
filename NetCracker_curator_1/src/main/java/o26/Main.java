package o26;

import o26.controller.*;

public class Main {

    public static void main(String[] args){
        Loader dataLoader = new DataLoader();
        TaskCreator taskCreator = new TaskCreator();
        Notification notification = new Notification();
        Journal journal = new Journal();

        journal.setLoader(dataLoader);
        journal.setTaskCreator(taskCreator);
        journal.setNotification(notification);

        journal.showMenu();
    }
}