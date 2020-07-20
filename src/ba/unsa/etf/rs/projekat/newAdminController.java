package ba.unsa.etf.rs.projekat;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class newAdminController {
    public TextField newAAdminEmail;
    public PasswordField newAdminPassword;
    public VotingDAO baza = new VotingDAO();
    private Admin admin = new Admin(1,"2","2");


    public void newAdminAction(ActionEvent actionEvent) {
        admin.setE_mail(newAAdminEmail.getText());
        admin.setPassword(newAdminPassword.getText());
        baza.addAdmin(admin);

        Stage zatvaranjePoruka = (Stage) newAdminPassword.getScene().getWindow();
        zatvaranjePoruka.close();
    }
}
