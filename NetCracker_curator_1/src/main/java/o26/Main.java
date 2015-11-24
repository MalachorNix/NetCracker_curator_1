package o26;

import o26.controller.*;
import o26.view.MenuMenuItem;

public final class Main {

    /*
    * Сделал приватный конструктор и финальный класс, чтобы не ругался PMD.
    * */

    private Main() {

    }

    public static void main(String[] args){
        Loader dataLoader = new DataLoader();
        TaskCreator taskCreator = new TaskCreator();
        Notification notification = new Notification();
        MenuMenuItem menuItem = new MenuMenuItem();
        Journal journal = new Journal();

        journal.setLoader(dataLoader);
        journal.setTaskCreator(taskCreator);
        journal.setNotification(notification);
        journal.setView(menuItem);

        journal.showMenu();
    }
}