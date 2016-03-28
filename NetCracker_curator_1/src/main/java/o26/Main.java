package o26;

import o26.controller.*;
import o26.view.AbstractMenuItem;
import o26.view.NotificationViewer;
import o26.view.UserItem;

public final class Main {

    private Main() {

    }

    public static void main(String[] args) {
        Loader dataLoader = new DataLoader();
        ITaskCreator taskCreator = new TaskCreator();
        INotification notification = new Notification();
        NotificationViewer notificationViewer = new NotificationViewer();
        notification.setNotificationViewer(notificationViewer);
        AbstractMenuItem userItem = new UserItem(); //Итем входа пользователя
        IUserData userData = new UserData();
        Journal journal = new Journal();

        journal.setLoader(dataLoader);
        journal.setTaskCreator(taskCreator);
        journal.setNotification(notification);
        journal.setView(userItem);
        journal.setUserData(userData);

//        journal.showMenu();
    }
}