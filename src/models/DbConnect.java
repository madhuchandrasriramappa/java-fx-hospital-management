package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
    private static Connection connection = null;

    private DbConnect() {
        // Private constructor to prevent instantiation
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            // JDBC URL, username, and password
            String jdbcUrl = "jdbc:mysql://www.papademas.net:3307/510labs?autoReconnect=true&useSSL=false";
            String username = "db510";
            String password = "510";

            try {
                connection = DriverManager.getConnection(jdbcUrl, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e; // Re-throw the exception to handle it in the calling code
            }
        }
        return connection;
    }
}
