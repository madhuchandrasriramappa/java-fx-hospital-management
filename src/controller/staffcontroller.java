package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.staff;


public class staffcontroller {

    @FXML
    private TextField staffNameField;

    @FXML
    private TextField staffRoleField;

    @FXML
    private TableView<staff> staffTableView;

    @FXML
    private TableColumn<staff, String> staffNameColumn;

    @FXML
    private TableColumn<staff, String> staffRoleColumn;

    private ObservableList<staff> staffList; // List to store staff data

    @FXML
    public void initialize() {
        // Initialize TableView columns
        staffNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        staffRoleColumn.setCellValueFactory(cellData -> cellData.getValue().roleProperty());

        // Example: Initialize staffList with some sample data
        staffList = FXCollections.observableArrayList();
        staffList.add(new staff("John Doe", "Doctor"));
        staffList.add(new staff("Alice Smith", "Nurse"));

        // Populate TableView with sample staff data
        staffTableView.setItems(staffList);
    }

    @FXML
    private void addstaff() {
        String staffName = staffNameField.getText();
        String staffRole = staffRoleField.getText();

        // Validate input
        if (!staffName.isEmpty() && !staffRole.isEmpty()) {
            // Create a new staff object and add it to the list
            staff newstaff = new staff(staffName, staffRole);
            staffList.add(newstaff);

            // Clear input fields after adding
            staffNameField.clear();
            staffRoleField.clear();

            showAlert("staff Added", "New staff added successfully!");
        } else {
            showAlert("Error", "Please enter both staff name and role.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

