package models;

public class patientDB {
    private String firstName;
    private String lastName;
    private String disease;
    private String email;
    private String id;
    
    public patientDB() {
    	
    }
    // patient database
    public patientDB(String firstName, String LastName, String disease, String email, String id) {
    	this.firstName = firstName;
    	this.lastName = LastName;
    	this.disease = disease;
    	this.email = email;
    	this.id = id;
    }
    
    // Getter and setter for 'firstName'
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter and setter for 'lastName'
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter and setter for 'disease'
    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    // Getter and setter for 'email'
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    // Getter and setter for 'id'
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
