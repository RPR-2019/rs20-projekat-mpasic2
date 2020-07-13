package ba.unsa.etf.rs.projekat;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.StageStyle;


import javax.swing.*;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class MainPageController {

    public Button loginAdmin;
    public Button LoginUser;
    public TextField adminName;
    public PasswordField adminPassword;
    public TextField userNumber;
    public TextField userJMBG;
    public Button closeMainPage;
    VotingDAO baza = new VotingDAO();
    private Voters glasac = new Voters(1,"","");

    public void helpProzor(ActionEvent actionEvent) throws IOException {
        Stage noviProzor = new Stage();
        Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/help.fxml"));
        noviProzor.setTitle("Kontakt centar");
        Scene scene = new  Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        noviProzor.setScene(scene);
        noviProzor.show();
    }

    /*adminName.textProperty().addListener((obs, oldIme, newIme) -> { });
    public boolean valjalMail(String email) {
        Character ludoA = '@';
        for(int i=0;i<adminName.getLength();i++){
            if(email.charAt(i) == ludoA)
                return true;
        }
        return false;
    }*/


    public void loginAdminButton(ActionEvent actionEvent) throws IOException {
        boolean alertProzor=false;

        for(int i=0;i<baza.getAdmin().size();i++){
            if(adminName.getText().equals(baza.getAdmin().get(i).getE_mail()) && adminPassword.getText().equals(baza.getAdmin().get(i).getPassword())) {
                Stage noviProzor = new Stage();
                Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/administrator.fxml"));
                noviProzor.setTitle("Dobrodosao " + adminName.getText());
                Scene scene = new Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
                noviProzor.setScene(scene);
                noviProzor.show();

                Stage zatvaranjePoruka = (Stage) adminName.getScene().getWindow();
                zatvaranjePoruka.close();
                alertProzor=true;
            }


        }

        if(!alertProzor){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Greška prilikom registracije");
            alert.setContentText("Neispravni podaci!");

            alert.showAndWait();
        }


    }

    public void loginUserButton(ActionEvent actionEvent) throws IOException {
        boolean daLiJeOK = true;


        if(userJMBG.getText().isEmpty() || userJMBG.getLength()!=13){
            userJMBG.getStyleClass().removeAll("poljeIspravno");
            userJMBG.getStyleClass().add("poljeNijeIspravno");
            daLiJeOK=false;
        }

        if(userNumber.getText().isEmpty()){
            userNumber.getStyleClass().removeAll("poljeIspravno");
            userNumber.getStyleClass().add("poljeNijeIspravno");
            daLiJeOK=false;
        }


        if(daLiJeOK) {

            glasac.setCard_number(userNumber.getText());
            glasac.setJmbg(userJMBG.getText());
            boolean sveUredu = true;
            for(int i=0;i<baza.getUsers().size();i++){
                if(userNumber.getText().equals(baza.getUsers().get(i).getCard_number()))
                        sveUredu=false;
                if(userJMBG.getText().equals(baza.getUsers().get(i).getJmbg()))
                    sveUredu=false;
            }
            if(sveUredu) {

                baza.addUser(glasac);

                Stage noviProzor = new Stage(StageStyle.UNDECORATED);
                Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/votePage.fxml"));
                noviProzor.setTitle("Glasački listić");
                Scene scene = new Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
                noviProzor.setScene(scene);
                noviProzor.show();

                Stage zatvaranjePoruka = (Stage) adminName.getScene().getWindow();
                zatvaranjePoruka.close();
            }

            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Greska prilikom registracije");
                alert.setContentText("Ovaj korisnik je vec galasao!");

                alert.showAndWait();
            }
        }

        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Greska prilikom registracije");
            alert.setContentText("Neispravni podaci!");

            alert.showAndWait();
        }
    }

    public void closeMainPageButton(ActionEvent actionEvent) throws IOException {
        //baza.deleteInstance(); ovo je za bazu kada je dodam
        Stage zatvaranjePoruka=(Stage)adminName.getScene().getWindow();
        zatvaranjePoruka.close();
    }

}
