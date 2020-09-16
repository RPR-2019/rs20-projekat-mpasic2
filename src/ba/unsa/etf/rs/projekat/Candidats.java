package ba.unsa.etf.rs.projekat;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Candidats {
    SimpleIntegerProperty id;
    SimpleObjectProperty<Party> partyId;
    SimpleStringProperty name;
    SimpleStringProperty lastname;
    SimpleObjectProperty<LocalDate> birthDate;
    SimpleStringProperty livingPlace;
    SimpleObjectProperty<Functions> functions;
    SimpleIntegerProperty voteNumber;

    public Candidats(int id, Party partyId, String name, String lastname, LocalDate birthDate, String livingPlace, Functions functions, int voteNumber) {
        this.id = new SimpleIntegerProperty(id);
        this.partyId = new SimpleObjectProperty<Party>(partyId);
        this.name = new SimpleStringProperty(name);
        this.lastname = new SimpleStringProperty(lastname);
        this.birthDate = new SimpleObjectProperty<LocalDate>(birthDate);
        this.livingPlace = new SimpleStringProperty(livingPlace);
        this.functions = new SimpleObjectProperty<Functions>(functions);
        this.voteNumber = new SimpleIntegerProperty(voteNumber);
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

    public Party getPartyId() {
        return partyId.get();
    }

    public SimpleObjectProperty<Party> partyIdProperty() {
        return partyId;
    }

    public void setPartyId(Party partyId) {
        this.partyId.set(partyId);
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

    public LocalDate getBirthDate() {
        return birthDate.get();
    }

    public SimpleObjectProperty<LocalDate> birthDateProperty() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate.set(birthDate);
    }

    public String getLivingPlace() {
        return livingPlace.get();
    }

    public SimpleStringProperty livingPlaceProperty() {
        return livingPlace;
    }

    public void setLivingPlace(String livingPlace) {
        this.livingPlace.set(livingPlace);
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

    public int getVoteNumber() {
        return voteNumber.get();
    }

    public SimpleIntegerProperty voteNumberProperty() {
        return voteNumber;
    }

    public void setVoteNumber(int voteNumber) {
        this.voteNumber.set(voteNumber);
    }


}
