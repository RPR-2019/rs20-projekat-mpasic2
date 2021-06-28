package ba.unsa.etf.rs.projekat;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class NewPartyController {
    public TextField txtfldPartyName;
    public VotingDAO baza = new VotingDAO();
    private Party part = new Party(1,"name");

    public void addParty(ActionEvent actionEvent) throws SQLException, IOException {
        boolean isThereSameParty = false;
        for(int i=0;i<baza.getParty().size();i++){
            if(baza.getParty().get(i).getName_party().equals(txtfldPartyName.getText())){
                isThereSameParty=true;
            }
        }

        if(txtfldPartyName.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Dodavanje stranke");
            alert.setContentText("Neuspješno dodavanje stranke!");
            alert.showAndWait();
        }

        else if(isThereSameParty==true){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Neuspješno dodavanje stranke");
            alert.setContentText("Unesena stranka već postoji!");
            alert.showAndWait();
        }
        else {
            part.setName_party(txtfldPartyName.getText());
            baza.addParty(part);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Obavijest");
            alert.setHeaderText("Dodavanje stranke");
            alert.setContentText("Uspješno ste dodali stranku!");
            alert.showAndWait();

            baza.closeBase();
            Stage zatvaranjePoruka = (Stage) txtfldPartyName.getScene().getWindow();
            zatvaranjePoruka.close();

            Stage noviProzor = new Stage();
            Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/administrator.fxml"));
            noviProzor.setTitle("Dobrodošli");
            Scene scene = new Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            noviProzor.setScene(scene);
            noviProzor.show();
        }


    }

    public void backToAdmin(ActionEvent actionEvent) throws IOException {
        Stage noviProzor = new Stage();
        Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/administrator.fxml"));
        noviProzor.setTitle("Dobrodošli");
        Scene scene = new Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        noviProzor.setScene(scene);
        noviProzor.show();

        Stage zatvaranjePoruka = (Stage) txtfldPartyName.getScene().getWindow();
        zatvaranjePoruka.close();
    }
}
