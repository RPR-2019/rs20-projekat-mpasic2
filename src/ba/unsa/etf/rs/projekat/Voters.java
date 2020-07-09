package ba.unsa.etf.rs.projekat;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Voters {
    SimpleIntegerProperty id;
    SimpleStringProperty card_number;
    SimpleStringProperty jmbg;


    public Voters(int id, String card_number, String jmbg) {
        this.id = new SimpleIntegerProperty(id);
        this.card_number = new SimpleStringProperty(card_number);
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

    public String getCard_number() {
        return card_number.get();
    }

    public SimpleStringProperty card_numberProperty() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number.set(card_number);
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
