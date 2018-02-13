package server;

import connector.DBConnector;
import dao.AbstractDAO;
import dao.ext.*;
import entity.AbstractEntity;
import entity.ext.Admin;
import entity.ext.Group;
import entity.ext.Student;
import entity.ext.Teacher;
import message.MessageType;
import message.MyMessage;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.net.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Business {
    private AbstractDAO dao;
    private Connection connection;

    public Business() {
        connection = DBConnector.getConnection();
    }

    private MyMessage checkLoginAndPhone(MyMessage myMessage, AbstractEntity entity , String login, String phone){

        dao = new AdminDAO(connection);
        if (dao.findEntityByLogin(login) == null) {
            dao = new StudentDAO(connection);
            if (dao.findEntityByLogin(login) == null) {
                if(dao.checkPhone(phone)) {
                    dao = new TeacherDAO(connection);
                    if (dao.findEntityByLogin(login) == null) {
                        if(dao.checkPhone(phone)){

                                myMessage.setMessageType(MessageType.SUCCESSFUL);
                                return myMessage;

                        }
                        myMessage.setMessageType(MessageType.SAME_PHONE_NUMBERS);
                        return myMessage;
                    }
                    myMessage.setMessageType(MessageType.SAME_LOGINS);
                    return myMessage;
                }
                myMessage.setMessageType(MessageType.SAME_PHONE_NUMBERS);
                return myMessage;
            }
            myMessage.setMessageType(MessageType.SAME_LOGINS);
            return myMessage;
        }
        myMessage.setMessageType(MessageType.SAME_LOGINS);
        return myMessage;
    }

    private MyMessage checkLoginPhoneGroupNumber(MyMessage myMessage, AbstractEntity entity , String login, String phone, int groupNumber){

        dao = new AdminDAO(connection);
        if (dao.findEntityByLogin(login) == null) {
            dao = new StudentDAO(connection);
            if (dao.findEntityByLogin(login) == null) {
                if(dao.checkPhone(phone)) {
                    dao = new TeacherDAO(connection);
                    if (dao.findEntityByLogin(login) == null) {
                        if(dao.checkPhone(phone)){
                            dao = new GroupDAO(connection);
                            if (dao.findEntityById(groupNumber) == null) {
                                myMessage.setMessageType(MessageType.WRONG_GROUP_NUMBER);
                                return myMessage;
                            }
                            else {
                                myMessage.setMessageType(MessageType.SUCCESSFUL);
                                return myMessage;
                            }
                        }
                        myMessage.setMessageType(MessageType.SAME_PHONE_NUMBERS);
                        return myMessage;
                    }
                    myMessage.setMessageType(MessageType.SAME_LOGINS);
                    return myMessage;
                }
                myMessage.setMessageType(MessageType.SAME_PHONE_NUMBERS);
                return myMessage;
            }
            myMessage.setMessageType(MessageType.SAME_LOGINS);
            return myMessage;
        }
        myMessage.setMessageType(MessageType.SAME_LOGINS);
        return myMessage;
    }

    private MyMessage checkIdTeacherGroupNumber(MyMessage myMessage, int idTeacher, int groupNumber){

        dao = new TeacherDAO(connection);
        if (dao.findEntityById(idTeacher) == null) {
            myMessage.setMessageType(MessageType.WRONG_ID_TEACHER);
            dao = new GroupDAO(connection);
            if (dao.findEntityById(groupNumber) != null) {
                myMessage.setMessageType(MessageType.WRONG_GROUP_NUMBER);
            }
        }
        else{
            myMessage.setMessageType(MessageType.SUCCESSFUL);
        }
        return myMessage;

    }

    private MyMessage checkIdTeacher(MyMessage myMessage, int idTeacher){

        dao = new TeacherDAO(connection);
        if (dao.findEntityById(idTeacher) == null) {
            myMessage.setMessageType(MessageType.WRONG_ID_TEACHER);
        }
        else{
            myMessage.setMessageType(MessageType.SUCCESSFUL);
        }
        return myMessage;
    }

    public MessageType AccRegistration(MyMessage myMessage) {

        Teacher teacher;
        teacher = myMessage.getTeacher();
        String login = teacher.getLogin();

        dao = new AdminDAO(connection);
        if (dao.findEntityByLogin(login) == null) {
            dao = new StudentDAO(connection);
            if (dao.findEntityByLogin(login) == null) {
                if(dao.checkPhone(teacher.getPhone())) {
                    dao = new TeacherDAO(connection);
                    if (dao.findEntityByLogin(login) == null) {
                        if(dao.checkPhone(teacher.getPhone())){
                        if (dao.create(teacher) != null) {
                            return MessageType.SUCCESSFUL;
                        }
                        }
                        return MessageType.SAME_PHONE_NUMBERS;
                    }
                    return MessageType.SAME_LOGINS;
                }
                return MessageType.SAME_PHONE_NUMBERS;
            }
            return MessageType.SAME_LOGINS;
        }
        return MessageType.SAME_LOGINS;
    }

    public MyMessage AddAdministrator(MyMessage myMessage) {
        Admin admin = myMessage.getAdmin();
        String login = admin.getLogin();
        myMessage = new MyMessage();

        dao = new StudentDAO(connection);
        if (dao.findEntityByLogin(login) == null) {
            dao = new TeacherDAO(connection);
            if (dao.findEntityByLogin(login) == null) {
                dao = new AdminDAO(connection);
                admin = (Admin)dao.create(admin);
                if (admin != null) {
                    myMessage.setAdmin(admin);
                    myMessage.setMessageType(MessageType.SUCCESSFUL);
                    return myMessage;
                }
            }
        }
        myMessage.setMessageType(MessageType.SAME_LOGINS);
        return myMessage;
    }

    public MyMessage AddTeacher(MyMessage myMessage) {
        Teacher teacher = new Teacher();
        teacher = myMessage.getTeacher();
        String login = teacher.getLogin();
        String phone = teacher.getPhone();
        myMessage = new MyMessage();
        myMessage = checkLoginAndPhone(myMessage, teacher, login, phone);
        if (myMessage.getMessageType() == MessageType.SUCCESSFUL) {
            dao = new TeacherDAO(connection);
            teacher = (Teacher)dao.create(teacher);
            if (teacher != null) {
                myMessage.setTeacher(teacher);
                myMessage.setMessageType(MessageType.SUCCESSFUL);
                return myMessage;
            }
        }
        return myMessage;
    }

    public MyMessage AddStudent(MyMessage myMessage) {
        Student student = new Student();
        student = myMessage.getStudent();
        String login = student.getLogin();
        String phone = student.getPhone();
        int groupNumber = student.getGroupNumber();
        myMessage = new MyMessage();
        myMessage = checkLoginPhoneGroupNumber(myMessage, student, login, phone,groupNumber);
        try {
            Thread.sleep(100);
        }catch(InterruptedException e){}
        if (myMessage.getMessageType() == MessageType.SUCCESSFUL) {
            dao = new StudentDAO(connection);
            student = (Student)dao.create(student);
            if (student != null) {
                myMessage.setStudent(student);
                myMessage.setMessageType(MessageType.SUCCESSFUL);
                return myMessage;
            }
        }
        return myMessage;
    }

    public MyMessage AddGroup(MyMessage myMessage) {
        Group group = myMessage.getGroup();
        int groupNumber = group.getGroupNumber();
        myMessage = new MyMessage();
        dao = new GroupDAO(connection);
        if(dao.findEntityById(groupNumber) != null){
            myMessage.setMessageType(MessageType.WRONG_GROUP_NUMBER);
        }
        else{
            group = (Group)dao.create(group);
            if (group != null) {
                myMessage.setGroup(group);
                myMessage.setMessageType(MessageType.SUCCESSFUL);
                return myMessage;
            }
        }
        return myMessage;
    }

    public MyMessage deleteAdmin(MyMessage myMessage){
        dao = new AdminDAO(connection);
        dao.delete(myMessage.getAdmin().getLogin());
        myMessage = new MyMessage(MessageType.SUCCESSFUL);
        return myMessage;
    }

    public MessageType deleteTeacher(MyMessage myMessage){
        dao = new TeacherDAO(connection);
        dao.delete(myMessage.getTeacher().getLogin());

        String loginTeacher = myMessage.getTeacher().getLogin();
        dao = new TeacherDAO(connection);
        Teacher teacher = (Teacher)dao.findEntityByLogin(loginTeacher);
        if(teacher!=null) {
            int idTeacher = teacher.getIdTeacher();

            dao = new GroupDAO(connection);
            if (dao.findByIdTeacher(idTeacher) != null) {
                myMessage.setMessageType(MessageType.TEACHER_HAS_GROUPS);
                return MessageType.TEACHER_HAS_GROUPS;
            }
        }
        else{
            dao = new TeacherDAO(connection);
            dao.delete(myMessage.getTeacher().getLogin());
            myMessage.setMessageType(MessageType.SUCCESSFUL);
            return MessageType.SUCCESSFUL;
        }
        return MessageType.DEFAULT;
    }

    public MyMessage deleteStudent(MyMessage myMessage){
        dao = new StudentDAO(connection);
        dao.delete(myMessage.getStudent().getLogin());
        myMessage = new MyMessage(MessageType.SUCCESSFUL);
        return myMessage;
    }

    public MessageType deleteGroup(MyMessage myMessage){

        int groupNumber = myMessage.getGroup().getGroupNumber();
        StudentDAO studentDao = new StudentDAO(connection);
        if(studentDao.findEntityByGroupNumber(groupNumber)){
            return MessageType.GROUP_CONTAINS_STUDENTS;
        }
        else{
            dao = new GroupDAO(connection);
            dao.delete(groupNumber);
            return MessageType.SUCCESSFUL;
        }
    }

    public MyMessage authorization(MyMessage myMessage) {
        String login = myMessage.getLogin();
        String password = myMessage.getPassword();
        dao = new AdminDAO(connection);
        if(dao.findEntityByLogin(login)!=null && dao.checkLoginAndPassword(login,password)){
            myMessage.setMessageType(MessageType.ADMIN_AUTHORIZATION);
            myMessage.setAdmin((Admin) dao.findEntityByLogin(login));
            return myMessage;
        }
        dao = new StudentDAO(connection);
        if(dao.findEntityByLogin(login)!=null && dao.checkLoginAndPassword(login,password)){
            myMessage.setMessageType(MessageType.STUDENT_AUTHORIZATION);
            myMessage.setStudent((Student) dao.findEntityByLogin(login));
            return myMessage;
        }
        dao = new TeacherDAO(connection);
        if(dao.findEntityByLogin(login)!=null && dao.checkLoginAndPassword(login,password)){
            myMessage.setMessageType(MessageType.TEACHER_AUTHORIZATION);
            myMessage.setTeacher((Teacher) dao.findEntityByLogin(login));
            return myMessage;
        }
        myMessage.setMessageType(MessageType.FAILED_AUTHORIZATION);
        return myMessage;
    }

    public MyMessage allAdmins(MyMessage myMessage) {
        dao = new AdminDAO(connection);
        Admin admin = null;
        myMessage = new MyMessage(dao.findAll(), admin);
        return myMessage;
    }

    public MyMessage allStudents(MyMessage myMessage) {
        dao = new StudentDAO(connection);
        Student student = null;
        myMessage = new MyMessage(dao.findAll(), student);
        return myMessage;
    }

    public MyMessage allTeachers(MyMessage myMessage) {
        dao = new TeacherDAO(connection);
        Teacher teacher = null;
        myMessage = new MyMessage(dao.findAll(), teacher);
        return myMessage;
    }

    public MyMessage allGroups(MyMessage myMessage) {
        dao = new GroupDAO(connection);
        Group group = null;
        myMessage = new MyMessage(dao.findAll(), group);
        return myMessage;
    }

    public MyMessage redactAdministrators(MyMessage myMessage) {
        Admin admin = new Admin();
        admin = myMessage.getAdmin();
        String login = admin.getLogin();
        myMessage = new MyMessage();

        dao = new StudentDAO(connection);
        if (dao.findEntityByLogin(login) == null) {
            dao = new TeacherDAO(connection);
            if (dao.findEntityByLogin(login) == null) {
                dao = new AdminDAO(connection);
                admin = (Admin)dao.update(admin);
                if (admin != null) {
                    myMessage.setAdmin(admin);
                    myMessage.setMessageType(MessageType.SUCCESSFUL);
                    return myMessage;
                }
            }
        }
        myMessage.setMessageType(MessageType.SAME_LOGINS);
        return myMessage;
    }

    public MyMessage redactGroups(MyMessage myMessage) {
        Group group = new Group();
        group = myMessage.getGroup();
        int idTeacher = group.getIdTeacher();
        myMessage = new MyMessage();
        checkIdTeacher(myMessage,idTeacher);

        if (myMessage.getMessageType() == MessageType.SUCCESSFUL) {
            dao = new GroupDAO(connection);
            group = (Group)dao.update(group);
            if (group != null) {
                myMessage.setGroup(group);
                myMessage.setMessageType(MessageType.SUCCESSFUL);
                }
            }
        return myMessage;
    }

    public MyMessage redactStudents(MyMessage myMessage) {
        Student student = myMessage.getStudent();
        String ourphone = student.getPhone();
        myMessage = new MyMessage();

        dao = new StudentDAO(connection);
        if (!dao.checkPhoneAndId(student.getId(), student.getPhone())) {
            dao = new GroupDAO(connection);
            if(dao.findEntityById(student.getGroupNumber()) != null) {
                dao = new StudentDAO(connection);
                student = (Student) dao.update(student);
                if (student != null) {
                    myMessage.setMessageType(MessageType.REDACT_SUCCESS);
                    return myMessage;
                }
            }
            else myMessage.setMessageType(MessageType.WRONG_GROUP_NUMBER);
            return myMessage;
        }

        else {
            dao = new TeacherDAO(connection);
            if (dao.checkPhone(ourphone)) {
                dao = new StudentDAO(connection);
                if(dao.checkPhone(ourphone)) {
                    student = (Student) dao.update(student);
                    if (student != null) {
                        myMessage.setMessageType(MessageType.REDACT_SUCCESS);
                        return myMessage;
                    }
                }
            }
        }
        myMessage.setMessageType(MessageType.SAME_PHONE_NUMBERS);
        return myMessage;
    }

    public MyMessage redactTeachers(MyMessage myMessage) {
        Teacher teacher;
        teacher = myMessage.getTeacher();
        String ourphone = teacher.getPhone();
        myMessage = new MyMessage();

        dao = new TeacherDAO(connection);
        if (!dao.checkPhoneAndId(teacher.getId(), teacher.getPhone())) {
            teacher = (Teacher) dao.update(teacher);
            if (teacher != null) {
                myMessage.setMessageType(MessageType.REDACT_SUCCESS);
                return myMessage;
            }
        }

        else {
                dao = new StudentDAO(connection);
                if (dao.checkPhone(ourphone)) {
                    dao = new TeacherDAO(connection);
                    if(dao.checkPhone(ourphone)) {

                        teacher = (Teacher) dao.update(teacher);
                        if (teacher != null) {
                            myMessage.setMessageType(MessageType.REDACT_SUCCESS);
                            return myMessage;
                        }
                    }
                }
        }
        myMessage.setMessageType(MessageType.SAME_PHONE_NUMBERS);
        return myMessage;
    }

    public MyMessage redactTeacher(MyMessage myMessage) {
        Teacher teacher;
        teacher = myMessage.getTeacher();
        String login = teacher.getLogin();
        myMessage = new MyMessage();

        dao = new StudentDAO(connection);
        if (dao.findEntityByLogin(login) == null) {
            dao = new AdminDAO(connection);
            if (dao.findEntityByLogin(login) == null) {
                dao = new TeacherDAO(connection);
                teacher = (Teacher)dao.update(teacher);
                if (teacher != null) {
                    myMessage.setMessageType(MessageType.REDACT_SUCCESS);
                    return myMessage;
                }
            }
        }
        myMessage.setMessageType(MessageType.SAME_LOGINS);
        return myMessage;
    }


    public MyMessage redactStudent(MyMessage myMessage) {
        Student student;
        student = myMessage.getStudent();
        String login = student.getLogin();
        myMessage = new MyMessage();

        dao = new TeacherDAO(connection);
        if (dao.findEntityByLogin(login) == null) {
            dao = new AdminDAO(connection);
            if (dao.findEntityByLogin(login) == null) {
                dao = new StudentDAO(connection);
                student = (Student)dao.update(student);
                if (student != null) {
                    myMessage.setMessageType(MessageType.REDACT_SUCCESS);
                    return myMessage;
                }
            }
        }
        myMessage.setMessageType(MessageType.SAME_LOGINS);
        return myMessage;
    }

    public MyMessage groupsOfTeacher(MyMessage myMessage) {
        Teacher teacher = myMessage.getTeacher();
        int idTeacher = teacher.getIdTeacher();
        dao = new GroupDAO(connection);
        Group group = null;
        myMessage = new MyMessage(dao.findByIdTeacher(idTeacher),group);
        myMessage.setMessageType(MessageType.GROUPS_OF_TEACHER);
        return myMessage;
    }

    public MyMessage studentsOfGroup(MyMessage myMessage) {
        Group group = myMessage.getGroup();
        int groupNumber = group.getGroupNumber();
        dao = new StudentDAO(connection);
        Student student = null;
        myMessage = new MyMessage(dao.findByGroupNumber(groupNumber),student);
        myMessage.setMessageType(MessageType.STUDENTS_OF_GROUP);
        return myMessage;
    }



    public MyMessage lessonsOfStudent(MyMessage myMessage) {
        Student student = myMessage.getStudent();
        int idStudent = student.getIdStudent();
        dao = new StudentDAO(connection);
        myMessage = new MyMessage(dao.findLessons(idStudent));
        myMessage.setMessageType(MessageType.LESSONS_OF_STUDENT);
        return myMessage;
    }

    public MyMessage redactLessons(MyMessage myMessage) {
        int idStudent = myMessage.getStudent().getIdStudent();

        for(Map.Entry<String, Integer> lesson : myMessage.getLessons().entrySet()) {

            String name = lesson.getKey();
            int status = lesson.getValue();
            dao = new StudentDAO(connection);
            dao.changeLessons(idStudent, name, status);
        }

        myMessage = new MyMessage(dao.findLessons(idStudent));
        myMessage.setMessageType(MessageType.SUCCESSFUL);
        return myMessage;
    }

    public MyMessage groupOfStudent(MyMessage myMessage) {
        Student student = myMessage.getStudent();

        dao = new StudentDAO(connection);
        student = (Student)dao.findEntityById(student.getIdStudent());

        int groupNumber = student.getGroupNumber();
        dao = new GroupDAO(connection);
        Group group = (Group)dao.findEntityById(groupNumber);

        myMessage = new MyMessage();
        myMessage.setGroup(group);
        myMessage.setMessageType(MessageType.SUCCESSFUL);
        return myMessage;
    }

    public MyMessage teacherOfStudent(MyMessage myMessage) {
        Group group = myMessage.getGroup();

        dao = new GroupDAO(connection);
        group = (Group)dao.findEntityById(group.getGroupNumber());

        int idTeacher = group.getIdTeacher();

        dao = new TeacherDAO(connection);
        Teacher teacher = (Teacher)dao.findEntityById(idTeacher);

        myMessage = new MyMessage();
        myMessage.setTeacher(teacher);
        myMessage.setMessageType(MessageType.SUCCESSFUL);
        return myMessage;
    }

    public MyMessage pastTest(MyMessage myMessage) {
        dao = new LessonDAO(connection);
        List<Integer> array= dao.findPastTest();

        myMessage = new MyMessage();
        myMessage.setPastTest(array);
        myMessage.setMessageType(MessageType.SUCCESSFUL);
        return myMessage;
    }
}

