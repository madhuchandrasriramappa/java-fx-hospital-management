package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.patient;
import models.User;
import models.patientDB;
import models.patientDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class patientcontrollerjava {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;
    
    @FXML
    private TextField newDisease;

    @FXML
    private TableView<patient> patientTableView;

    @FXML
    private TableColumn<patient, String> patientId;
    
    @FXML
    private TableColumn<patient, String> firstName;

    @FXML
    private TableColumn<patient, String> lastName;
    
    @FXML
    private TableColumn<patient, String> disease;


    private List<patient> patientList; // Example patient data list
    
    public User loggedInUser;
    
    ObservableList<patient> tableData;

    @FXML
    public void initialize() {
    	patientId.setCellValueFactory(cellData -> cellData.getValue().patientIdProperty());
    	firstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
    	lastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    	disease.setCellValueFactory(cellData -> cellData.getValue().diseaseProperty());
    }
    
    public void initData(User user) {
        this.loggedInUser = user;
        
        patientDao p = new patientDao();
        List<patientDB> patientDisease = p.getPatientsByEmail(this.loggedInUser.getEmail());

      	 // Set sample data to the TableView
        tableData = FXCollections.observableArrayList();
        
        for(int i=0; i<patientDisease.size(); i++) {
        	tableData.add(new patient( 
        			patientDisease.get(i).getFirstName(),
        			patientDisease.get(i).getLastName(),
        			patientDisease.get(i).getDisease(),
        			patientDisease.get(i).getEmail(),
        			"0"));
        }
        
        patientTableView.setItems(tableData);
        
        // You can perform actions with loggedInUser here or in initialize()
    }

    @FXML
    private void addpatient() {
        String new_disease = newDisease.getText();

        // Validate input
        if (!new_disease.isEmpty()) {
        	
        	patientDB pdb = new patientDB(this.loggedInUser.getFirstName(), this.loggedInUser.getLastName(), 
        			new_disease, this.loggedInUser.getEmail(), "0");
        	
        	patient newPatientDisease = new patient(this.loggedInUser.getFirstName(), 
        			this.loggedInUser.getLastName(), 
        			new_disease, this.loggedInUser.getEmail(), "0");
     
        	
        	 this.tableData.add(newPatientDisease);
        	 
        	 patientDao pdao =  new patientDao();
        	 pdao.insertPatient(pdb);

            // Clear input fields after adding
        	newDisease.clear();

            showAlert("patient Added", "New patient added successfully!");
        } else {
            showAlert("Error", "Please enter both first name and last name.");
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

