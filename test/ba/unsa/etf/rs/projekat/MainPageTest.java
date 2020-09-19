package ba.unsa.etf.rs.projekat;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import static org.junit.jupiter.api.Assertions.*;
import org.testfx.framework.junit5.Start;

import java.awt.event.KeyEvent;
import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

@ExtendWith(ApplicationExtension.class)
public class MainPageTest {
    Stage theStage;
    @Start
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainPage.fxml"));
        stage.setTitle("E-glasanje");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
        stage.toFront();
        theStage = stage;
    }

    @Test
    public void userTest(FxRobot robot){
        Button button = robot.lookup("#loginAdmin").queryAs(Button.class);
        assertNotEquals(null,button);

        Platform.runLater( () -> {
            theStage.close();
        });
    }
    @Test
    public void voterTest(FxRobot robot){
        Button button = robot.lookup("#LoginUser").queryAs(Button.class);
        assertNotEquals(null,button);

        Platform.runLater( () -> {
            theStage.close();
        });
    }
    @Test
    public void loginAdmin(FxRobot robot){
        robot.clickOn("#adminName");
        robot.write("1");
        //robot.clickOn("#adminPassword");
        robot.press(KeyCode.TAB).release(KeyCode.TAB).press(KeyCode.ENTER);
        robot.write("1");
        //robot.clickOn("#loginAdmin");
        robot.press(KeyCode.TAB).release(KeyCode.TAB).press(KeyCode.ENTER);

        robot.lookup("#backToMainPage").tryQuery().isPresent();
        assertTrue(theStage.isShowing());

        Platform.runLater( () -> {
            theStage.close();
        });

    }
    @Test
    public void loginVoter(FxRobot robot){
        robot.clickOn("#userNumber");
        robot.write("145K2O6");
        robot.clickOn("#userJMBG");
        robot.write("2702000175249");
        TextField polje = robot.lookup("#userJMBG").queryAs(TextField.class);
        robot.clickOn("#LoginUser");

        assertTrue(theStage.isShowing());
        Platform.runLater( () -> {
            theStage.close();
        });

    }

    @Test
    public void loginAdmin1(FxRobot robot){
        robot.clickOn("#adminName");
        robot.write("Admin");

        //robot.clickOn("#adminPassword");
        robot.press(KeyCode.TAB).release(KeyCode.TAB).press(KeyCode.ENTER);
        robot.write("password");
        robot.clickOn("#loginAdmin");


        assertTrue(theStage.isShowing());
        Platform.runLater( () -> {
            theStage.close();
        });
    }

    @Test
    public void loginAdminBack(FxRobot robot){
        robot.clickOn("#adminName");
        robot.write("1");
        //robot.clickOn("#adminPassword");
        robot.press(KeyCode.TAB).release(KeyCode.TAB).press(KeyCode.ENTER);
        robot.write("1");
        //robot.clickOn("#loginAdmin");
        robot.press(KeyCode.TAB).release(KeyCode.TAB).press(KeyCode.ENTER);
        robot.clickOn("#loginAdmin");

        robot.clickOn("#backToMainPage");
        assertFalse(theStage.isShowing());

        Platform.runLater( () -> {
            theStage.close();
        });
    }

    @Test
    public void help(FxRobot robot){
        robot.press(KeyCode.ALT).press(KeyCode.H).press(KeyCode.C);
        ImageView imgAbout = robot.lookup("#imgAbout").queryAs(ImageView.class);
        assertNotNull(imgAbout);

        Platform.runLater( () -> {
            Stage stage = (Stage) imgAbout.getScene().getWindow();
            theStage.close();
        });
    }

    @Test
    public void helpClose(FxRobot robot){
        robot.press(KeyCode.ALT).press(KeyCode.H).press(KeyCode.C);
        //robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        robot.clickOn("#outButton");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        robot.sleep(1000);

        robot.press(KeyCode.ALT);
        robot.press(KeyCode.F4);
        Button button = new Button();
        try {
             button = robot.lookup("#outButton").queryAs(Button.class);
        }
        catch (Exception e){
            assertNotEquals(null,button);
        }



    }

}
