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
import java.time.LocalDate;
import java.util.ResourceBundle;

import static java.lang.Integer.valueOf;
import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class VotePageController implements Initializable {

    public Button votePageBack;
    public TableView<Candidats> tblPresident;
    public TableColumn<Candidats,Integer> colPresidentNumber;
    public TableColumn<Candidats,String> colPresidentName;
    public TableColumn<Candidats,String> colPresidentParty;
    public TableView<Candidats> tblUnderPresident;
    public TableColumn<Candidats,Integer> colUnderPresidentNumber;
    public TableColumn<Candidats,String> colUnderPresidentName;
    public TableColumn<Candidats,String> colUnderPresidentParty;
    public TableView<Candidats> tblDeputy;
    public TableColumn<Candidats,Integer> colDeputyNumber;
    public TableColumn<Candidats,String> colDeputyName;
    public TableColumn<Candidats,String> colDeputyParty;
    public VotingDAO baza = new VotingDAO();
    public TextField txtfldPresident;
    public TextField txtfldUnderPresident;
    public TextField txtfldDeputy;
    public Party part = new Party(5,"aaa");
    public Functions fun = new Functions(4,"kralj");
    public Candidats predsjednik = new Candidats(1,part,"1","2", LocalDate.now(),"1",fun,3);
    public Candidats potpredsjednik = new Candidats(1,part,"1","2", LocalDate.now(),"1",fun,3);
    public Candidats zamjenik = new Candidats(1,part,"1","2", LocalDate.now(),"1",fun,3);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblPresident.setItems(baza.getPresidents());
        colPresidentNumber.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPresidentName.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getName() + " " +cellData.getValue().getLastname()));
        colPresidentParty.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getParty_id().getName_party()));

        tblUnderPresident.setItems(baza.getUnderPresidents());
        colUnderPresidentNumber.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUnderPresidentName.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getName() + " " +cellData.getValue().getLastname()));
        colUnderPresidentParty.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getParty_id().getName_party()));

        tblDeputy.setItems(baza.getDeputy());
        colDeputyNumber.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDeputyName.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getName() + " " +cellData.getValue().getLastname()));
        colDeputyParty.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getParty_id().getName_party()));
    }


    public void votePageBackAction(ActionEvent actionEvent) throws IOException, SQLException {

        baza.deleteVoters(baza.numberOfVoters());

            baza.closeBase();
            Stage noviProzor = new Stage();
            Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/mainPage.fxml"));
            noviProzor.setTitle("E-glasanje");
            Scene scene = new Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            noviProzor.setScene(scene);
            noviProzor.show();

            Stage zatvaranjePoruka = (Stage) votePageBack.getScene().getWindow();
            zatvaranjePoruka.close();

    }


    public void onActionSacuvaj(ActionEvent actionEvent) throws IOException, SQLException {
        boolean presidentOK = false;
        boolean underPresidentOK = false;
        boolean deputyOK = false;
        for (int i=0;i<baza.getPresidents().size();i++){
            //System.out.printf("Ovo je u bazi: " + baza.getPresidents().get(i).getId() + "\n");
            //System.out.printf("Ovo je u txt-u: " + txtfldPresident.getText() + "\n");
            if(valueOf(txtfldPresident.getText())==baza.getPresidents().get(i).getId()){
                predsjednik=baza.getPresidents().get(i);
                presidentOK=true;}

        }
        for (int i=0;i<baza.getUnderPresidents().size();i++){
            //System.out.printf("Ovo je u bazi: " + baza.getUnderPresidents().get(i).getId() + "\n");
            if(valueOf(txtfldUnderPresident.getText())==baza.getUnderPresidents().get(i).getId()){
                potpredsjednik=baza.getUnderPresidents().get(i);
                underPresidentOK=true;}

        }
        for (int i=0;i<baza.getDeputy().size();i++){
            if(valueOf(txtfldDeputy.getText())==baza.getDeputy().get(i).getId()){
                zamjenik=baza.getDeputy().get(i);
                deputyOK=true;}

        }

        if(txtfldPresident.getText().isEmpty() || txtfldUnderPresident.getText().isEmpty() || txtfldDeputy.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Greška prilikom glasanja");
            alert.setContentText("Niste popunili sva polja!");

            if(txtfldPresident.getText().isEmpty() ){
                txtfldPresident.getStyleClass().removeAll("poljeIspravno");
                txtfldPresident.getStyleClass().add("poljeNijeIspravno");
            }
            if(txtfldUnderPresident.getText().isEmpty() ){
                txtfldUnderPresident.getStyleClass().removeAll("poljeIspravno");
                txtfldUnderPresident.getStyleClass().add("poljeNijeIspravno");
            }
            if(txtfldDeputy.getText().isEmpty() ){
                txtfldDeputy.getStyleClass().removeAll("poljeIspravno");
                txtfldDeputy.getStyleClass().add("poljeNijeIspravno");
            }
            //kad je uredu
            if(!txtfldPresident.getText().isEmpty() ){
                txtfldPresident.getStyleClass().removeAll("poljeNijeIspravno");
            }
            if(!txtfldUnderPresident.getText().isEmpty() ){
                txtfldUnderPresident.getStyleClass().removeAll("poljeNijeIspravno");
            }
            if(!txtfldDeputy.getText().isEmpty() ){
                txtfldDeputy.getStyleClass().removeAll("poljeNijeIspravno");
            }

            alert.showAndWait();
        }
        //kad nije prazno ali uneseni podaci nisu validni

        if(!presidentOK){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Greška prilikom glasanja");
            alert.setContentText("Neispravno glasanje!");
            alert.showAndWait();

            txtfldPresident.getStyleClass().removeAll("poljeIspravno");
            txtfldPresident.getStyleClass().add("poljeNijeIspravno");
        }
        if(!underPresidentOK){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Greška prilikom glasanja");
            alert.setContentText("Neispravno glasanje!");
            alert.showAndWait();

            txtfldUnderPresident.getStyleClass().removeAll("poljeIspravno");
            txtfldUnderPresident.getStyleClass().add("poljeNijeIspravno");
        }
        if(!deputyOK){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Greška prilikom glasanja");
            alert.setContentText("Neispravno glasanje!");
            alert.showAndWait();

            txtfldDeputy.getStyleClass().removeAll("poljeIspravno");
            txtfldDeputy.getStyleClass().add("poljeNijeIspravno");
        }
        if(presidentOK && underPresidentOK && deputyOK) {


            baza.addVotes(predsjednik);
            baza.addVotes(potpredsjednik);
            baza.addVotes(zamjenik);

            baza.closeBase();

            //alert for succes
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Successful voting");
            alert.setHeaderText("Uspješno ste glasali");
            alert.setContentText("Hvala Vam!");
            alert.showAndWait();

            Stage noviProzor = new Stage();
            Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/mainPage.fxml"));
            noviProzor.setTitle("E-glasanje");
            Scene scene = new Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            noviProzor.setScene(scene);
            noviProzor.show();

            Stage zatvaranjePoruka = (Stage) votePageBack.getScene().getWindow();
            zatvaranjePoruka.close();


        }
    }
}
