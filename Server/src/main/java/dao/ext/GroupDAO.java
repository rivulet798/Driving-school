package dao.ext;

import dao.AbstractDAO;
import entity.ext.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupDAO extends AbstractDAO<Group> {


    public static final String SQL_SELECT_ALL_GROUPS = "SELECT * FROM drivingschool.group";
    public static final String SQL_SELECT_GROUP_BY_GROUP_NUMBER = "SELECT * FROM drivingschool.group WHERE groupNumber=";
    public static final String SQL_SELECT_GROUP_BY_ID_TEACHER = "SELECT * FROM drivingschool.group WHERE teacher_idTeacher=";
    public static final String SQL_DELETE_BY_GROUP_NUMBER = "DELETE FROM drivingschool.group WHERE drivingschool.group.groupNumber=";
    public static final String SQL_INSERT_INTO = "INSERT INTO drivingschool.group (groupNumber,category,maxNumberOfStudents,dateOfBeginning,dateOfEnding,teacher_idTeacher)  VALUES(?,?,?,?,?,?)";
    public static final String SQL_CHECK_GROUP_NUMBER = "SELECT * FROM drivingschool.group WHERE drivingSchool.group.groupNumber=?";
    public static final String SQL_UPDATE_BY_GROUP_NUMBER = "UPDATE drivingschool.group SET category=?,maxNumberOfStudents=?,dateOfBeginning=?,dateOfEnding=?,teacher_idTeacher=? WHERE drivingschool.group.groupNumber=";


    public GroupDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Group> findAll() {
        List<Group> groups = new ArrayList<>();
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_GROUPS);
            while (resultSet.next()) {
                Group group = new Group();
                group.setGroupNumber(resultSet.getInt("groupNumber"));
                group.setCategory(resultSet.getString("category"));
                group.setMaxNumberOfStudents(resultSet.getInt("maxNumberOfStudents"));
                group.setDateOfBeginning(resultSet.getString("dateOfBeginning"));
                group.setDateOfEnding(resultSet.getString("dateOfEnding"));
                group.setIdTeacher(resultSet.getInt("teacher_idTeacher"));
                groups.add(group);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return groups;
    }

    @Override
    public List<Group> findByIdTeacher(int idTeacher) {
        List<Group> groups = new ArrayList<>();
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_GROUP_BY_ID_TEACHER + idTeacher);
            while (resultSet.next()) {
                Group group = new Group();
                group.setGroupNumber(resultSet.getInt("groupNumber"));
                group.setCategory(resultSet.getString("category"));
                group.setMaxNumberOfStudents(resultSet.getInt("maxNumberOfStudents"));
                group.setDateOfBeginning(resultSet.getString("dateOfBeginning"));
                group.setDateOfEnding(resultSet.getString("dateOfEnding"));
                group.setIdTeacher(resultSet.getInt("teacher_idTeacher"));
                groups.add(group);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return groups;
    }

    @Override
    public Group findEntityById(int groupNumber) throws UnsupportedOperationException {
        Group group = null;
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_GROUP_BY_GROUP_NUMBER + groupNumber);
            while (resultSet.next()) {
                if (resultSet.getInt("groupNumber") == groupNumber) {
                    group = new Group();
                    group.setGroupNumber(resultSet.getInt("groupNumber"));
                    group.setCategory(resultSet.getString("category"));
                    group.setMaxNumberOfStudents(resultSet.getInt("maxNumberOfStudents"));
                    group.setDateOfBeginning(resultSet.getString("dateOfBeginning"));
                    group.setDateOfEnding(resultSet.getString("dateOfEnding"));
                    group.setIdTeacher(resultSet.getInt("teacher_idTeacher"));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return group;
    }

    @Override
    public Group findEntityByLogin(String login) throws UnsupportedOperationException{
       throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(int idGroup){
        Statement st = null;

        try {
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_GROUP_BY_GROUP_NUMBER + idGroup);
            if (resultSet.next()) {
                st.executeUpdate(SQL_DELETE_BY_GROUP_NUMBER + idGroup );
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
    public boolean delete(String idStr)  throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean delete(Group entity) {
        Statement st = null;
        int count = 0;
        try {
            int id = entity.getGroupNumber();
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_GROUP_BY_GROUP_NUMBER + id);
            if (resultSet.next()) {
                st.executeUpdate(SQL_DELETE_BY_GROUP_NUMBER + id);
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
    public Group create(Group entity) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_INSERT_INTO);
            st.setInt(1, entity.getGroupNumber());
            st.setString(2, entity.getCategory());
            st.setInt(3, entity.getMaxNumberOfStudents());
            st.setString(4, entity.getDateOfBeginning());
            st.setString(5, entity.getDateOfEnding());
            st.setInt(6,entity.getIdTeacher());

            st.executeUpdate();
            Group group = findEntityById(entity.getGroupNumber());
            return group;
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return null;
    }

    @Override
    public boolean create(Group entity , int i) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_INSERT_INTO);
            st.setInt(1, entity.getGroupNumber());
            st.setString(2, entity.getCategory());
            st.setInt(3, entity.getMaxNumberOfStudents());
            st.setString(4, entity.getDateOfBeginning());
            st.setString(5, entity.getDateOfEnding());
            st.setInt(6,entity.getIdTeacher());

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
    public Group update(Group entity) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_UPDATE_BY_GROUP_NUMBER + entity.getGroupNumber());
            st.setString(1, entity.getCategory());
            st.setInt(2, entity.getMaxNumberOfStudents());
            st.setString(3,entity.getDateOfBeginning());
            st.setString(4,entity.getDateOfEnding());
            st.setInt(5,entity.getIdTeacher());

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
            st = connection.prepareStatement(SQL_CHECK_GROUP_NUMBER);
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
    public List<Group> findByGroupNumber(int groupNumber) throws UnsupportedOperationException {
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