package entity.ext;

import entity.AbstractEntity;

public class Admin extends AbstractEntity{
    private int idAdmin;
    private String login;
    private String password;

    public Admin() {
    }

    public Admin(int idAdmin, String login, String password) {
        this.idAdmin = idAdmin;
        this.login = login;
        this.password = password;
    }

    public Admin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Admin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public Admin(String login) {
        this.login = login;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "idAdmin=" + idAdmin +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getId() {
        return idAdmin;
    }
}
