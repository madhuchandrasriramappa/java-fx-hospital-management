package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.User;
import models.UserDao;
import controller.patientcontrollerjava;
import controller.doctorDashboard;

import java.io.IOException;

public class logincontroller {

    @FXML
    private TextField emailIdField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void login() {
        String email = emailIdField.getText();
        String password = passwordField.getText();

        UserDao userDao = new UserDao();
        User user = userDao.getUserByEmailAndPassword(email, password);
        
        if (user == null) {
        	showAlert("Login Failed", "Invalid email or password");
        }
        
        if (user.getType().equals("doctor")) {
        	openDoctorDashboardPage();
        } else {
        	openPatientManagementPage(user);
        }
    }

    @FXML
    private void openPatientManagementPage(User user) {
        try {
        	patientcontrollerjava p = new patientcontrollerjava();
        	p.loggedInUser = user;
        	
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/patientManagement.fxml"));
            Parent root = loader.load();
            
            // Get the controller instance
            patientcontrollerjava controller = loader.getController();
            controller.initData(user);
            
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Patient Management Dashboard");
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // openDoctorDashboardpage
    @FXML
    private void openDoctorDashboardPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/doctorDashboard.fxml"));
            Parent root = loader.load();
            
            // Get the controller instance
            doctorDashboard controller = loader.getController();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Doctor Dashboard");
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    @FXML
    private void showUserRegisterationScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Register.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Register User");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
