package ba.unsa.etf.rs.projekat;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class WinnersController implements Initializable {


    public TextField winnerPresident;
    public TextField winnerUnderPresident;
    public TextField winnerDeputy;
    public TextField winnerPresidentVotes;
    public TextField winnerUnderPresidentVotes;
    public TextField winnerDeputyVotes;
    public TextField allVotes;
    public VotingDAO baza = new VotingDAO();
    int counderPresident = 0;
    int counterUnderPresident = 0;
    int conterDeputy = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //checking the base to find who of candidats has the most of votes
        int maxPred=0;
        for(int i=0;i<baza.getPresidents().size();i++){
            if(baza.getPresidents().get(i).getVoteNumber() > baza.getPresidents().get(maxPred).getVoteNumber())
                maxPred=i;
        }
        int maxPotpred=0;
        for(int i=0;i<baza.getUnderPresidents().size();i++){
            if(baza.getUnderPresidents().get(i).getVoteNumber() > baza.getUnderPresidents().get(maxPotpred).getVoteNumber())
                maxPotpred=i;
        }
        int maxDeputy=0;
        for(int i=0;i<baza.getDeputy().size();i++){
            if(baza.getDeputy().get(i).getVoteNumber() > baza.getDeputy().get(maxDeputy).getVoteNumber())
                maxDeputy=i;
        }
        //we need to check if some candidats have equal number of votes
        for(int i=0;i<baza.getPresidents().size();i++){
            if(baza.getPresidents().get(i).getVoteNumber() == baza.getPresidents().get(maxPred).getVoteNumber())
                counderPresident=counderPresident+1;
        }
        for(int i=0;i<baza.getUnderPresidents().size();i++){
            if(baza.getUnderPresidents().get(i).getVoteNumber() == baza.getUnderPresidents().get(maxPotpred).getVoteNumber())
                counterUnderPresident=counterUnderPresident+1;
        }
        for(int i=0;i<baza.getDeputy().size();i++){
            if(baza.getDeputy().get(i).getVoteNumber() == baza.getDeputy().get(maxDeputy).getVoteNumber())
                conterDeputy=conterDeputy+1;
        }

        if(counderPresident>1 || counterUnderPresident>1 || conterDeputy>1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText("Postoje kandidati koji imaju jednak broj glasova!");
            alert.setContentText("Molimo kontaktirajte administraotora");

            alert.showAndWait();

        }
        else {
            //setting text to textfields
            allVotes.setText(String.valueOf(baza.numberOfVoters()));
            winnerPresident.setText(baza.getPresidents().get(maxPred).getName() + " " + baza.getPresidents().get(maxPred).getLastname());
            winnerUnderPresident.setText(baza.getUnderPresidents().get(maxPotpred).getName() + " " + baza.getUnderPresidents().get(maxPotpred).getLastname());
            winnerDeputy.setText(baza.getDeputy().get(maxDeputy).getName() + " " + baza.getDeputy().get(maxDeputy).getLastname());
            winnerPresidentVotes.setText(String.valueOf(baza.getPresidents().get(maxPred).getVoteNumber()));
            winnerUnderPresidentVotes.setText(String.valueOf(baza.getUnderPresidents().get(maxPotpred).getVoteNumber()));
            winnerDeputyVotes.setText(String.valueOf(baza.getDeputy().get(maxDeputy).getVoteNumber()));
        }
    }

    public void actionWriteXML(javafx.event.ActionEvent actionEvent) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Zapiši XML datoteku");
            Stage stage = (Stage)winnerPresident.getScene().getWindow();
            File file = fileChooser.showSaveDialog(stage);
            if (file == null)
                return;

            XMLFormat xml = new XMLFormat();
            xml.zapisi(file);


        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong file format");
            alert.setContentText("An error occured during file save.");
            alert.showAndWait();
        }
    }
}
