package ba.unsa.etf.rs.projekat;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class newPasswordController implements Initializable {
    public PasswordField newPasswordOne;
    public PasswordField newPasswordTwo;
    public PasswordField odlPassword;
    public VotingDAO baza = new VotingDAO();
    public TextField emailAdmin;

    public void newPasswordAction(ActionEvent actionEvent) throws IOException {
        if(newPasswordOne.getText().equals(newPasswordTwo.getText())){
            baza.setNewPassword(emailAdmin.getText(),MainPageController.hashPassword(newPasswordOne.getText()));



            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Obavijest");
            alert.setHeaderText("Uspjesno");
            alert.setContentText("Uspjesno promjenjena lozinka!");

            alert.showAndWait();

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Greška prilikom promjene lozinke");
            alert.setContentText("Neispravni podaci!");

            alert.showAndWait();
        }

        Stage zatvaranjePoruka = (Stage) newPasswordOne.getScene().getWindow();
        zatvaranjePoruka.close();

        Stage noviProzor = new Stage();
        Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/administrator.fxml"));
        noviProzor.setTitle("Dobrodošli");
        Scene scene = new Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        noviProzor.setScene(scene);
        noviProzor.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emailAdmin.setText(MainPageController.userNameGlobal);
    }

    public void backToAdmin(ActionEvent actionEvent) throws IOException {
        Stage noviProzor = new Stage();
        Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/administrator.fxml"));
        noviProzor.setTitle("Dobrodošli");
        Scene scene = new Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        noviProzor.setScene(scene);
        noviProzor.show();

        Stage zatvaranjePoruka = (Stage) newPasswordOne.getScene().getWindow();
        zatvaranjePoruka.close();
    }
}
