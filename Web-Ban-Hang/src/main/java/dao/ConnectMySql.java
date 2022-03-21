package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectMySql {
    public static Connection getConnection() {
        String jdbcURL = "jdbc:mysql://localhost:3306/case_module3";
        String jdbcUsername = "root";
        String jdbcPassword = "Nguyenduy12";

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
