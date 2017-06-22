import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class Main{
    public static void main(String[] args){
        MainUI panel = new MainUI("cardPanel");
        JFrame frame = new JFrame("Client app");
        frame.setSize(800,500);
        //JPanel test = panel.createAdminPanel();
       // test.setLayout(new BorderLayout());
        panel.setLayout(new BorderLayout());

        frame.add(panel);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //frame.setLayout(null);

    }

    public static DataObject connectServer(DataObject dataobject) {
        DataObject response = null;
        try {
            Socket socketToServer = new Socket("localhost", 9487);
            ObjectOutputStream oos = new ObjectOutputStream(socketToServer.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socketToServer.getInputStream());
            System.out.println("before write");
            oos.writeObject(dataobject);

            response = (DataObject) ois.readObject();
            if(response instanceof ServerResponse){
                System.out.println("response is a server response");
                response = (ServerResponse)response;
            }else if(response instanceof Authentication){
                System.out.println("response for authentication");
                response = (Authentication)response;
            }
            System.out.println("got response back from server");
            return response;
        } catch (Exception e) {
            System.out.println(e);
        }
        return response;
    }


}