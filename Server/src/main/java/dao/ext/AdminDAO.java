package dao.ext;

import dao.AbstractDAO;
import entity.ext.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminDAO extends AbstractDAO<Admin> {


    public static final String SQL_SELECT_ALL_ADMINS = "SELECT * FROM drivingschool.admin";
    public static final String SQL_SELECT_ADMIN_BY_ID = "SELECT * FROM drivingschool.admin WHERE idAdmin=";
    public static final String SQL_SELECT_ADMIN_BY_LOGIN = "SELECT * FROM drivingschool.admin WHERE login=";
    public static final String SQL_DELETE_BY_LOGIN = "DELETE FROM drivingschool.admin WHERE drivingschool.admin.login=";
    public static final String SQL_DELETE_BY_ID = "DELETE FROM drivingschool.admin WHERE drivingschool.admin.idAdmin=";
    public static final String SQL_INSERT_INTO = "INSERT INTO drivingschool.admin (login,password)  VALUES(?,?)";
    public static final String SQL_CHECK_LOGIN_AND_ID = "SELECT * FROM drivingschool.admin WHERE drivingSchool.admin.idAdmin=? OR drivingschool.admin.login=?";
    public static final String SQL_UPDATE_BY_ID = "UPDATE drivingschool.admin SET login=?,password=? WHERE drivingschool.admin.idAdmin=";
    public static final String SQL_UPDATE_BY_LOGIN = "UPDATE drivingschool.admin SET login=?,password=? WHERE drivingschool.admin.login=";
    public static final String SQL_CHECK_LOGIN_AND_PASSWORD= "SELECT * FROM drivingschool.admin WHERE drivingschool.admin.login=? AND drivingschool.admin.password=?";
    public static final String SQL_CHECK_PHONE_AND_ID = "SELECT * FROM drivingschool.admin WHERE drivingSchool.admin.idAdmin=? AND drivingschool.admin.phone=?";
    public static final String SQL_SELECT_ADMIN_BY_PHONE = "SELECT * FROM drivingschool.admin WHERE phone=";

    public AdminDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Admin> findAll() {
        List<Admin> admins = new ArrayList<>();
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_ADMINS);
            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setIdAdmin(resultSet.getInt("idAdmin"));
                admin.setLogin(resultSet.getString("login"));
                admin.setPassword(resultSet.getString("password"));

                admins.add(admin);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return admins;
    }

    @Override
    public Admin findEntityById(int id) throws UnsupportedOperationException {
        Admin admin = null;
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ADMIN_BY_ID + id);
            while (resultSet.next()) {
                if (resultSet.getInt("idAdmin") == id) {
                    admin = new Admin();
                    admin.setIdAdmin(resultSet.getInt("idAdmin"));
                    admin.setLogin(resultSet.getString("login"));
                    admin.setPassword(resultSet.getString("password"));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return admin;
    }

    @Override
    public Admin findEntityByLogin(String login) {
        Admin admin = null;
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ADMIN_BY_LOGIN + "'" + login + "'");
            while (resultSet.next()) {
                if (resultSet.getString("login").equals(login)) {
                    admin = new Admin();
                    admin.setIdAdmin(resultSet.getInt("idAdmin"));
                    admin.setLogin(resultSet.getString("login"));
                    admin.setPassword(resultSet.getString("password"));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return admin;
    }



    @Override
    public boolean delete(int id) {
        Statement st = null;
        int count = 0;
        try {
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ADMIN_BY_ID + id);
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
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ADMIN_BY_LOGIN + "'" + login + "'");
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
    public boolean delete(Admin entity) {

        Statement st = null;
        int count = 0;
        try {
            int id = entity.getIdAdmin();
            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ADMIN_BY_ID + id);
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
    public Admin create(Admin entity) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_INSERT_INTO);
            st.setString(1, entity.getLogin());
            st.setString(2, entity.getPassword());

            st.executeUpdate();
            Admin admin = findEntityByLogin(entity.getLogin());
            return admin;
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return null;
    }

    @Override
    public boolean create(Admin entity , int i) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_INSERT_INTO);
            st.setString(1, entity.getLogin());
            st.setString(2, entity.getPassword());

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
    public Admin update(Admin entity) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_UPDATE_BY_ID + entity.getIdAdmin());
            st.setString(1, entity.getLogin());
            st.setString(2, entity.getPassword());
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
    public List<Admin> findByIdTeacher(int idTeacher) throws UnsupportedOperationException{
      throw new UnsupportedOperationException();
    }

    @Override
    public List<Admin> findByGroupNumber(int groupNumber) throws UnsupportedOperationException {
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