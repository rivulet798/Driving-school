package dao.ext;

import dao.AbstractDAO;
import entity.ext.Student;

import java.beans.Encoder;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDAO extends AbstractDAO<Student> {


    public static final String SQL_SELECT_ALL_STUDENTS = "SELECT * FROM drivingschool.student";
    public static final String SQL_SELECT_STUDENT_BY_ID = "SELECT * FROM drivingschool.student WHERE idStudent=";
    public static final String SQL_SELECT_STUDENT_BY_LOGIN = "SELECT * FROM drivingschool.student WHERE login=";
    public static final String SQL_SELECT_STUDENT_BY_GROUP_NUMBER = "SELECT * FROM drivingschool.student WHERE group_groupNumber=";
    public static final String SQL_DELETE_BY_LOGIN = "DELETE FROM drivingschool.student WHERE drivingschool.student.login=";
    public static final String SQL_DELETE_BY_ID = "DELETE FROM drivingschool.student WHERE drivingschool.student.idStudent=";
    public static final String SQL_INSERT_INTO = "INSERT INTO drivingschool.student (login,password,lastName,firstName,patronymic,phone,group_groupNumber)  VALUES(?,?,?,?,?,?,?)";
    public static final String SQL_CHECK_LOGIN_AND_ID = "SELECT * FROM drivingschool.student WHERE drivingSchool.student.idStudent=? OR drivingschool.student.login=?";
    public static final String SQL_CHECK_PHONE = "SELECT * FROM drivingschool.student WHERE drivingSchool.student.phone=?";
    public static final String SQL_UPDATE_BY_ID = "UPDATE drivingschool.student SET login=?,password=?,lastName=?,firstName=?,patronymic=?,phone=?,group_groupNumber=? WHERE drivingschool.student.idStudent=";
    public static final String SQL_CHECK_LOGIN_AND_PASSWORD= "SELECT * FROM drivingschool.student WHERE drivingschool.student.login=? AND drivingschool.student.password=?";
    public static final String SQL_CHECK_PHONE_AND_ID = "SELECT * FROM drivingschool.student WHERE drivingSchool.student.idStudent=? AND drivingschool.student.phone=?";

    public static final String SQL_INSERT_INTO_SHL = "INSERT INTO drivingschool.student_has_lesson (student_idStudent,lesson_id,status)  VALUES(?,?,?)";
    public static final String SQL_SELECT_LESSON_BY_ID_STUDENT = "SELECT drivingschool.lesson.lessonName, drivingschool.student_has_lesson.status FROM  drivingschool.student_has_lesson JOIN drivingschool.lesson ON drivingschool.lesson.id=drivingschool.student_has_lesson.lesson_id JOIN drivingschool.student ON drivingschool.student.idStudent=drivingschool.student_has_lesson.student_idStudent WHERE drivingschool.student.idStudent=?";

    public static final String SQL_SELECT_LESSON_ID_BY_NAME = "SELECT lesson.id FROM lesson WHERE lesson.lessonName=?";
    public static final String SQL_UPDATE_SHL_CHANGE_LESSON = "UPDATE student_has_lesson SET student_has_lesson.status=? WHERE student_has_lesson.student_idStudent=? AND student_has_lesson.lesson_id=?;";

    public StudentDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_STUDENTS);
            while (resultSet.next()) {
                Student student = new Student();
                student.setIdStudent(resultSet.getInt("idStudent"));
                student.setLogin(resultSet.getString("login"));
                student.setPassword(resultSet.getString("password"));
                student.setLastName(resultSet.getString("lastName"));
                student.setFirstName(resultSet.getString("firstName"));
                student.setPatronymic(resultSet.getString("patronymic"));
                student.setPhone(resultSet.getString("phone"));
                student.setGroupNumber(resultSet.getInt("group_groupNumber"));
                students.add(student);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return students;
    }

    public boolean findEntityByGroupNumber(int groupNumber) throws UnsupportedOperationException {
        Student student = null;
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_STUDENT_BY_GROUP_NUMBER + groupNumber);
            while (resultSet.next()) {
               return true;
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return false;
    }


    @Override
    public Student findEntityById(int id) throws UnsupportedOperationException {
        Student student = null;
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_STUDENT_BY_ID + id);
            while (resultSet.next()) {
                if (resultSet.getInt("idStudent") == id) {
                    student = new Student();
                    student.setIdStudent(resultSet.getInt("idStudent"));
                    student.setLogin(resultSet.getString("login"));
                    student.setPassword(resultSet.getString("password"));
                    student.setLastName(resultSet.getString("lastName"));
                    student.setFirstName(resultSet.getString("firstName"));
                    student.setPatronymic(resultSet.getString("patronymic"));
                    student.setPhone(resultSet.getString("phone"));
                    student.setGroupNumber(resultSet.getInt("group_groupNumber"));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return student;
    }

    @Override
    public Student findEntityByLogin(String login) {
        Student student = null;
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_STUDENT_BY_LOGIN + "'" + login + "'");
            while (resultSet.next()) {
                if (resultSet.getString("login").equals(login)) {
                    student =new Student();
                    student.setIdStudent(resultSet.getInt("idStudent"));
                    student.setLogin(resultSet.getString("login"));
                    student.setPassword(resultSet.getString("password"));
                    student.setLastName(resultSet.getString("lastName"));
                    student.setFirstName(resultSet.getString("firstName"));
                    student.setPatronymic(resultSet.getString("patronymic"));
                    student.setPhone(resultSet.getString("phone"));
                    student.setGroupNumber(resultSet.getInt("group_groupNumber"));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return student;
    }

    @Override
    public boolean delete(int id) throws UnsupportedOperationException{
        Statement st = null;
        int count = 0;
        try {
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_STUDENT_BY_ID + id);
            if (resultSet.next()) {
                st.executeUpdate(SQL_DELETE_BY_ID + id);
                return true;
            }

        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return false;
    }

    @Override
    public boolean delete(String login) {
        Statement st = null;
        int count = 0;
        int id = -1;
        try {
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_STUDENT_BY_LOGIN + "'" + login + "'");
            if (resultSet.next()) {
                st.executeUpdate(SQL_DELETE_BY_LOGIN + "'" + login + "'");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return false;
    }


    @Override
    public boolean delete(Student entity) {
        Statement st = null;
        int count = 0;
        try {
            int id = entity.getIdStudent();
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_STUDENT_BY_ID + id);
            if (resultSet.next()) {
                st.executeUpdate(SQL_DELETE_BY_ID + id);
                return true;
            }

        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return false;
    }

    @Override
    public Student create(Student entity) {
        PreparedStatement st = null;

        try {
            st = connection.prepareStatement(SQL_INSERT_INTO);
            st.setString(1, entity.getLogin());
            st.setString(2, entity.getPassword());
            st.setString(3, entity.getLastName());
            st.setString(4, entity.getFirstName());
            st.setString(5, entity.getPatronymic());
            st.setString(6,entity.getPhone());
            st.setInt(7,entity.getGroupNumber());

            st.executeUpdate();
            Student student = findEntityByLogin(entity.getLogin());

            PreparedStatement st2 = null;
            for(int i=1;i<11;i++){
                st2 = connection.prepareStatement(SQL_INSERT_INTO_SHL);
                st2.setInt(1,student.getIdStudent());
                st2.setInt(2,i);
                st2.setInt(3,0);
                st2.executeUpdate();
            }



            return student;
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return null;
    }

    @Override
    public boolean create(Student entity, int i) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_INSERT_INTO);
            st.setString(1, entity.getLogin());
            st.setString(2, entity.getPassword());
            st.setString(3, entity.getLastName());
            st.setString(4, entity.getFirstName());
            st.setString(5, entity.getPatronymic());
            st.setString(6,entity.getPhone());
            st.setInt(7,entity.getGroupNumber());

            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return false;
    }

    @Override
    public Student update(Student entity) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_UPDATE_BY_ID + entity.getIdStudent());
            st.setString(1, entity.getLogin());
            st.setString(2, entity.getPassword());
            st.setString(3,entity.getLastName());
            st.setString(4,entity.getFirstName());
            st.setString(5,entity.getPatronymic());
            st.setString(6,entity.getPhone());
            st.setInt(7,entity.getGroupNumber());

            st.executeUpdate();
            return entity;
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return null;
    }

    @Override
    public boolean checkLoginAndId(int id, String login) throws UnsupportedOperationException {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_CHECK_LOGIN_AND_ID);
            st.setInt(1, id);
            st.setString(2, login);
            ResultSet resultSet = st.executeQuery();
            if (!resultSet.next())
                return true;
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return false;
    }

    @Override
    public boolean checkPhoneAndId(int id, String phone) throws UnsupportedOperationException {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_CHECK_PHONE_AND_ID);
            st.setInt(1, id);
            st.setString(2, phone);
            ResultSet resultSet = st.executeQuery();
            if (!resultSet.next())
                return true;
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return false;
    }

    @Override
    public boolean checkPhone(String phone) throws UnsupportedOperationException {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_CHECK_PHONE);
            st.setString(1, phone);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next())
                return false;
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return true;
    }

    @Override
    public boolean checkLoginAndPassword(String login,String password) throws UnsupportedOperationException {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_CHECK_LOGIN_AND_PASSWORD);
            st.setString(1, login);
            st.setString(2, password);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next())
                return true;
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return false;
    }

    @Override
    public List<Student> findByIdTeacher(int idTeacher) throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Student> findByGroupNumber(int groupNumber) {
        List<Student> students = new ArrayList<>();
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_STUDENT_BY_GROUP_NUMBER + groupNumber);
            while (resultSet.next()) {
                Student student = new Student();
                student.setIdStudent(resultSet.getInt("idStudent"));
                student.setLogin(resultSet.getString("login"));
                student.setPassword(resultSet.getString("password"));
                student.setFirstName(resultSet.getString("firstName"));
                student.setLastName(resultSet.getString("lastName"));
                student.setPatronymic(resultSet.getString("patronymic"));
                student.setPhone(resultSet.getString("phone"));
                students.add(student);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return students;
    }

    @Override
    public Map<String,Integer> findLessons(int idStudent) {
        Map<String, Integer> lessons = new HashMap<>();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_SELECT_LESSON_BY_ID_STUDENT);
            st.setInt(1,idStudent);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                lessons.put(resultSet.getString("lessonName"),resultSet.getInt("status"));
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return lessons;
    }

    @Override
    public boolean changeLessons(int idStudent, String name, int status) {
        PreparedStatement st = null;
        PreparedStatement st2 = null;
        int idLesson=0;
        try {
            st = connection.prepareStatement(SQL_SELECT_LESSON_ID_BY_NAME);
            st.setString(1, new String(name.getBytes("UTF-8")));
            ResultSet resultSet = st.executeQuery();
            if(resultSet.next()) {
                idLesson = resultSet.getInt(1);
            }

            st2 = connection.prepareStatement(SQL_UPDATE_SHL_CHANGE_LESSON );
            st2.setInt(1,status);
            st2.setInt(2,idStudent);
            st2.setInt(3, idLesson);
            int res = st2.executeUpdate();
            if(idLesson>0 && res>0){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            close(st);
            close(st2);
        }
        return false;
    }

    @Override
    public List<Integer> findPastTest(){
        return null;
    }

}