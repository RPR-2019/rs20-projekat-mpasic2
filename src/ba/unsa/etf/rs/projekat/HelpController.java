package ba.unsa.etf.rs.projekat;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class HelpController implements Initializable {
    public ImageView imgAbout;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image image = new Image("/img/pozadi.jpg");
        imgAbout.setImage(image);

    }


}
