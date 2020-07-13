package ba.unsa.etf.rs.projekat;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

        allVotes.setText(String.valueOf(baza.numberOfVoters()));
        winnerPresident.setText(baza.getPresidents().get(maxPred).getName()+" "+baza.getPresidents().get(maxPred).getLastname());
        winnerUnderPresident.setText(baza.getUnderPresidents().get(maxPotpred).getName()+" "+baza.getUnderPresidents().get(maxPotpred).getLastname());
        winnerDeputy.setText(baza.getDeputy().get(maxDeputy).getName()+" "+baza.getDeputy().get(maxDeputy).getLastname());
        winnerPresidentVotes.setText(String.valueOf(baza.getPresidents().get(maxPred).getVote_number()));
        winnerUnderPresidentVotes.setText(String.valueOf(baza.getUnderPresidents().get(maxPotpred).getVote_number()));
        winnerDeputyVotes.setText(String.valueOf(baza.getDeputy().get(maxDeputy).getVote_number()));
    }




}
