package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;

public class patient {

    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty disease;
    private final StringProperty patientId;
    private final StringProperty id;
    
    public patient(String firstName, String lastName, String disease, String patientId, String id) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.disease = new SimpleStringProperty(disease);
        this.patientId = new SimpleStringProperty(patientId);
        this.id = new SimpleStringProperty(id);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String i) {
    	this.id.set(i);
    }

    // Getter methods for firstName and lastName properties
    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }
    
    public void setDisease(String s) {
        this.disease.set(s);
    }

    // Setter methods for firstName and lastName properties
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    // Property accessor methods for firstName and lastName
    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }
    
    public StringProperty diseaseProperty() {
        return disease;
    }
    
    public StringProperty diseaseIdProperty() {
        return id;
    }
    
    public StringProperty patientIdProperty() {
        return patientId;
    }
    
    public StringProperty IdProperty() {
        return id;
    }
}
