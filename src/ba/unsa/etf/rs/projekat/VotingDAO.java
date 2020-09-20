package ba.unsa.etf.rs.projekat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class VotingDAO {

   // private static VotingDAO instance;

    private Connection connection;
    private static PreparedStatement findAdminQuery,addNewUserQuery,findUserQuery,newQuery,getAllCandidats,getAllPartys,getAllFunctions,QueryAllVotersNumber,newPasswordQuery,addVote,
            addNewAdmin,adminNewQuery,addNewCandidas, newQueryCandidats,howMuchVotesQuery,deleteVoterQuery,addNewPartyQuery,newPartyQuery,
            newQueryVotersDelete, deleteAllVoters, deleteAllCandidats, deleteAdminQuery;

    public VotingDAO() {

        try {
            if (connection == null) {
                connection = DriverManager.getConnection("jdbc:sqlite:baza.db");
                findAdminQuery = connection.prepareStatement("SELECT * FROM admin;");
                addNewUserQuery = connection.prepareStatement("INSERT INTO voters VALUES (?,?,?);");
                findUserQuery = connection.prepareStatement("SELECT * FROM voters");
                newQuery = connection.prepareStatement("SELECT MAX(id)+1 from voters; ");
                getAllCandidats = connection.prepareStatement("SELECT * FROM candidats ORDER BY function, vote_number DESC, name;");
                getAllPartys = connection.prepareStatement("SELECT * FROM party");
                getAllFunctions = connection.prepareStatement("SELECT * FROM functions");
                QueryAllVotersNumber = connection.prepareStatement("SELECT COUNT(*) FROM voters");
                newPasswordQuery = connection.prepareStatement("UPDATE admin SET password = ? WHERE e_mail = ?");
                addNewAdmin = connection.prepareStatement("INSERT INTO admin VALUES(?,?,?);");
                adminNewQuery = connection.prepareStatement("Select MAX(id)+1 from admin; ");
                addVote = connection.prepareStatement("UPDATE candidats SET vote_number = ? WHERE id = ?;");
                addNewCandidas = connection.prepareStatement("INSERT INTO candidats VALUES(?,?,?,?,?,?,?,?);");
                newQueryCandidats = connection.prepareStatement("SELECT MAX(id)+1 from candidats; ");
                howMuchVotesQuery = connection.prepareStatement("SELECT vote_number FROM candidats WHERE id = ?");
                deleteVoterQuery = connection.prepareStatement("DELETE FROM voters WHERE id = ?");
                addNewPartyQuery = connection.prepareStatement("INSERT INTO party VALUES(?,?);");
                newPartyQuery = connection.prepareStatement("Select MAX(id)+1 from party; ");
                newQueryVotersDelete = connection.prepareStatement("SELECT MAX(id) from voters; ");
                deleteAllVoters = connection.prepareStatement("DELETE FROM voters;");
                deleteAllCandidats = connection.prepareStatement("DELETE FROM candidats;");
                deleteAdminQuery = connection.prepareStatement("DELETE FROM admin WHERE id=?");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }



    public ObservableList<Admin> getAdmin(){
        ObservableList<Admin> administratori = FXCollections.observableArrayList();

        try {
            ResultSet rs = findAdminQuery.executeQuery();

            while(rs.next()) {
                Admin noviAdmin = new Admin(rs.getInt(1),rs.getString(2),rs.getString(3));
                administratori.add(noviAdmin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return administratori;
    }

    public ObservableList<Functions> getFunction(){
        ObservableList<Functions> funkcijeKandidata = FXCollections.observableArrayList();

        try {
            ResultSet rs = getAllFunctions.executeQuery();

            while(rs.next()) {
                Functions funkcije = new Functions(rs.getInt(1),rs.getString(2));
                funkcijeKandidata.add(funkcije);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return funkcijeKandidata;
    }

    public ObservableList<Party> getParty(){
        ObservableList<Party> svePartije = FXCollections.observableArrayList();

        try {
            ResultSet rs = getAllPartys.executeQuery();

            while(rs.next()) {
                Party partije = new Party(rs.getInt(1),rs.getString(2));
                svePartije.add(partije);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return svePartije;
    }

    public ObservableList<Candidats> getCandidats(){
        ObservableList<Candidats> kandidati = FXCollections.observableArrayList();

        try {
            ResultSet rs = getAllCandidats.executeQuery();

            while(rs.next()) {
                Party partija = new Party(0,"ime");
                for(int i=0;i<getParty().size();i++){
                    if(getParty().get(i).getId() == rs.getInt(2))
                        partija=getParty().get(i);
                }
                Functions funkcija = new Functions(0,"ime");
                for(int i=0;i<getFunction().size();i++){
                    if(getFunction().get(i).getId() == rs.getInt(7))
                        funkcija=getFunction().get(i);
                }
                Candidats noviKandidati = new Candidats(rs.getInt(1),partija,rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),rs.getString(6),funkcija,rs.getInt(8));
                kandidati.add(noviKandidati);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kandidati;
    }


    public ObservableList<Voters> getUsers(){
        ObservableList<Voters> korisniciGlasaci = FXCollections.observableArrayList();

        try {
            ResultSet rs = findUserQuery.executeQuery();

            while(rs.next()) {
                Voters noviGlasac = new Voters(rs.getInt(1),rs.getString(2),rs.getString(3));
                korisniciGlasaci.add(noviGlasac);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return korisniciGlasaci;
    }

    public void addUser(Voters vot){

        try {
            ResultSet rs = newQuery.executeQuery();

            if (rs.next())
                vot.setId(rs.getInt(1));
            else
                vot.setId(1);

            addNewUserQuery.setInt(1,vot.getId());
            addNewUserQuery.setString(2,vot.getCardNumber());
            addNewUserQuery.setString(3,vot.getJmbg());

            addNewUserQuery.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int numberOfVoters(){
        int brojGlasaca=0;
        try {
            ResultSet rs = QueryAllVotersNumber.executeQuery();
            brojGlasaca = rs.getInt(1);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brojGlasaca;
    }

    public ObservableList<Candidats> getPresidents(){
        ObservableList<Candidats> predsjednici = FXCollections.observableArrayList();

        for(int i=0;i<getCandidats().size();i++){
            if(getCandidats().get(i).getFunctions().getId() == 1)
                predsjednici.add(getCandidats().get(i));
        }
        return predsjednici;
    }

    public ObservableList<Candidats> getUnderPresidents(){
        ObservableList<Candidats> potpredsjednici = FXCollections.observableArrayList();

        for(int i=0;i<getCandidats().size();i++){
            if(getCandidats().get(i).getFunctions().getId() == 2)
                potpredsjednici.add(getCandidats().get(i));
        }
        return potpredsjednici;
    }


    public ObservableList<Candidats> getDeputy(){
        ObservableList<Candidats> zamjenici = FXCollections.observableArrayList();

        for(int i=0;i<getCandidats().size();i++){
            if(getCandidats().get(i).getFunctions().getId() == 3)
                zamjenici.add(getCandidats().get(i));
        }
        return zamjenici;
    }

    public void setNewPassword(String email, String newPassword){
        try {
            newPasswordQuery.setString(2,email);
            newPasswordQuery.setString(1,newPassword);


            newPasswordQuery.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addAdmin(Admin ad){

        try {
            ResultSet rs = adminNewQuery.executeQuery();

            if (rs.next())
                ad.setId(rs.getInt(1));
            else
                ad.setId(1);

            addNewAdmin.setInt(1,ad.getId());
            addNewAdmin.setString(2,ad.getEmail());
            addNewAdmin.setString(3,ad.getPassword());

            addNewAdmin.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addCandidate(Candidats cand){

        try {
            ResultSet rs = newQueryCandidats.executeQuery();

            if (rs.next())
                cand.setId(rs.getInt(1));
            else
                cand.setId(1);

            addNewCandidas.setInt(1,cand.getId());
            addNewCandidas.setInt(2,cand.getPartyId().getId());
            addNewCandidas.setString(3,cand.getName());
            addNewCandidas.setString(4,cand.getLastname());
            addNewCandidas.setDate(5, Date.valueOf(cand.getBirthDate()));
            addNewCandidas.setString(6,cand.getLivingPlace());
            addNewCandidas.setInt(7,cand.getFunctions().getId());
            addNewCandidas.setInt(8,cand.getVoteNumber());

            addNewCandidas.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addVotes(Candidats cand){
        try {
            addVote.setInt(1,cand.getVoteNumber()+1);
            addVote.setInt(2,cand.getId());

            addVote.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void closeBase() throws SQLException {
        connection.close();
        connection = null;
    }

    public void deleteVoters(int id){
        try {
            deleteVoterQuery.setInt(1,id);


            deleteVoterQuery.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addParty(Party part){

        try {
            ResultSet rs = newPartyQuery.executeQuery();

            if (rs.next())
                part.setId(rs.getInt(1));
            else
                part.setId(1);

            addNewPartyQuery.setInt(1,part.getId());
            addNewPartyQuery.setString(2,part.getName_party());


            addNewPartyQuery.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEverything(){

        try {
            deleteAllVoters.execute();
            deleteAllCandidats.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void deleteAdmin(){
        int id = getAdmin().size();
        try {
            deleteAdminQuery.setInt(1,id);
            deleteAdminQuery.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Candidats getWinnerPresident(){
        Candidats votedPresident = getPresidents().get(0);

        int maxPred=0;
        for(int i=0;i<getPresidents().size();i++){
            if(getPresidents().get(i).getVoteNumber() > getPresidents().get(maxPred).getVoteNumber())
                maxPred=i;

        }
        votedPresident.setId(maxPred);
        return votedPresident;
    }

    public Candidats getWinnerUnderPresident(){
        Candidats votedUnderPresident = getUnderPresidents().get(0);

        int maxPred=0;
        for(int i=0;i<getUnderPresidents().size();i++){
            if(getUnderPresidents().get(i).getVoteNumber() > getUnderPresidents().get(maxPred).getVoteNumber())
                maxPred=i;

        }
        votedUnderPresident.setId(maxPred);
        return votedUnderPresident;
    }

    public Candidats getWinnerDeputy(){
        Candidats votedPresident = getPresidents().get(0);

        int maxPred=0;
        for(int i=0;i<getPresidents().size();i++){
            if(getPresidents().get(i).getVoteNumber() > getPresidents().get(maxPred).getVoteNumber())
                maxPred=i;

        }
        votedPresident.setId(maxPred);
        return votedPresident;
    }

}
