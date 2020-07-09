package ba.unsa.etf.rs.projekat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class VotingDAO {

   // private static VotingDAO instance;
    private Connection connection;
    private PreparedStatement findAdminQuery,addNewUserQuery,findUserQuery,newQuery;


    public VotingDAO() {



        try {
            connection = DriverManager.getConnection("jdbc:sqlite:baza.db");
            //findAdminMailQuery = connection.prepareStatement("SELECT * FROM ADMIN WHERE e_mail = ?");
            //findAdminPasswordQuery = connection.prepareStatement("SELECT * FROM ADMIN WHERE password = ?");
            findAdminQuery = connection.prepareStatement("SELECT * FROM admin;");
            addNewUserQuery = connection.prepareStatement("INSERT INTO voters VALUES (?,?,?);");
            findUserQuery = connection.prepareStatement("SELECT * FROM voters");
            newQuery = connection.prepareStatement("Select MAX(id)+1 from voters; ");



        } catch (SQLException e) {
            e.printStackTrace();
        }

       /* public static void removeInstance() {
            if (instance == null) return;
            instance.close();
            instance = null;
        }*/


       /* public void close() {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/
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
            addNewUserQuery.setString(2,vot.getCard_number());
            addNewUserQuery.setString(3,vot.getJmbg());

            addNewUserQuery.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
