package ba.unsa.etf.rs.projekat;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.naming.Binding;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class AdministratorController implements Initializable {

    public GridPane mainGridAdmin;
    public TableView<Candidats> tblCandidats;
    public TableColumn<Candidats,String> colParty;
    public TableColumn<Candidats,String> colCandidats;
    public TableColumn<Candidats,Integer> colNumberVote;
    public TableColumn<Candidats,String> colFunction;

    public TableView<Voters> tblVoters;
    public TableColumn<Candidats,String> colUserNumber;
    public TableColumn<Candidats,String> colJMBG;
    public VotingDAO baza = new VotingDAO();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblCandidats.setItems(baza.getCandidats());
        //colMan.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getManufacturer().getName()));
        colParty.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getParty_id().getName_party()));
        //colParty.setCellValueFactory(data -> (ObservableValue<String>) new PropertyValueFactory(data.getValue().getParty_id().getName_party()));
        colCandidats.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getName()+" "+cellData.getValue().getLastname()));
        colNumberVote.setCellValueFactory(new PropertyValueFactory<>("vote_number"));
        //colFunction.setCellValueFactory(data -> (ObservableValue<String>) new PropertyValueFactory(data.getValue().getFunctions().getFunction_name()));
        colFunction.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFunctions().getFunction_name()));


        tblVoters.setItems(baza.getUsers());
        colUserNumber.setCellValueFactory(new PropertyValueFactory<>("card_number"));
        colJMBG.setCellValueFactory(new PropertyValueFactory<>("jmbg"));
    }


    public void backButtonAction(ActionEvent actionEvent) throws IOException {

        Stage noviProzor = new Stage();
        Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/mainPage.fxml"));
        noviProzor.setTitle("E-glasanje");
        Scene scene = new  Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        noviProzor.setScene(scene);
        noviProzor.show();

        Stage zatvaranjePoruka=(Stage)mainGridAdmin.getScene().getWindow();
        zatvaranjePoruka.close();

    }

    public void finishVoting(ActionEvent actionEvent) throws IOException {

        Stage noviProzor = new Stage();
        Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/winners.fxml"));
        noviProzor.setTitle("Izvještaj");
        Scene scene = new  Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        noviProzor.setScene(scene);
        noviProzor.show();

        Stage zatvaranjePoruka=(Stage)mainGridAdmin.getScene().getWindow();
        zatvaranjePoruka.close();
    }

    public void helpOnAction(ActionEvent actionEvent) throws IOException {
        Stage noviProzor = new Stage();
        Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/help.fxml"));
        noviProzor.setTitle("Kontakt centar");
        Scene scene = new  Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        noviProzor.setScene(scene);
        noviProzor.show();
    }

    public void newPasswordAction(ActionEvent actionEvent) throws IOException {
        Stage noviProzor = new Stage();
        Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/newPassword.fxml"));
        noviProzor.setTitle("Promjena lozinke");
        Scene scene = new  Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        noviProzor.setScene(scene);
        noviProzor.show();
    }

    public void newAdminAction(ActionEvent actionEvent) throws IOException {
        Stage noviProzor = new Stage();
        Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/newAdmin.fxml"));
        noviProzor.setTitle("Dodavanje administratora");
        Scene scene = new  Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        noviProzor.setScene(scene);
        noviProzor.showAndWait();
        //tblCandidats.setItems(baza.getCandidats());
    }

    public void addCandidatAction(ActionEvent actionEvent) throws IOException {
        Stage noviProzor = new Stage();
        Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/newCandidat.fxml"));
        noviProzor.setTitle("Dodavanje kandidata");
        Scene scene = new  Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        noviProzor.setScene(scene);
        noviProzor.show();
    }

    public void addPartyAction(ActionEvent actionEvent) throws IOException, SQLException {
        Stage noviProzor = new Stage();
        Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/newParty.fxml"));
        noviProzor.setTitle("Dodavanje stranke");
        Scene scene = new  Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        noviProzor.setScene(scene);
        noviProzor.show();

        //baza.closeBase();
    }
}
