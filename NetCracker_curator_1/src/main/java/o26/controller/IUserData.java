package o26.controller;


public interface IUserData {

    boolean userRegistration(String login, String password);
    
    boolean checkLogin(String login);
    
    boolean checkPassword(String login, String password);
}