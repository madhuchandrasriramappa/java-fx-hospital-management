package controller;

import models.UserDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import models.User;

import java.io.IOException;

public class register {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneNoField;
    
    @FXML
    private CheckBox isDoctorField;

    @FXML
    private void handleRegisterButtonAction() {
        // Get user data from the form
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String phoneNo = phoneNoField.getText();
        Boolean is_doctor = isDoctorField.isSelected();

        // Create a User object
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNo(phoneNo);
        
        if (is_doctor) {
        	user.setType("doctor");
        } else {
        	user.setType("patient");
        }

        // Create a UserDAO instance
        UserDao userDAO = new UserDao();

        // Insert the user into the database
        boolean isInserted = userDAO.insertUser(user);

        if (isInserted) {
            // Show registration success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration Successful");
            alert.setHeaderText(null);
            alert.setContentText("User registered successfully!");
            alert.showAndWait();

            // sucessful registartion
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Login.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Show error message if registration fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration Failed");
            alert.setHeaderText(null);
            alert.setContentText("Failed to register user!");
            alert.showAndWait();
        }
    }
}
