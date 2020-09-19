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
        boolean isThereSameParty = false;
        for(int i=0;i<baza.getParty().size();i++){
            if(baza.getParty().get(i).getName_party().equals(txtfldPartyName.getText())){
                isThereSameParty=true;
            }
        }

        if(txtfldPartyName.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error dialog");
            alert.setHeaderText("Dodavanje stranke");
            alert.setContentText("Neuspješno dodavanje stranke!");
            alert.showAndWait();
        }

        else if(isThereSameParty==true){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error dialog");
            alert.setHeaderText("Neuspješno dodavanje stranke");
            alert.setContentText("Unesena stranka već postoji!");
            alert.showAndWait();
        }
        else {
            part.setName_party(txtfldPartyName.getText());
            baza.addParty(part);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information dialog");
            alert.setHeaderText("Dodavanje stranke");
            alert.setContentText("Uspješno ste dodali stranku!");
            alert.showAndWait();

            baza.closeBase();
            Stage zatvaranjePoruka = (Stage) txtfldPartyName.getScene().getWindow();
            zatvaranjePoruka.close();
        }
    }
}
