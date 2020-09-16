package ba.unsa.etf.rs.projekat;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class newAdminController {
    public TextField newAAdminEmail;
    public PasswordField newAdminPassword;
    public VotingDAO baza = new VotingDAO();
    private Admin admin = new Admin(1,"2","2");


    public void newAdminAction(ActionEvent actionEvent) throws SQLException {
        admin.setEmail(newAAdminEmail.getText());
        admin.setPassword(newAdminPassword.getText());
        baza.addAdmin(admin);

        baza.closeBase();

        Stage zatvaranjePoruka = (Stage) newAdminPassword.getScene().getWindow();
        zatvaranjePoruka.close();
    }
}
