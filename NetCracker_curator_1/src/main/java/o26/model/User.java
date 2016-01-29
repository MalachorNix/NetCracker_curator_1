package o26.model;


public class User implements IUser {

    private String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
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
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}