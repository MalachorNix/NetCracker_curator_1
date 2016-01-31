package o26.model;

import java.util.List;


public class User implements IUser {

    private String login;
    private String password;
    private List<Integer> idList;

    public User(String login, String password, List<Integer> idList) {
        this.login = login;
        this.password = password;
        this.idList = idList;
    }
    
    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }
    
    @Override
    public List<Integer> getId() {
        return idList;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public void addId(Integer id) {
        this.idList.add(id);
    }
}