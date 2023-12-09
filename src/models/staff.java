package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class staff {

    private final StringProperty name;
    private final StringProperty role;

    public staff(String name, String role) {
        this.name = new SimpleStringProperty(name);
        this.role = new SimpleStringProperty(role);
    }

    // Getter methods for name and role properties
    public String getName() {
        return name.get();
    }

    public String getRole() {
        return role.get();
    }

    // Setter methods for name and role properties
    public void setName(String name) {
        this.name.set(name);
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    // Property methods for name and role
    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty roleProperty() {
        return role;
    }
}

