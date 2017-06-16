/**
 * Created by josephchiou on 6/14/17.
 */
import java.io.*;
import java.net.*;
public class BackendServer implements Runnable{

    public static void main(String [] args){
        ServerSocket serverObj;
        DataObject obj = null;
        Member admin= null;
        try{
            serverObj = new ServerSocket(9487);

            int time=0;

            for(;;) {
                Socket incoming = serverObj.accept(); //open a socket block that waits for someone 
                ObjectOutputStream oos = new ObjectOutputStream(incoming.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(incoming.getInputStream());


                /*
                obj = (DataObject)ois.readObject();
                System.out.println("Message received : " + obj.getMessage());
                obj.setMessage(obj.getMessage()+ "123");
                System.out.println("Message sent : " + obj.getMessage());
                oos.writeObject(obj);
                System.out.println("Time: " + time++);
                */

                System.out.println("before read obj");
               // System.out.println("class Type is: "+ classType);
                admin = (Member) ois.readObject();
                System.out.println("Message recieved: from " + admin.getMessage());
                admin.setMessage("Message recieved by server ...sending back to client");
                oos.writeObject(admin);
            }

        }catch(Exception e){
                System.out.println(e);
        }


    }

    @Override
    //for multi thread
    public void run() {

    }
}
