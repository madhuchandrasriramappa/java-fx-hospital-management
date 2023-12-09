package models;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Otherclass {

    public void createTables() {
        String[] createTableQueries = {
            "CREATE TABLE IF NOT EXISTS mc_login (id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(50) NOT NULL, password VARCHAR(50) NOT NULL)",
            "CREATE TABLE IF NOT EXISTS mc_patient (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100) NOT NULL, dob DATE, gender VARCHAR(10), address VARCHAR(255))",
            "CREATE TABLE IF NOT EXISTS mc_register (id INT AUTO_INCREMENT PRIMARY KEY, patient_id INT NOT NULL, admission_date DATE NOT NULL, discharge_date DATE, diagnosis VARCHAR(255), FOREIGN KEY (patient_id) REFERENCES mc_patient(id))",
            "CREATE TABLE IF NOT EXISTS mc_staff (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100) NOT NULL, position VARCHAR(50), department VARCHAR(50))"
        };

        try (Connection connection = DbConnect.getConnection()) {
            if (connection != null) {
                Statement statement = connection.createStatement();

                for (String query : createTableQueries) {
                    statement.executeUpdate(query);
                    System.out.println("Table created successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void retrieveTableNames() {
        try (Connection connection = DbConnect.getConnection()) {
            if (connection != null) {
                DatabaseMetaData metaData = connection.getMetaData();

                // Retrieving specific tables by name
                String[] tableNames = {"mc_login", "mc_patient", "mc_register", "mc_staff"};
                System.out.println("Tables:");
                for (String tableName : tableNames) {
                    ResultSet resultSet = metaData.getTables(null, null, tableName, null);
                    while (resultSet.next()) {
                        System.out.println(tableName);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Otherclass other = new Otherclass();
        other.createTables(); // Create tables
        other.retrieveTableNames(); // Retrieve table names
    }
}
