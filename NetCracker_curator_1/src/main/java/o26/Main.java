package o26;

import o26.controller.*;
import o26.view.MenuItem;
import o26.view.MenuMenuItem;

public final class Main {

    /*
    * Сделал приватный конструктор и финальный класс, чтобы не ругался PMD.
    * */

    private Main() {

    }

    public static void main(String[] args){
        Loader dataLoader = new DataLoader();
        ITaskCreator taskCreator = new TaskCreator();
        INotification notification = new Notification();
        MenuItem menuItem = new MenuMenuItem();
        Journal journal = new Journal();

        journal.setLoader(dataLoader);
        journal.setTaskCreator(taskCreator);
        journal.setNotification(notification);
        journal.setView(menuItem);

        journal.showMenu();
    }
}