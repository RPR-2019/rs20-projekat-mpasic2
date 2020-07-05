package ba.unsa.etf.rs.projekat;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class PocetnaController {

    /*public Button AdminDugme;
    public Button GlasacDugme;
    public TextField PassAdmin;
    public TextField UsernameAdmin;*/

    public void helpProzor(ActionEvent actionEvent) throws IOException {
        //Stage scena = new Stage();
        //Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml.about.fxml"));
        Stage noviProzor = new Stage();
        Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/help.fxml"));
        noviProzor.setTitle("Informacije o programu");
        Scene scene = new  Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        noviProzor.setScene(scene);
        noviProzor.show();
    }
}
