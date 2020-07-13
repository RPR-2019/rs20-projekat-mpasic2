package ba.unsa.etf.rs.projekat;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class newPasswordController {
    public PasswordField newPasswordOne;
    public PasswordField newPasswordTwo;
    public PasswordField odlPassword;
    public VotingDAO baza = new VotingDAO();
    public TextField emailAdmin;

    public void newPasswordAction(ActionEvent actionEvent) {
        if(newPasswordOne==newPasswordTwo){
            baza.setNewPassword(emailAdmin.getText(),newPasswordOne.getText());
        }
    }




}
