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


public class doctorDashboard {
	@FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;
    
    @FXML
    private TextField deleteDisease;
    
    @FXML
    private TextField updateDiseaseID;
    
    @FXML
    private TextField updateDiseaseName;

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
    
    @FXML
    private TableColumn<patient, String> diseaseId;

    private List<patient> patientList; // Example patient data list
    
    public User loggedInUser;
    
    ObservableList<patient> tableData;
    
    @FXML
    public void initialize() {
    	patientId.setCellValueFactory(cellData -> cellData.getValue().patientIdProperty());
    	firstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
    	lastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    	disease.setCellValueFactory(cellData -> cellData.getValue().diseaseProperty());
    	diseaseId.setCellValueFactory(cellData -> cellData.getValue().diseaseIdProperty());
    	
        patientDao p = new patientDao();
        List<patientDB> patientDisease = p.getAllPatients();
        
   	 // Set sample data to the TableView
        tableData = FXCollections.observableArrayList();
        
        for(int i=0; i<patientDisease.size(); i++) {
        
        	tableData.add(new patient( 
        			patientDisease.get(i).getFirstName(),
        			patientDisease.get(i).getLastName(),
        			patientDisease.get(i).getDisease(),
        			patientDisease.get(i).getEmail(),
        			patientDisease.get(i).getId()));
        }
        
        patientTableView.setItems(tableData);
    }
    
    @FXML
    public void deleteSelectedPatient() {
    	String d = deleteDisease.getText();
    	
    	for(int i=0; i<tableData.size(); i++) {
    		if(tableData.get(i).getId().equals(d)) {
    			tableData.remove(i);
    		}
    	}
    	
	   	 patientDao pdao =  new patientDao();
	   	 pdao.deletePatientByEmail(d);
	   	 
	   	showAlert("Success", "Patient record deleted successfully");
    }
    // updataPatient
    @FXML
    public void updatePatient() {
    	String d1 = updateDiseaseID.getText();
    	String d2 = updateDiseaseName.getText();
       
    	for(int i=0; i<tableData.size(); i++) {
    		if(tableData.get(i).getId().equals(d1)) {
    			tableData.get(i).setDisease(d2);
    		}
    	}
    	
    	patientDao pdao =  new patientDao();
	   	 pdao.updatePatient(d2, d1);
	   	 
	   	showAlert("Success", "Patient record updated successfully");
    }
    
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
