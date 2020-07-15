package ba.unsa.etf.rs.projekat;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class NewCandidatController {

    public TextField candidateParty;
    public TextField candidateName;
    public TextField candidateLastname;
    public TextField candidateLivingPlace;
    public TextField candidateFunction;
    public DatePicker candidateDate;
    public VotingDAO baza = new VotingDAO();
    public Party part = new Party(5,"aaa");
    public Functions fun = new Functions(4,"kralj");
    public Candidats cand = new Candidats(1,part,"1","2", LocalDate.now(),"1",fun,3);


    public void sacuvajAction(ActionEvent actionEvent) {
        int numbefOfCandidats = baza.getCandidats().size();

        cand.setId(numbefOfCandidats+1);
        cand.setParty_id(part);
        cand.setName(candidateName.getText());
        cand.setLastname(candidateLastname.getText());
        cand.setBirth_date(candidateDate.getValue());
        cand.setLiving_place(candidateLivingPlace.getText());
        cand.setFunctions(fun);
        cand.setVote_number(0);

        baza.addCandidate(cand);


        
    }
}
