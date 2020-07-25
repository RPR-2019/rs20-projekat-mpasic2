package ba.unsa.etf.rs.projekat;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class NewPartyController {
    public TextField txtfldPartyName;
    public VotingDAO baza = new VotingDAO();
    private Party part = new Party(1,"name");

    public void addParty(ActionEvent actionEvent) throws SQLException {
        part.setName_party(txtfldPartyName.getText());
        baza.addParty(part);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setHeaderText("Dodavanje stranke");
        alert.setContentText("Uspješno ste dodali stranku!");

        alert.showAndWait();
        baza.closeBase();
        Stage zatvaranjePoruka = (Stage) txtfldPartyName.getScene().getWindow();
        zatvaranjePoruka.close();
    }
}
