package o26.controller;

import o26.model.ITask;
import o26.model.Parameter;
import o26.model.IUser;
import o26.model.User;
import o26.view.MenuMenuItem;
import o26.view.MenuItem;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Journal {
    private List<ITask> tasks;
    private Loader loader;
    private ITaskCreator taskCreator;
    private MenuItem view;
    private INotification notification;
    private IUser user;
    private IUserData userData;
    
    public void addTask(List<Parameter> parameters, List<ITask> tasks) {
        if (taskCreator.validate(parameters, tasks)) {
            tasks.add(taskCreator.createTask(parameters));
        } else {
            System.out.println("Задачу создать нельзя!");
        }
    }

    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public void editTask(int id, List parameters) {
        for (Object parameter1 : parameters) {
            Parameter parameter = (Parameter) parameter1;
            tasks.get(id).setValue(parameter.getType(), parameter.getValue());
        }
    }

    public List<ITask> getTasks() {
        return this.tasks;
    }

    public void save() {
        Map<String, Integer> idList = new HashMap<>();
        for(ITask task: tasks) {
            idList.put(user.getLogin(), (Integer) task.getValue(Parameter.TypeParameter.ID));
        }
        loader.saveData(tasks, idList);
    }

    @SuppressWarnings("unchecked")
    public void load() {
        tasks = loader.loadData();
    }
    
    public void showMenu() {
        this.view.show(this);
    }

    public void notificationStart() {
        journalChanged();
        notification.start();
    }
    
    public void journalChanged() {
        notification.setActual(this);
    }
    
    public void setLoader(Loader loader){
        this.loader = loader;
    }

    public void setTaskCreator(ITaskCreator taskCreator) {
        this.taskCreator = taskCreator;
    }

    public void setNotification(INotification notification) {
        this.notification = notification;
    }

    public void setView(MenuItem view) {
        this.view = view;
    }

    public void setUser(IUser user) {
        this.user = user;
    }
    
    public String getUserLogin() {
        return user.getLogin();
    }

    public void setUserData(IUserData userData) {
        this.userData = userData;
    }

    public boolean validatePasswords(String password, String password1) {
        if (password.equals(password1)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean registration(String login, String password) {
        if (!this.userData.userRegistration(login, password)) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean checkLogin(String login) {
        if (!this.userData.checkLogin(login)) {
            return true;
        } else {
            return false;
        }
    }
    
    public void login(String login, String password) {
        if (checkLogin(login) && this.userData.checkPassword(login, password)) {
            user = new User(login, password, new ArrayList<Integer>());
            view = new MenuMenuItem();
            this.load();
            this.notificationStart();
            this.showMenu();
        }
    }
}