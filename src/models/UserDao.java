package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    // Other methods...

    // Method to insert a user into the database
    public boolean insertUser(User user) {
        String insertQuery = "INSERT INTO mc_register (first_name, last_name, phone_no, email, password, type) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPhoneNo());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getType());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0; // Returns true if the user was inserted successfully

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if user insertion failed
    }

    // Method to retrieve a user by email and password
    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;
        String query = "SELECT * FROM mc_register WHERE email = ? AND password = ?";

        try (Connection connection = DbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // If a user is found, create a User object
                user = new User();
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setPhoneNo(resultSet.getString("phone_no"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setType(resultSet.getString("type"));
                // Set other properties accordingly
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
