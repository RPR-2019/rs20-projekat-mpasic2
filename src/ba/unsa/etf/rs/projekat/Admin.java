package ba.unsa.etf.rs.projekat;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Admin {
    SimpleIntegerProperty id;
    SimpleStringProperty e_mail;
    SimpleStringProperty password;

    public Admin(int id, String e_mail, String password) {
        this.id = new SimpleIntegerProperty(id);
        this.e_mail = new SimpleStringProperty(e_mail);
        this.password = new SimpleStringProperty(password);
    }

    /*public Admin(int anInt, String string, String string1) {
    }*/

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getE_mail() {
        return e_mail.get();
    }

    public SimpleStringProperty e_mailProperty() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail.set(e_mail);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
}
