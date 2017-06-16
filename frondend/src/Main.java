/**
 * Created by josephchiou on 6/15/17.
 */
import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String [] args){

       Login l = new Login("Login");
       l.init();

       Member admin = new Admin();
       admin.setFirstName("Joseph");
       //admin.setLastName("Chiou");
       admin.setMessage(admin.getFirstName()+ " from client");
       System.out.println("Message sent: " + admin.getMessage());


       try {
           Socket socketToServer = new Socket("localhost", 9487);
           ObjectOutputStream oos = new ObjectOutputStream(socketToServer.getOutputStream());
           ObjectInputStream ois = new ObjectInputStream(socketToServer.getInputStream());
           System.out.println("before write");
           oos.writeObject(admin);
           admin = (Member)ois.readObject();
       }catch(Exception e){
            System.out.println(e);
       }
       System.out.println(admin.getMessage());

       //sql testing
        try {
            Connection conn;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/CS602", "root", "root");
            System.out.println("After establish connection");

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
