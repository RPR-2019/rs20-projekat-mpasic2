package ba.unsa.etf.rs.projekat;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class NewCandidatController {

    public ChoiceBox<Party> candidateParty;
    public TextField candidateName;
    public TextField candidateLastname;
    public TextField candidateLivingPlace;
    public ChoiceBox<Functions> candidateFunction;
    public DatePicker candidateDate;
    public VotingDAO baza = new VotingDAO();
    private ObservableList<Party> stranke = baza.getParty();
    private ObservableList<Functions> funkcije = baza.getFunction();
    public Party part = new Party(5,"aaa");
    public Functions fun = new Functions(4,"kralj");
    public Candidats cand = new Candidats(1,part,"1","2", LocalDate.now(),"1",fun,3);



    @FXML
    public void initialize() {
        candidateParty.setItems(stranke);
        candidateFunction.setItems(funkcije);
    }

    public void sacuvajAction(ActionEvent actionEvent) throws SQLException, IOException {
        int numbefOfCandidats = baza.getCandidats().size();

        if (candidateName.getText().isEmpty() || candidateLastname.getText().isEmpty() || candidateParty.getValue()==null ||
                candidateLivingPlace.getText().isEmpty() || candidateFunction.getValue()==null) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error dialog");
            alert.setHeaderText("Neuspješno dodavanje kandidata");
            alert.setContentText("Niste popunili sva potrebna polja!");
            alert.showAndWait();
        }
        else {
            cand.setId(numbefOfCandidats + 1);
            cand.setPartyId(candidateParty.getSelectionModel().getSelectedItem());
            cand.setName(candidateName.getText());
            cand.setLastname(candidateLastname.getText());
            cand.setBirthDate(candidateDate.getValue());
            cand.setLivingPlace(candidateLivingPlace.getText());
            cand.setFunctions(candidateFunction.getSelectionModel().getSelectedItem());
            cand.setVoteNumber(0);

            baza.addCandidate(cand);


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setHeaderText("Dodavanje kandidata");
            alert.setContentText("Uspjesno ste dodali kandidata!");

            alert.showAndWait();
            baza.closeBase();

            Stage zatvaranjePoruka = (Stage) candidateFunction.getScene().getWindow();
            zatvaranjePoruka.close();


            Stage noviProzor = new Stage();
            Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/administrator.fxml"));
            noviProzor.setTitle("Dobrodosli");
            Scene scene = new Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            noviProzor.setScene(scene);
            noviProzor.show();

        }
    }
}
