package ba.unsa.etf.rs.projekat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VotingDAO {

    private static VotingDAO instance;
    private Connection connection;
    private PreparedStatement findAdminMailQuery,findAdminPasswordQuery,findUserCardNumberQuery,findUserJMBGQuery;


    private VotingDAO() {

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:el_baza.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            findAdminMailQuery = connection.prepareStatement("SELECT * FROM ADMIN WHERE e_mail = ?");
            findAdminPasswordQuery = connection.prepareStatement("SELECT * FROM ADMIN WHERE password = ?");
            findUserCardNumberQuery = connection.prepareStatement("SELECT * FROM VOTERS WHERE card_number = ?");
            findUserJMBGQuery = connection.prepareStatement("SELECT * FROM VOTERS WHERE jmbg = ?");

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



}
