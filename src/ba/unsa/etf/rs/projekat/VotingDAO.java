package ba.unsa.etf.rs.projekat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class VotingDAO {

   // private static VotingDAO instance;
    private Connection connection;
    private PreparedStatement findAdminQuery,addNewUserQuery,findUserJMBGQuery;


    public VotingDAO() {



        try {
            connection = DriverManager.getConnection("jdbc:sqlite:baza.db");
            //findAdminMailQuery = connection.prepareStatement("SELECT * FROM ADMIN WHERE e_mail = ?");
            //findAdminPasswordQuery = connection.prepareStatement("SELECT * FROM ADMIN WHERE password = ?");
            findAdminQuery = connection.prepareStatement("SELECT * FROM admin;");
            addNewUserQuery = connection.prepareStatement("INSERT INTO voters VALUES (?,?,?)");
            findUserJMBGQuery = connection.prepareStatement("SELECT * FROM voters WHERE jmbg = ?");



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

}
