package ba.unsa.etf.rs.projekat;

import com.sun.jdi.connect.Transport;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class HelpController implements Initializable {
    public Button dugmeSlanje;
    public ImageView imgAbout;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image image = new Image("/img/admin.png");
        imgAbout.setImage(image);

    }


    public void izlazHelp(ActionEvent actionEvent) {
        Stage zatvaranjePoruka=(Stage)imgAbout.getScene().getWindow();
        zatvaranjePoruka.close();
    }
}
