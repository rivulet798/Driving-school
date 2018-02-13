package message;

import entity.ext.Admin;
import entity.ext.Group;
import entity.ext.Student;
import entity.ext.Teacher;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class MyMessage implements Serializable {
    private final static long serialVersionUID = 1L;

    private Admin admin;
    private Teacher teacher;
    private Student student;
    private Group group;
    private MessageType messageType;
    private String login;
    private String password;
    private List<Admin> admins;
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Group> groups;
    private Map<String,Integer> lessons;
    private List<Integer> pastTest;

    public MyMessage() {
    }

    public List<Integer> getPastTest() {
        return pastTest;
    }

    public void setPastTest(List<Integer> pastTest) {
        this.pastTest = pastTest;
    }

    public MyMessage(Map<String, Integer> lessons) {
        this.lessons = lessons;
    }

    public MyMessage(List<Admin> admins, Admin admin) {
        this.admins = admins;
    }

    public MyMessage(List<Student> students, Student student) {
        this.students = students;
    }

    public MyMessage(List<Teacher> teachers, Teacher teacher) {
        this.teachers = teachers;
    }

    public MyMessage(List<Group> groups, Group group) {
        this.groups = groups;
    }

    public MyMessage(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public MyMessage(MessageType messageType) {
        this.messageType = messageType;
    }

    public MyMessage(Teacher teacher) {

        this.teacher = teacher;
    }

    public MyMessage(Admin admin) {

        this.admin = admin;
    }

    public MyMessage(Student student) {
        this.student = student;
    }

    public MyMessage(Group group) {
        this.group = group;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
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

    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Map<String, Integer> getLessons() {
        return lessons;
    }

    public void setLessons(Map<String, Integer> lessons) {
        this.lessons = lessons;
    }
}
