package ba.unsa.etf.rs.projekat;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Observable;

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

    public void sacuvajAction(ActionEvent actionEvent) throws SQLException {
        int numbefOfCandidats = baza.getCandidats().size();

        cand.setId(numbefOfCandidats+1);
        cand.setParty_id(candidateParty.getSelectionModel().getSelectedItem());
        cand.setName(candidateName.getText());
        cand.setLastname(candidateLastname.getText());
        cand.setBirth_date(candidateDate.getValue());
        cand.setLiving_place(candidateLivingPlace.getText());
        cand.setFunctions(candidateFunction.getSelectionModel().getSelectedItem());
        cand.setVote_number(0);

        baza.addCandidate(cand);


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setHeaderText("Dodavanje kandidata");
        alert.setContentText("Uspjesno ste dodali kandidata!");

        alert.showAndWait();


        Stage zatvaranjePoruka = (Stage) candidateFunction.getScene().getWindow();
        zatvaranjePoruka.close();
        baza.closeBase();
    }
}
