package o26.model;

import java.util.List;

public interface IUser {

    String getLogin();

    String getPassword();
    
    List getId();

    void setLogin(String login);

    void setPassword(String password);
    
    void addId(Integer id);
}