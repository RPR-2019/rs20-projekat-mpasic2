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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.naming.Binding;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
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

    /*public AdministratorController() {
        baza = VotingDAO.getInstance();
        Admin = new Admin();
    }
    colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colPublishDate.setCellValueFactory(new PropertyValueFactory<>("publishDate"));

        colGradDrzava.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDrzava().getNaziv()));
        colName.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getName()+" "+cellData.getValue().getSurname()));
*/
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
}
