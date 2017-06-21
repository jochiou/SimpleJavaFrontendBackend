/**
 * Created by josephchiou on 6/14/17.
 */
import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class BackendServer implements Runnable{

    public static void main(String [] args){
        ServerSocket serverObj;
        DataObject obj = null;
        Member admin= null;
        DbCommunication dbC = null;
        ServerResponse sr = null;
        UserCommand uc = null;
        ArrayList<ArrayList<String>> resultData = null;  //returning this obj to user
        try{
            serverObj = new ServerSocket(9487);
/*
            for(;;) {
                Socket incoming = serverObj.accept(); //open a socket block that waits for someone 
                ObjectOutputStream oos = new ObjectOutputStream(incoming.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(incoming.getInputStream());

//                obj = (DataObject)ois.readObject();
//                System.out.println("Message received : " + obj.getMessage());
//                obj.setMessage(obj.getMessage()+ "123");
//                System.out.println("Message sent : " + obj.getMessage());
//                oos.writeObject(obj);
//                System.out.println("Time: " + time++);

                System.out.println("before read obj");
               // System.out.println("class Type is: "+ classType);
                admin = (Member) ois.readObject();
                System.out.println("Message recieved: from " + admin.getMessage());
                admin.setMessage("Message recieved by server ...sending back to client");
                oos.writeObject(admin);
            }
*/
            for(;;){
                Socket incoming = serverObj.accept();
                ObjectOutputStream oos = new ObjectOutputStream(incoming.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(incoming.getInputStream());
                uc = (UserCommand)ois.readObject(); //get user command
                //Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");
                dbC = new DbCommunication(uc.getUserID());
                dbC.exeSQL(uc);

                //executeQuery returns result set
                if(uc.getCommandType().toLowerCase().equals("select")){
                    ResultSet rs = dbC.getResultSet();
                    ResultSetMetaData rsm = rs.getMetaData();
                    int columnCount = rsm.getColumnCount();
                    resultData = new ArrayList<>();

                    //result set index starting from 1
                    //first row = column name
                    for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
                        ArrayList<String> columnName = new ArrayList<>();
                        columnName.add(rsm.getColumnName(columnIndex));
                    }
                    //start loading data into resultData from the rs
                    while(rs.next()){
                        ArrayList<String> row = new ArrayList<>();
                        for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++){ //result set index starting from 1
                            row.add(rs.getString(columnIndex));
                        }
                        resultData.add(row);
                    }
                    sr.setRsObj(resultData); //write the arraylist with outputs into the obj that's going to be passed back to user

                }else{ //executeUpdate returns int
                    int rowAffected = dbC.getRowAffected();
                }


                oos.writeObject(sr); //return the response back to user
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
