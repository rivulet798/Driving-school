package dao.ext;

import dao.AbstractDAO;
import entity.ext.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TeacherDAO extends AbstractDAO<Teacher> {


    public static final String SQL_SELECT_ALL_TEACHERS = "SELECT * FROM drivingschool.teacher";
    public static final String SQL_SELECT_TEACHER_BY_ID = "SELECT * FROM drivingschool.teacher WHERE idTeacher=";
    public static final String SQL_SELECT_TEACHER_BY_LOGIN = "SELECT * FROM drivingschool.teacher WHERE login=";
    public static final String SQL_DELETE_BY_LOGIN = "DELETE FROM drivingschool.teacher WHERE drivingschool.teacher.login=";
    public static final String SQL_DELETE_BY_ID = "DELETE FROM drivingschool.teacher WHERE drivingschool.teacher.idTeacher=";
    public static final String SQL_INSERT_INTO = "INSERT INTO drivingschool.teacher (login,password,lastName,firstName,patronymic,phone,experience)  VALUES(?,?,?,?,?,?,?)";
    public static final String SQL_CHECK_LOGIN_AND_ID = "SELECT * FROM drivingschool.teacher WHERE drivingSchool.teacher.idTeacher=? OR drivingschool.teacher.login=?";
    public static final String SQL_CHECK_PHONE = "SELECT * FROM drivingschool.teacher WHERE drivingSchool.teacher.phone=?";
    public static final String SQL_UPDATE_BY_ID = "UPDATE drivingschool.teacher SET login=?,password=?,lastName=?,firstName=?,patronymic=?,phone=?,experience=? WHERE drivingschool.teacher.idTeacher=";
    public static final String SQL_CHECK_LOGIN_AND_PASSWORD= "SELECT * FROM drivingschool.teacher WHERE drivingschool.teacher.login=? AND drivingschool.teacher.password=?";
    public static final String SQL_CHECK_PHONE_AND_ID = "SELECT * FROM drivingschool.teacher WHERE drivingSchool.teacher.idTeacher=? AND drivingschool.teacher.phone=?";

    public TeacherDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Teacher> findAll() {
        List<Teacher> teachers = new ArrayList<>();
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_TEACHERS);
            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setIdTeacher(resultSet.getInt("idTeacher"));
                teacher.setLogin(resultSet.getString("login"));
                teacher.setPassword(resultSet.getString("password"));
                teacher.setLastName(resultSet.getString("lastName"))    ;
                teacher.setFirstName(resultSet.getString("firstName"));
                teacher.setPatronymic(resultSet.getString("patronymic"));
                teacher.setPhone(resultSet.getString("phone"));
                teacher.setExperience(resultSet.getInt("experience"));
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return teachers;
    }

    @Override
    public Teacher findEntityById(int id) throws UnsupportedOperationException {
        Teacher teacher = null;
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_TEACHER_BY_ID + id);
            while (resultSet.next()) {
                if (resultSet.getInt("idTeacher") == id) {
                    teacher = new Teacher();
                    teacher.setIdTeacher(resultSet.getInt("idTeacher"));
                    teacher.setLogin(resultSet.getString("login"));
                    teacher.setPassword(resultSet.getString("password"));
                    teacher.setLastName(resultSet.getString("lastName"));
                    teacher.setFirstName(resultSet.getString("firstName"));
                    teacher.setPatronymic(resultSet.getString("patronymic"));
                    teacher.setPhone(resultSet.getString("phone"));
                    teacher.setExperience(resultSet.getInt("experience"));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return teacher;
    }

    @Override
    public Teacher findEntityByLogin(String login) {
        Teacher teacher = null;
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_TEACHER_BY_LOGIN + "'" + login + "'");
            while (resultSet.next()) {
                if (resultSet.getString("login").equals(login)) {
                    teacher = new Teacher();
                    teacher.setIdTeacher(resultSet.getInt("idTeacher"));
                    teacher.setLogin(resultSet.getString("login"));
                    teacher.setPassword(resultSet.getString("password"));
                    teacher.setLastName(resultSet.getString("lastName"));
                    teacher.setFirstName(resultSet.getString("firstName"));
                    teacher.setPatronymic(resultSet.getString("patronymic"));
                    teacher.setPhone(resultSet.getString("phone"));
                    teacher.setExperience(resultSet.getInt("experience"));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return teacher;
    }

    @Override
    public boolean delete(int id) {
        Statement st = null;
        int count = 0;
        try {
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_TEACHER_BY_ID + id);
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
            ResultSet resultSet = st.executeQuery(SQL_SELECT_TEACHER_BY_LOGIN + "'" + login + "'");
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
    public boolean delete(Teacher entity) {
        Statement st = null;
        int count = 0;
        try {
            int id = entity.getIdTeacher();
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_TEACHER_BY_ID + id);
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
    public Teacher create(Teacher entity) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_INSERT_INTO);
            st.setString(1, entity.getLogin());
            st.setString(2, entity.getPassword());
            st.setString(3, entity.getLastName());
            st.setString(4, entity.getFirstName());
            st.setString(5, entity.getPatronymic());
            st.setString(6, entity.getPhone());
            st.setInt(7,entity.getExperience());

            st.executeUpdate();
            Teacher teacher = findEntityByLogin(entity.getLogin());
            return teacher;
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return null;
    }

    @Override
    public boolean create(Teacher entity ,int i) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_INSERT_INTO);
            st.setString(1, entity.getLogin());
            st.setString(2, entity.getPassword());
            st.setString(3, entity.getLastName());
            st.setString(4, entity.getFirstName());
            st.setString(5, entity.getPatronymic());
            st.setString(6, entity.getPhone());
            st.setInt(7,entity.getExperience());

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
    public Teacher update(Teacher entity) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_UPDATE_BY_ID + entity.getIdTeacher());
            st.setString(1, entity.getLogin());
            st.setString(2, entity.getPassword());
            st.setString(3,entity.getLastName());
            st.setString(4,entity.getFirstName());
            st.setString(5,entity.getPatronymic());
            st.setString(6,entity.getPhone());
            st.setInt(7,entity.getExperience());

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
    public List<Teacher> findByIdTeacher(int idTeacher) throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Teacher> findByGroupNumber(int groupNumber) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String,Integer> findLessons(int idStudent) throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean changeLessons(int idStudent, String name, int status){
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Integer> findPastTest(){
        return null;
    }

}