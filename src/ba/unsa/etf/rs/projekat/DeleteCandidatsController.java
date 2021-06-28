package ba.unsa.etf.rs.projekat;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;


public class DeleteCandidatsController implements Initializable {
    public Button votePageBack;
    public Button deleteBtn;
    public VotingDAO baza = new VotingDAO();
    public TableView<Candidats> tblCandidats;
    public TableColumn<Candidats,Integer> colCandNumber;
    public TableColumn<Candidats,String> colCandName;
    public TableColumn<Candidats,String> colCandParty;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblCandidats.setItems(baza.getCandidats());
        colCandNumber.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCandName.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getName() + " " +cellData.getValue().getLastname()));
        colCandParty.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPartyId().getName_party()));

    }

    public void votePageBackAction(ActionEvent actionEvent) throws SQLException, IOException {

        Stage zatvaranjePoruka = (Stage) votePageBack.getScene().getWindow();
        zatvaranjePoruka.close();

        Stage noviProzor = new Stage();
        Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/administrator.fxml"));
        noviProzor.setTitle("Dobrodošli");
        Scene scene = new Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        noviProzor.setScene(scene);
        noviProzor.show();

    }

    public void onActionDelete(ActionEvent actionEvent) {

        Candidats deleteCand = tblCandidats.getSelectionModel().getSelectedItem();
        if(deleteCand!=null) {


            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setContentText("Da li želite obrisati kandidata?");
            confirm.setTitle("Brisanje kandidata");
            Optional<ButtonType> result = confirm.showAndWait();
            if (result.get() == ButtonType.OK) {
                baza.deleteCandidat(deleteCand);
            }
            confirm.close();
            tblCandidats.setItems(baza.getCandidats());
        }
         else{
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setContentText("Niste odabrali kandidata kojeg želite obrisati!");
                    error.setTitle("Neuspješno brisanje!");
                    error.show();
                }


    }
}
