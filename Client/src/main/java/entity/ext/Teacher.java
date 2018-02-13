package entity.ext;

import entity.AbstractEntity;

public class Teacher extends AbstractEntity{
    private int idTeacher;
    private String login;
    private String password;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String phone;
    private int experience;

    public Teacher() {
    }

    public Teacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    public Teacher(int idTeacher, String login, String password, String lastName, String firstName, String patronymic, String phone, int experience) {
        this.idTeacher = idTeacher;
        this.login = login;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.phone = phone;
        this.experience = experience;
    }

    public Teacher(String login, String password, String lastName,String firstName,String patronymic, String phone, int experience) {
        this.login = login;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.phone = phone;
        this.experience = experience;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "idTeacher=" + idTeacher +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phone='" + phone + '\'' +
                ", experience=" + experience +
                '}';
    }

    public int getId() {
        return idTeacher;
    }
}
