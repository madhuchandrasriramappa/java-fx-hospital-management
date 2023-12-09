package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class patientDao {
	// Method to retrieve a patient by email
//    public patientDB getPatientByEmail(String email) {
//    	patientDB user = null;
//        String query = "SELECT * FROM mc_patient WHERE email = ?";
//
//        try (Connection connection = DbConnect.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//
//            preparedStatement.setString(1, email);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                // If a user is found, create a User object
//                user = new patientDB();
//                user.setFirstName(resultSet.getString("First Name"));
//                user.setLastName(resultSet.getString("Last Name"));
//                user.setEmail(resultSet.getString("email"));
//                user.setDisease(resultSet.getString("Disease"));
//                // Set other properties accordingly
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        
//        return user;
//    }
    
    public List<patientDB> getPatientsByEmail(String email) {
        List<patientDB> users = new ArrayList<>();
        String query = "SELECT * FROM mc_patient WHERE email = ?";

        try (Connection connection = DbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                patientDB user = new patientDB();
                user.setFirstName(resultSet.getString("First Name"));
                user.setLastName(resultSet.getString("Last Name"));
                user.setEmail(resultSet.getString("email"));
                user.setDisease(resultSet.getString("Disease"));
                // Set other properties accordingly

                users.add(user); // Add each user to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
    
    public List<patientDB> getAllPatients() {
        List<patientDB> users = new ArrayList<>();
        String query = "SELECT * FROM mc_patient";

        try (Connection connection = DbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                patientDB user = new patientDB();
                user.setFirstName(resultSet.getString("First Name"));
                user.setLastName(resultSet.getString("Last Name"));
                user.setEmail(resultSet.getString("email"));
                user.setDisease(resultSet.getString("Disease"));
                user.setId(resultSet.getString("Patient ID"));
                // Set other properties accordingly

                users.add(user); // Add each user to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
    
    public void insertPatient(patientDB patient) {
        String query = "INSERT INTO mc_patient (`First Name`, `Last Name`, `email`, `Disease`) VALUES (?, ?, ?, ?)";

        try (Connection connection = DbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, patient.getFirstName());
            preparedStatement.setString(2, patient.getLastName());
            preparedStatement.setString(3, patient.getEmail());
            preparedStatement.setString(4, patient.getDisease());
            // Set other properties accordingly if needed

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Patient inserted successfully!");
            } else {
                System.out.println("Failed to insert patient.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
    // deleting the patientbyemail
    public void deletePatientByEmail(String id) {
        String query = "DELETE FROM mc_patient WHERE `Patient ID` = ?";

        try (Connection connection = DbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Patient deleted successfully!");
            } else {
                System.out.println("No patient found with the given email.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updatePatient(String d, String id) {
        String query = "UPDATE mc_patient SET `Disease` = ? WHERE `Patient ID` = ?";

        try (Connection connection = DbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, d);
            preparedStatement.setString(2, id); // Assuming 'patient_id' is the unique identifier

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Patient updated successfully!");
            } else {
                System.out.println("Failed to update patient.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

