package ba.unsa.etf.rs.projekat;

import com.sun.javafx.css.StyleCache;
import org.testfx.framework.junit5.Start;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class VoteServer implements Runnable{
    private int port;
    private boolean stop = false;
    private String name;
    private Thread thread;
    public VoteServer(int port) {
        name = String.valueOf(port);
        thread = new Thread(this, name);
        this.port = port;
        thread.start();
    }

    private boolean startServer(){
        try {
            ServerSocket server = new ServerSocket(port);
            while(!stop){
                Socket connection = server.accept();
                InputStream input = connection.getInputStream();
                OutputStream output = connection.getOutputStream();
                PrintWriter out = new PrintWriter(output, true);
                BufferedReader in = new BufferedReader(new InputStreamReader(input));
                Integer result = Integer.parseInt(in.readLine()) * 100;
                out.println(result);
                in.close();
                out.close();
//                if(input!=null){
//                    Scanner scanner = new Scanner(input);
//                    String body = "";
//                    while(scanner.hasNextLine()){
//                        body+=scanner.nextLine();
//                    }
//                    Integer number = Integer.parseInt(body)*100;
//                    System.out.println(number);
//                    if(output!=null){
//                        System.out.println("Output: " + number);
//                        output.write(number.toString().getBytes());
//                        output.close();
//                    }
//                    input.close();
//                }

                connection.close();
            }
            server.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(port);
            while (!stop) {
                Socket connection = server.accept();
                InputStream input = connection.getInputStream();
                OutputStream output = connection.getOutputStream();
                PrintWriter out = new PrintWriter(output, true);
                BufferedReader in = new BufferedReader(new InputStreamReader(input));
                Integer result = Integer.parseInt(in.readLine()) * 100;
                out.println(result);
                in.close();
                out.close();
//                if(input!=null){
//                    Scanner scanner = new Scanner(input);
//                    String body = "";
//                    while(scanner.hasNextLine()){
//                        body+=scanner.nextLine();
//                    }
//                    Integer number = Integer.parseInt(body)*100;
//                    System.out.println(number);
//                    if(output!=null){
//                        System.out.println("Output: " + number);
//                        output.write(number.toString().getBytes());
//                        output.close();
//                    }
//                    input.close();
//                }

                connection.close();
            }
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        stop = true;
    }
}
