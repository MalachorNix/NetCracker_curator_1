package o26.controller;

import o26.view.NotificationViewer;

public interface INotification {
    
    void setActual(Journal journal);

    void setNotificationViewer(NotificationViewer notificationViewer);
    
    void start();
}
