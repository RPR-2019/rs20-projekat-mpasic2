package ba.unsa.etf.rs.projekat;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Scanner;

import javafx.stage.StageStyle;
import org.mindrot.jbcrypt.BCrypt;


import javax.swing.text.Document;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class MainPageController {

    public Button loginAdmin;
    public Button LoginUser;
    public TextField adminName;
    public PasswordField adminPassword;
    public TextField userNumber;
    public TextField userJMBG;
    public Button closeMainPage;
    public GridPane mainGridLogin;
    public ButtonBar buttonBarPhoto;
    VotingDAO baza = new VotingDAO();
    private Voters glasac = new Voters(1,"","");
    public enum Validate{OK, NOTOK};
    Validate validation;
    public static String userNameGlobal="";

    public static String fromCharCode(int... codePoints) {
        StringBuilder builder = new StringBuilder(codePoints.length);
        for (int codePoint : codePoints) {
            builder.append(Character.toChars(codePoint));
        }
        return builder.toString();
    }

    public static String hashPassword(String password){
        String hashPass = "";

        for(int i=0;i<password.length();i++){
            int br = password.charAt(i);
            int rest = br%16;
            int temp = rest+55;
            hashPass+=fromCharCode(temp);


        }

        return hashPass;
    }



    public void helpAction(ActionEvent actionEvent) throws IOException {
        Stage noviProzor = new Stage();
        Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/help.fxml"));
        noviProzor.setTitle("Kontakt centar");
        Scene scene = new  Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        noviProzor.setScene(scene);
        noviProzor.show();

    }

    public void loginAdminButton(ActionEvent actionEvent) throws IOException, SQLException {

        boolean isThereAdminName=false;
        for(int i=0;i<baza.getAdmin().size();i++) {
            if (adminName.getText().equals(baza.getAdmin().get(i).getEmail())) {
                isThereAdminName=true;
            }
        }

        boolean isThereAdminPassword=false;

        for(int i=0;i<baza.getAdmin().size();i++) {
            if(hashPassword(adminPassword.getText()).equals(baza.getAdmin().get(i).getPassword()))
                    isThereAdminPassword=true;

        }



        if(isThereAdminName && isThereAdminPassword) {
            String pom ="";
            for(int i = 0; i <adminName.getText().length();i++){
                if(adminName.getText().charAt(i)== '@') break;
                pom+=adminName.getText().charAt(i);
            }

            Stage noviProzor = new Stage();
            Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/administrator.fxml"));
            userNameGlobal= pom;
            noviProzor.setTitle("Dobrodošao " + pom);
            Scene scene = new Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            noviProzor.setScene(scene);
            noviProzor.show();

            Stage zatvaranjePoruka = (Stage) adminName.getScene().getWindow();
            zatvaranjePoruka.close();
            baza.closeBase();
        }
        else if(isThereAdminName && !isThereAdminPassword){
            adminPassword.getStyleClass().removeAll("poljeIspravno");
            adminPassword.getStyleClass().add("poljeNijeIspravno");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Greška prilikom registracije");
            alert.setContentText("Neispravna lozinka!");
            alert.showAndWait();
        }

        else{
            adminPassword.getStyleClass().removeAll("poljeIspravno");
            adminPassword.getStyleClass().add("poljeNijeIspravno");
            adminName.getStyleClass().removeAll("poljeIspravno");
            adminName.getStyleClass().add("poljeNijeIspravno");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Greška prilikom registracije");
            alert.setContentText("Neispravni podaci!");
            alert.showAndWait();
        }
    }


    public static boolean isJMBGOkSERVER(String jmbg) throws IOException {
        String adresa = "http://localhost:9090/jmbgcheck?jmbg="+jmbg;
        URL url = new URL(adresa);
        Scanner sc = new Scanner(url.openStream());
        StringBuffer sb = new StringBuffer();
        while(sc.hasNext())
                sb.append(sc.next());
        String res = sb.toString();
        return res.contains("DOBAR");

    }

    public void loginUserButton(ActionEvent actionEvent) throws IOException, SQLException {
        validation = Validate.OK;

        if(userJMBG.getText().isEmpty() || !isJMBGOkSERVER(userJMBG.getText())){
            userJMBG.getStyleClass().removeAll("poljeIspravno");
            userJMBG.getStyleClass().add("poljeNijeIspravno");
            validation = Validate.NOTOK;
        }

        if(userNumber.getText().isEmpty()){
            userNumber.getStyleClass().removeAll("poljeIspravno");
            userNumber.getStyleClass().add("poljeNijeIspravno");
            validation = Validate.NOTOK;
        }


        if(validation == Validate.OK) {

            glasac.setCardNumber(userNumber.getText());
            glasac.setJmbg(userJMBG.getText());
            boolean sveUredu = true;
            for(int i=0;i<baza.getUsers().size();i++){
                if(userNumber.getText().equals(baza.getUsers().get(i).getCardNumber()))
                        sveUredu=false;
                if(userJMBG.getText().equals(baza.getUsers().get(i).getJmbg()))
                    sveUredu=false;
            }

            if(sveUredu) {

                baza.addUser(glasac);
                baza.closeBase();

                Stage noviProzor = new Stage(StageStyle.UNDECORATED);
                Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/votePage.fxml"));
                noviProzor.setTitle("Glasački listić");
                Scene scene = new Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
                noviProzor.setScene(scene);
                noviProzor.show();

                Stage zatvaranjePoruka = (Stage) adminName.getScene().getWindow();
                zatvaranjePoruka.close();
            }

            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška");
                alert.setHeaderText("Greška prilikom registracije");
                alert.setContentText("Ovaj korisnik je vec galasao!");
                alert.showAndWait();
            }
        }

        else{


            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Greška prilikom registracije");
            alert.setContentText("Neispravni podaci!");

            alert.showAndWait();
        }
    }

    public void closeMainPageButton(ActionEvent actionEvent) throws IOException {
        Stage zatvaranjePoruka=(Stage)adminName.getScene().getWindow();
        zatvaranjePoruka.close();
    }



}
