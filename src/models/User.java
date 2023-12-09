package models;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNo;
    private String type;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Similarly, define getter and setter methods for other fields

    public String getLastName() {
        return lastName;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
       this.type = type;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
