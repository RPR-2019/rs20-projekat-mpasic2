package ba.unsa.etf.rs.projekat;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class NewPartyController {
    public TextField txtfldPartyName;
    public VotingDAO baza = new VotingDAO();
    private Party part = new Party(1,"name");

    public void addParty(ActionEvent actionEvent) {
        part.setName_party(txtfldPartyName.getText());
        baza.addParty(part);

    }
}
