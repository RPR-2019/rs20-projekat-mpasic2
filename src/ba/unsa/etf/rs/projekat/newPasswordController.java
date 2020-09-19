package ba.unsa.etf.rs.projekat;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class newPasswordController {
    public PasswordField newPasswordOne;
    public PasswordField newPasswordTwo;
    public PasswordField odlPassword;
    public VotingDAO baza = new VotingDAO();
    public TextField emailAdmin;

    public void newPasswordAction(ActionEvent actionEvent) {
        if(newPasswordOne.getText().equals(newPasswordTwo.getText())){
            baza.setNewPassword(emailAdmin.getText(),newPasswordOne.getText());



            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information dialog");
            alert.setHeaderText("Uspjesno");
            alert.setContentText("Uspjesno promjenjena lozinka!");

            alert.showAndWait();

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Greška prilikom promjene lozinke");
            alert.setContentText("Neispravni podaci!");

            alert.showAndWait();
        }

        Stage zatvaranjePoruka = (Stage) newPasswordOne.getScene().getWindow();
        zatvaranjePoruka.close();

    }




}
