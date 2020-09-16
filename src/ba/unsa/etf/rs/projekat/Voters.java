package ba.unsa.etf.rs.projekat;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Voters {
    SimpleIntegerProperty id;
    SimpleStringProperty cardNumber;
    SimpleStringProperty jmbg;


    public Voters(int id, String cardNumber, String jmbg) {
        this.id = new SimpleIntegerProperty(id);
        this.cardNumber = new SimpleStringProperty(cardNumber);
        this.jmbg = new SimpleStringProperty(jmbg);
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

    public String getCardNumber() {
        return cardNumber.get();
    }

    public SimpleStringProperty cardNumberProperty() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber.set(cardNumber);
    }

    public String getJmbg() {
        return jmbg.get();
    }

    public SimpleStringProperty jmbgProperty() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg.set(jmbg);
    }
}
