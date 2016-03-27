package o26.server;

import o26.controller.*;
import o26.model.ITask;
import o26.model.Parameter;
import o26.view.AbstractMenuItem;
import o26.view.NotificationViewer;
import o26.view.UserItem;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] ar) {
        int port = 6666;
        try {
            ServerSocket ss = new ServerSocket(port);
            Socket socket = ss.accept();
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            ObjectInputStream in = new ObjectInputStream(sin);
            ObjectOutputStream out = new ObjectOutputStream(sout);

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
            journal.setSocket(socket);

            int choice;

            do {
                choice = in.readInt();

                switch (choice) {
                    //Вход пользователя
                    case 1:
                        String login = in.readUTF();
                        String password = in.readUTF();
                        out.writeBoolean(journal.login(login, password));
                        out.flush();
                        break;
                    // Получение имени вошедшего пользователя
                    case 2:
                        out.writeUTF(journal.getUserLogin());
                        out.flush();
                        break;
                    // Сохранение списка задач
                    case 3:
                        journal.save();
                        break;
                    // Проверка пароля
                    case 4:
                        password = in.readUTF();
                        String password1 = in.readUTF();
                        boolean check = journal.validatePasswords(password, password1);
                        out.writeBoolean(check);
                        out.flush();
                        break;
                    //Регистрация
                    case 5:
                        login = in.readUTF();
                        password = in.readUTF();
                        boolean success = journal.registration(login, password);
                        out.writeBoolean(success);
                        out.flush();
                        break;
                    //Передача параметров задач
                    case 6:
                        List<ITask> tasks = (ArrayList) journal.getTasks();
                        out.writeInt(tasks.size());
                        out.flush();
                        for (int i = 0; i < tasks.size(); i++) {
                            out.writeUTF(tasks.get(i).toString());
                            out.flush();
                        }
                        break;
                    // Добавление задач в список
                    case 7:
                        List<Parameter> parameters = (ArrayList) in.readObject();
                        journal.addTask(parameters, journal.getTasks());
                        break;
                    // Оповещение об изменение журнала
                    case 8:
                        journal.journalChanged();
                        break;
                    // Выход
                    case 0:
                        break;
                }
            } while (choice != 0);
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}