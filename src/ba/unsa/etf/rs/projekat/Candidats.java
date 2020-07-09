package ba.unsa.etf.rs.projekat;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Candidats {
    SimpleIntegerProperty id;
    SimpleObjectProperty<Party> party_id;
    SimpleStringProperty name;
    SimpleStringProperty lastname;
    SimpleObjectProperty<LocalDate> birth_date;
    SimpleStringProperty living_place;
    SimpleObjectProperty<Functions> functions;
    SimpleIntegerProperty vote_number;

    public Candidats(SimpleIntegerProperty id, SimpleObjectProperty<Party> party_id, SimpleStringProperty name, SimpleStringProperty lastname, SimpleObjectProperty<LocalDate> birth_date, SimpleStringProperty living_place, SimpleObjectProperty<Functions> functions, SimpleIntegerProperty vote_number) {
        this.id = id;
        this.party_id = party_id;
        this.name = name;
        this.lastname = lastname;
        this.birth_date = birth_date;
        this.living_place = living_place;
        this.functions = functions;
        this.vote_number = vote_number;
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

    public Party getParty_id() {
        return party_id.get();
    }

    public SimpleObjectProperty<Party> party_idProperty() {
        return party_id;
    }

    public void setParty_id(Party party_id) {
        this.party_id.set(party_id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getLastname() {
        return lastname.get();
    }

    public SimpleStringProperty lastnameProperty() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    public LocalDate getBirth_date() {
        return birth_date.get();
    }

    public SimpleObjectProperty<LocalDate> birth_dateProperty() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date.set(birth_date);
    }

    public String getLiving_place() {
        return living_place.get();
    }

    public SimpleStringProperty living_placeProperty() {
        return living_place;
    }

    public void setLiving_place(String living_place) {
        this.living_place.set(living_place);
    }

    public Functions getFunctions() {
        return functions.get();
    }

    public SimpleObjectProperty<Functions> functionsProperty() {
        return functions;
    }

    public void setFunctions(Functions functions) {
        this.functions.set(functions);
    }

    public int getVote_number() {
        return vote_number.get();
    }

    public SimpleIntegerProperty vote_numberProperty() {
        return vote_number;
    }

    public void setVote_number(int vote_number) {
        this.vote_number.set(vote_number);
    }
}
