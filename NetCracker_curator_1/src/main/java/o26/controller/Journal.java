package o26.controller;

import o26.model.ITask;
import o26.model.Parameter;
import o26.model.IUser;
import o26.model.User;
import o26.view.MainMenuItem;
import o26.view.AbstractMenuItem;

import java.util.List;
import java.util.ArrayList;

public class Journal {
    private List<ITask> tasks;
    private Loader loader;
    private ITaskCreator taskCreator;
    private AbstractMenuItem view;
    private INotification notification;
    private IUser user;
    private IUserData userData;
    private ListID listID;
    
    public void addTask(List<Parameter> parameters, List<ITask> tasks) {
        listID.addID(parameters);
        tasks.add(taskCreator.createTask(parameters));
    }

    public void deleteTask(int id) {
        Integer taskID = (Integer) tasks.get(id).getValue(Parameter.TypeParameter.ID);
        if (taskID != null) {
            listID.removeID((Integer) tasks.get(id).getValue(Parameter.TypeParameter.ID));
        }
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
        List<Integer> idList = new ArrayList<>();
        for(ITask task: tasks) {
            idList.add((Integer) task.getValue(Parameter.TypeParameter.ID));
        }
        loader.saveData(tasks, user.getLogin(), idList);
    }

    @SuppressWarnings("unchecked")
    public void load() {
        tasks = loader.loadData(user.getLogin());
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

    public void setView(AbstractMenuItem view) {
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
        return password.equals(password1);
    }

    public boolean registration(String login, String password) {
        return this.userData.userRegistration(login, password);
    }
    
    public boolean checkLogin(String login) {
        return !this.userData.checkLogin(login);
    }
    
    public void login(String login, String password) {
        if (checkLogin(login) && this.userData.checkPassword(login, password)) {
            this.setUser(new User(login, password, new ArrayList<>()));
            this.setView(new MainMenuItem());
            listID = ListID.getInstance();
            tasks = null;
            this.load();
            this.notificationStart();
            this.showMenu();
        }
    }
}