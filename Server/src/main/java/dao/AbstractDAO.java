package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import entity.AbstractEntity;

public abstract class AbstractDAO <T extends AbstractEntity> {
    protected Connection connection;
    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }
    public abstract List<T> findAll();
    public abstract List<T> findByIdTeacher(int idTeacher);
    public abstract List<T> findByGroupNumber(int groupNumber);
    public abstract T findEntityById(int id);
    public abstract T findEntityByLogin(String login);
    public abstract boolean delete(String login);
    public abstract boolean delete(T entity);
    public abstract boolean delete(int id);
    public abstract T create(T entity);
    public abstract boolean create(T entity , int i);
    public abstract T update(T entity);
    public abstract boolean checkLoginAndId(int id, String login);
    public abstract boolean checkPhoneAndId(int id, String phone);
    public abstract boolean checkLoginAndPassword(String login, String password);
    public abstract boolean checkPhone(String phone);
    public abstract Map<String,Integer> findLessons(int idStudent);
    public abstract boolean changeLessons(int idStudent, String name, int status);
    public abstract List<Integer> findPastTest();

    public void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

}
