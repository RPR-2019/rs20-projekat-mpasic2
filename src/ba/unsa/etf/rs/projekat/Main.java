package ba.unsa.etf.rs.projekat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class Main extends Application {

    public static Admin currentlyLoggedInUser;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainPage.fxml"));
        primaryStage.setTitle("E-glasanje");
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.show();
        primaryStage.setResizable(false);
        currentlyLoggedInUser = null;
        VoteServer server = new VoteServer(2345);

        try {
            Socket socket = new Socket("localhost", 2345);
            OutputStream outputStream = socket.getOutputStream();
            String body = "766";
            PrintWriter out = new PrintWriter(outputStream, true);
            out.println(body);
            InputStream inputStream = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String response = "";
            System.out.println("Response: " + in.readLine());
            out.close();
            in.close();
            socket.close();
        } catch (IOException ignored) {

        }
        primaryStage.setOnHiding(event ->{
            server.stop();
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
