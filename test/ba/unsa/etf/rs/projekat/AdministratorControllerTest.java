package ba.unsa.etf.rs.projekat;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(ApplicationExtension.class)
public class AdministratorControllerTest {
    public VotingDAO baza = new VotingDAO();
    Stage theStage;
    @Start
    public void start(Stage stage) throws IOException {
        Stage noviProzor = new Stage();
        Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/administrator.fxml"));
        noviProzor.setTitle("Dobrodosao");
        Scene scene = new Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        noviProzor.setScene(scene);
        noviProzor.show();
        stage.toFront();
        theStage = stage;

    }
    @Test
    public void help(FxRobot robot){
        robot.press(KeyCode.ALT).press(KeyCode.H).press(KeyCode.C);
        ImageView imgAbout = robot.lookup("#imgAbout").queryAs(ImageView.class);
        assertNotNull(imgAbout);
    }

    @Test
    public void newAdminTest(FxRobot robot) {
        robot.press(KeyCode.ALT).press(KeyCode.E).press(KeyCode.A).press(KeyCode.ENTER);
        robot.lookup("#newAAdminEmail").tryQuery().isPresent();
        robot.clickOn("#newAAdminEmail");
        robot.write("NoviAdmin");
        robot.clickOn("#newAdminPassword");
        robot.write("password");
        robot.clickOn("#buttonAdd");

        assertFalse(theStage.isShowing());
        //baza.deleteAdmin();

    }
    @Test
    public void changePassword(FxRobot robot){
        robot.press(KeyCode.ALT).press(KeyCode.E).press(KeyCode.C);
        robot.clickOn("#emailAdmin");
        robot.write("1");
        robot.clickOn("#odlPassword");
        robot.write("1");
        robot.clickOn("#newPasswordOne");
        robot.write("123");
        robot.clickOn("#newPasswordTwo");
        robot.write("123");
        robot.clickOn("#changePassword");

        assertFalse(theStage.isShowing());
    }

    @Test
    public void newPartyTest(FxRobot robot){
        robot.press(KeyCode.ALT).press(KeyCode.E).press(KeyCode.A).release(KeyCode.A).press(KeyCode.A).release(KeyCode.A).press(KeyCode.A).release(KeyCode.A).press(KeyCode.ENTER);
        robot.clickOn("#txtfldPartyName");
        robot.write("Noova stranka");
        robot.clickOn("#addPartyBtn");

        assertFalse(theStage.isShowing());
    }

    @Test
    public void newCandidatTest(FxRobot robot){
        robot.press(KeyCode.ALT).press(KeyCode.E).press(KeyCode.A).release(KeyCode.A).press(KeyCode.A).release(KeyCode.A).press(KeyCode.ENTER);
        robot.clickOn("#candidateName");
        robot.write("Novica");
        robot.clickOn("#candidateLastname");
        robot.write("Novic");
        robot.clickOn("#candidateDate");
        robot.write("1/1/1998");
        robot.clickOn("#candidateLivingPlace");
        robot.write("Novic");
        robot.clickOn("#candidateParty");
        robot.clickOn("#candidateFunction");

        robot.clickOn("#candidateFunction").press(KeyCode.DOWN).press(KeyCode.ENTER).press(KeyCode.ENTER);
        robot.clickOn("#addCandidatBtn");

        assertFalse(theStage.isShowing());
    }

    @Test
    public void goBackTest(FxRobot robot){
        robot.press(KeyCode.ALT).press(KeyCode.F).press(KeyCode.B);

        Button button = robot.lookup("#loginAdmin").queryAs(Button.class);
        assertNotEquals(null,button);
    }

    @Test
    public void finishVotingTest(FxRobot robot){
        robot.clickOn("#finishVotingBtn");
        robot.press(KeyCode.ENTER);
        assertFalse(theStage.isShowing());
    }

}
