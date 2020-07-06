package ba.unsa.etf.rs.projekat;

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


import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class MainPageController {

    public Button loginAdmin;
    public Button LoginUser;
    public TextField adminName;
    public PasswordField adminPassword;
    public TextField userNumber;
    public TextField userJMBG;
    public Button closeMainPage;

    public void helpProzor(ActionEvent actionEvent) throws IOException {
        Stage noviProzor = new Stage();
        Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/help.fxml"));
        noviProzor.setTitle("Kontakt centar");
        Scene scene = new  Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        noviProzor.setScene(scene);
        noviProzor.show();
    }

    //adminName.textProperty().addListener((obs, oldIme, newIme) -> { });
    public boolean valjalMail(String email) {
        Character ludoA = '@';
        for(int i=0;i<adminName.getLength();i++){
            if(email.charAt(i) == ludoA)
                return true;
        }
        return false;
    }


    public void loginAdminButton(ActionEvent actionEvent) throws IOException {
        boolean daLiTrebaOtvoriti=true;

        if (!adminName.getText().isEmpty() && valjalMail(adminName.getText())) {
            adminName.getStyleClass().removeAll("poljeNijeIspravno");
            adminName.getStyleClass().add("poljeIspravno");
        } else {
            adminName.getStyleClass().removeAll("poljeIspravno");
            adminName.getStyleClass().add("poljeNijeIspravno");
            daLiTrebaOtvoriti=false;
        }

        if(adminPassword.getText().isEmpty()){
            adminPassword.getStyleClass().removeAll("poljeIspravno");
            adminPassword.getStyleClass().add("poljeNijeIspravno");
            daLiTrebaOtvoriti=false;
        } else {
            adminPassword.getStyleClass().removeAll("poljeNijeIspravno");
            adminPassword.getStyleClass().add("poljeIspravno");
        }


        if(daLiTrebaOtvoriti) {
            Stage noviProzor = new Stage();
            Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/administrator.fxml"));
            noviProzor.setTitle("Informacije o glasanju");
            Scene scene = new Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            noviProzor.setScene(scene);
            noviProzor.show();
        }
        else{
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
        } else {
            userJMBG.getStyleClass().removeAll("poljeNijeIspravno");
            userJMBG.getStyleClass().add("poljeIspravno");
        }



        if(daLiJeOK) {
            Stage noviProzor = new Stage();
            Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/votePage.fxml"));
            noviProzor.setTitle("Glasački listić");
            Scene scene = new Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            noviProzor.setScene(scene);
            noviProzor.show();
        }

        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Greška prilikom registracije");
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
