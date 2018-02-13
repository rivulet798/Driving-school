package entity.ext;

import entity.AbstractEntity;

public class Student extends AbstractEntity{
    private int idStudent;
    private String login;
    private String password;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String phone;
    private int groupNumber;

    public Student() {
    }

    public Student(int idStudent, String login, String password, String lastName, String firstName, String patronymic, String phone, int groupNumber) {
        this.idStudent = idStudent;
        this.login = login;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.phone = phone;
        this.groupNumber = groupNumber;
    }

    public Student(String login, String password, String lastName, String firstName, String patronymic,String phone,int groupNumber) {
        this.login = login;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.phone = phone;
        this.groupNumber = groupNumber;
    }

    public Student(int idStudent) {
        this.idStudent = idStudent;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
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

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "idStudent=" + idStudent +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phone='" + patronymic + '\'' +
                ", groupNumber=" + groupNumber +
                '}';
    }

    public int getId() {
        return idStudent;
    }
}
