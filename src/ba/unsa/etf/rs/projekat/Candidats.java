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
}
