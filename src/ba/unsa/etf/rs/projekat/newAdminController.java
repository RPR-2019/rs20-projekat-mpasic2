package ba.unsa.etf.rs.projekat;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class newAdminController {
    public TextField newAAdminEmail;
    public PasswordField newAdminPassword;
    public VotingDAO baza = new VotingDAO();
    private Admin admin = new Admin(1,"2","2");


    public void newAdminAction(ActionEvent actionEvent) throws SQLException, IOException {
        admin.setEmail(newAAdminEmail.getText());
        admin.setPassword(MainPageController.hashPassword(newAdminPassword.getText()));
        baza.addAdmin(admin);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information dialog");
        alert.setHeaderText("Uspjesno");
        alert.setContentText("Uspjesno dodan administrator!");

        alert.showAndWait();

        baza.closeBase();

        Stage zatvaranjePoruka = (Stage) newAdminPassword.getScene().getWindow();
        zatvaranjePoruka.close();


        Stage noviProzor = new Stage();
        Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/administrator.fxml"));
        noviProzor.setTitle("Dobrodošli");
        Scene scene = new Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        noviProzor.setScene(scene);
        noviProzor.show();

    }

    public void backToAdmin(ActionEvent actionEvent) throws IOException {
        Stage noviProzor = new Stage();
        Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/administrator.fxml"));
        noviProzor.setTitle("Dobrodošli");
        Scene scene = new Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        noviProzor.setScene(scene);
        noviProzor.show();

        Stage zatvaranjePoruka = (Stage) newAdminPassword.getScene().getWindow();
        zatvaranjePoruka.close();
    }
}
