package dao.ext;

import dao.AbstractDAO;
import entity.ext.Lesson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LessonDAO extends AbstractDAO<Lesson> {


    public static final String SQL_SELECT_ALL_LESSONS = "SELECT * FROM drivingschool.lesson";
    public static final String SQL_SELECT_LESSON_BY_ID = "SELECT * FROM drivingschool.lesson WHERE id=";
    public static final String SQL_DELETE_BY_ID = "DELETE FROM drivingschool.lesson WHERE drivingschool.lesson.id=";
    public static final String SQL_CHECK_ID = "SELECT * FROM drivingschool.lesson WHERE drivingSchool.lesson.id=";
    public static final String SQL_UPDATE_BY_ID = "UPDATE drivingschool.lesson SET lessonName=? WHERE drivingschool.lesson.id=";
    public static final String SQL_INSERT_INTO = "INSERT INTO drivingschool.lesson (id,lessonName)  VALUES(?,?)";

    public static final String SQL_SELECT_ALL_PAST_TEST = "SELECT count(*) AS count FROM student_has_lesson WHERE status=1 group by lesson_id;";

    public LessonDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Lesson> findAll() {
        List<Lesson> lessons = new ArrayList<>();
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_LESSONS);
            while (resultSet.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(resultSet.getInt("id"));
                lesson.setLessonName(resultSet.getString("lessonName"));
                lessons.add(lesson);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return lessons;
    }

    @Override
    public Lesson findEntityById(int id) throws UnsupportedOperationException {
        Lesson lesson = null;
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_LESSON_BY_ID + id);
            while (resultSet.next()) {
                if (resultSet.getInt("id") == id) {
                    lesson = new Lesson();
                    lesson.setId(resultSet.getInt("id"));
                    lesson.setLessonName(resultSet.getString("lessonName"));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return lesson;
    }

    @Override
    public Lesson findEntityByLogin(String login) throws UnsupportedOperationException {
     throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(int id) throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(String login) {
        int idLesson = Integer.parseInt(login);
        Statement st = null;
        int count = 0;
        int id = -1;
        try {
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_LESSON_BY_ID + idLesson);
            if (resultSet.next()) {
                st.executeUpdate(SQL_DELETE_BY_ID + idLesson);
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
    public boolean delete(Lesson entity) {
        Statement st = null;
        int count = 0;
        try {
            int id = entity.getId();
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_LESSON_BY_ID + id);
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
    public Lesson create(Lesson entity) throws UnsupportedOperationException {
       throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Lesson entity, int i) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_INSERT_INTO);
            st.setString(1, entity.getLessonName());
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
    public Lesson update(Lesson entity) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_UPDATE_BY_ID + entity.getId());
            st.setString(1, entity.getLessonName());
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
            st = connection.prepareStatement(SQL_CHECK_ID);
            st.setInt(1, id);
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
       return true;
    }

    @Override
    public boolean checkLoginAndPassword(String login, String password) throws UnsupportedOperationException {
        return false;
    }

    @Override
    public boolean checkPhoneAndId(int id, String phone) throws UnsupportedOperationException {
        return false;
    }
    @Override
    public List<Lesson> findByIdTeacher(int idTeacher) throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Lesson> findByGroupNumber(int groupNumber) throws UnsupportedOperationException {
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
        List<Integer> pastTest = new ArrayList<>();
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_PAST_TEST);
            while (resultSet.next()) {
                pastTest.add(resultSet.getInt("count"));
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return pastTest;
    }
}