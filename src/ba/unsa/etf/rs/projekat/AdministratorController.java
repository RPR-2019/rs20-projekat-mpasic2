package ba.unsa.etf.rs.projekat;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
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

    public AdministratorController() {
    }

    public AdministratorController(String model) {
        super();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblCandidats.setItems(baza.getCandidats());
        //colMan.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getManufacturer().getName()));
        colParty.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPartyId().getName_party()));
        //colParty.setCellValueFactory(data -> (ObservableValue<String>) new PropertyValueFactory(data.getValue().getParty_id().getName_party()));
        colCandidats.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getName()+" "+cellData.getValue().getLastname()));
        colNumberVote.setCellValueFactory(new PropertyValueFactory<>("voteNumber"));
        //colFunction.setCellValueFactory(data -> (ObservableValue<String>) new PropertyValueFactory(data.getValue().getFunctions().getFunction_name()));
        colFunction.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFunctions().getFunction_name()));


        tblVoters.setItems(baza.getUsers());
        colUserNumber.setCellValueFactory(new PropertyValueFactory<>("cardNumber"));
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

        if(baza.getCandidats().size()==0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nije moguće izvršiti brisanje!");
            alert.setHeaderText("Baza podataka je već prazna");
            alert.setContentText("Molimo kontaktirajte administraotora");

            alert.showAndWait();
        }
        else {
            int counderPresident = 0;
            int counterUnderPresident = 0;
            int conterDeputy = 0;
            int maxPred = 0;
            for (int i = 0; i < baza.getPresidents().size(); i++) {
                if (baza.getPresidents().get(i).getVoteNumber() > baza.getPresidents().get(maxPred).getVoteNumber())
                    maxPred = i;
            }
            int maxPotpred = 0;
            for (int i = 0; i < baza.getUnderPresidents().size(); i++) {
                if (baza.getUnderPresidents().get(i).getVoteNumber() > baza.getUnderPresidents().get(maxPotpred).getVoteNumber())
                    maxPotpred = i;
            }
            int maxDeputy = 0;
            for (int i = 0; i < baza.getDeputy().size(); i++) {
                if (baza.getDeputy().get(i).getVoteNumber() > baza.getDeputy().get(maxDeputy).getVoteNumber())
                    maxDeputy = i;
            }
            for (int i = 0; i < baza.getPresidents().size(); i++) {
                if (baza.getPresidents().get(i).getVoteNumber() == baza.getPresidents().get(maxPred).getVoteNumber())
                    counderPresident = counderPresident + 1;
            }
            for (int i = 0; i < baza.getUnderPresidents().size(); i++) {
                if (baza.getUnderPresidents().get(i).getVoteNumber() == baza.getUnderPresidents().get(maxPotpred).getVoteNumber())
                    counterUnderPresident = counterUnderPresident + 1;
            }
            for (int i = 0; i < baza.getDeputy().size(); i++) {
                if (baza.getDeputy().get(i).getVoteNumber() == baza.getDeputy().get(maxDeputy).getVoteNumber())
                    conterDeputy = conterDeputy + 1;
            }

            if (counderPresident > 1 || counterUnderPresident > 1 || conterDeputy > 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Info");
                alert.setHeaderText("Postoje kandidati koji imaju jednak broj glasova!");
                alert.setContentText("Molimo kontaktirajte administraotora");

                alert.showAndWait();

            } else {
                Stage noviProzor = new Stage();
                Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/winners.fxml"));
                noviProzor.setTitle("Izvještaj");
                Scene scene = new Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
                noviProzor.setScene(scene);
                noviProzor.show();

                Stage zatvaranjePoruka = (Stage) mainGridAdmin.getScene().getWindow();
                zatvaranjePoruka.close();
                baza.deleteEverything();


            }

        }



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

        Stage zatvaranjePoruka = (Stage) mainGridAdmin.getScene().getWindow();
        zatvaranjePoruka.close();
    }

    public void addPartyAction(ActionEvent actionEvent) throws IOException, SQLException {
        Stage noviProzor = new Stage();
        Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/newParty.fxml"));
        noviProzor.setTitle("Dodavanje stranke");
        Scene scene = new  Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        noviProzor.setScene(scene);
        noviProzor.show();

    }

    public void writeDatAction(ActionEvent actionEvent) {

        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Zapiši XML datoteku");
            Stage stage = (Stage)mainGridAdmin.getScene().getWindow();
            File file = fileChooser.showSaveDialog(stage);
            if (file == null)
                return;

            XMLFormat xml = new XMLFormat();
            xml.zapisi(file);


        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong file format");
            alert.setContentText("An error occured during file save.");
            alert.showAndWait();
        }
    }

    public void deleteCandAction(ActionEvent actionEvent) throws IOException {
        Stage noviProzor = new Stage();
        Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/deleteCandidats.fxml"));
        noviProzor.setTitle("Brisanje kandidata");
        Scene scene = new  Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        noviProzor.setScene(scene);
        noviProzor.show();

        Stage zatvaranjePoruka = (Stage) mainGridAdmin.getScene().getWindow();
        zatvaranjePoruka.close();
    }
}
