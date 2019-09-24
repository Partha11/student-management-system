package home.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {

    private SimpleIntegerProperty id;
    private SimpleStringProperty email;
    private SimpleIntegerProperty isAdmin;

    public User() {

        id = new SimpleIntegerProperty();
        email = new SimpleStringProperty();
        isAdmin = new SimpleIntegerProperty();
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public int getIsAdmin() {
        return isAdmin.get();
    }

    public SimpleIntegerProperty isAdminProperty() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin.set(isAdmin);
    }
}
