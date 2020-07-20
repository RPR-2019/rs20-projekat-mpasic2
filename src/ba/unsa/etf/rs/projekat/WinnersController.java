package ba.unsa.etf.rs.projekat;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.*;
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
            if(baza.getPresidents().get(i).getVote_number() > baza.getPresidents().get(maxPred).getVote_number())
                maxPred=i;
        }
        int maxPotpred=0;
        for(int i=0;i<baza.getUnderPresidents().size();i++){
            if(baza.getUnderPresidents().get(i).getVote_number() > baza.getUnderPresidents().get(maxPotpred).getVote_number())
                maxPotpred=i;
        }
        int maxDeputy=0;
        for(int i=0;i<baza.getDeputy().size();i++){
            if(baza.getDeputy().get(i).getVote_number() > baza.getDeputy().get(maxDeputy).getVote_number())
                maxDeputy=i;
        }
        //we need to check if some candidats have equal number of votes
        for(int i=0;i<baza.getPresidents().size();i++){
            if(baza.getPresidents().get(i).getVote_number() == baza.getPresidents().get(maxPred).getVote_number())
                counderPresident=counderPresident+1;
        }
        for(int i=0;i<baza.getUnderPresidents().size();i++){
            if(baza.getUnderPresidents().get(i).getVote_number() == baza.getUnderPresidents().get(maxPotpred).getVote_number())
                counterUnderPresident=counterUnderPresident+1;
        }
        for(int i=0;i<baza.getDeputy().size();i++){
            if(baza.getDeputy().get(i).getVote_number() == baza.getDeputy().get(maxDeputy).getVote_number())
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
            winnerPresidentVotes.setText(String.valueOf(baza.getPresidents().get(maxPred).getVote_number()));
            winnerUnderPresidentVotes.setText(String.valueOf(baza.getUnderPresidents().get(maxPotpred).getVote_number()));
            winnerDeputyVotes.setText(String.valueOf(baza.getDeputy().get(maxDeputy).getVote_number()));
        }
    }




}
