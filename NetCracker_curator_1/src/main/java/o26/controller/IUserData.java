package o26.controller;


public interface IUserData {

    int userRegistration(String login, String password);
    
    int checkLogin(String login);
    
    int checkPassword(String login, String password);
}