package connector;



import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;
import java.util.Properties;

public class DBConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/drivingschool?characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        Driver driver;
        try {
            driver = new FabricMySQLDriver();
        } catch (SQLException e) {
            System.out.println("Ошибка при создании драйвера...");
            throw new RuntimeException(e);
        }
        try {
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            System.out.println("Не удалось зарегестрировать драйвер");
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Не удалось установить соединение");
            throw new RuntimeException(e);
        }
        return connection;
    }
}