package ba.unsa.etf.rs.projekat;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Party {
    SimpleIntegerProperty id;
    SimpleStringProperty name_party;

    public Party(SimpleIntegerProperty id, SimpleStringProperty name_party) {
        this.id = id;
        this.name_party = name_party;
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

    public String getName_party() {
        return name_party.get();
    }

    public SimpleStringProperty name_partyProperty() {
        return name_party;
    }

    public void setName_party(String name_party) {
        this.name_party.set(name_party);
    }
}
