package ba.unsa.etf.rs.projekat;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import static org.junit.jupiter.api.Assertions.*;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

@ExtendWith(ApplicationExtension.class)
public class MainPageTest {
    @Start
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainPage.fxml"));
        stage.setTitle("E-glasanje");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }

    @Test
    public void userTest(FxRobot robot){
        Button button = robot.lookup("#loginAdmin").queryAs(Button.class);
        assertNotEquals(null,button);
    }
}
