/**
 * Created by josephchiou on 6/14/17.
 */
import javax.xml.crypto.Data;
import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class BackendServer implements Runnable{

    public static void main(String [] args){
        ServerSocket serverObj;
        DbCommunication dbC;
        ServerResponse sr;
        UserCommand uc;
        Authentication user;
        ArrayList<ArrayList<String>> resultData = null;  //returning this obj to user

        try{
            serverObj = new ServerSocket(9487);
            for(;;){

                System.out.println("New round of socket.accept :");
                Socket incoming = serverObj.accept();
                sr = new ServerResponse(); //one serverResponse instance per socket
                ObjectOutputStream oos = new ObjectOutputStream(incoming.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(incoming.getInputStream());
                System.out.println("Before getting user command");

                //get something from user; can be UserCommand or Authentication
                DataObject sthFromUser = (DataObject)ois.readObject();
                if(sthFromUser instanceof UserCommand){
                    uc = (UserCommand)sthFromUser; //get user command

                    System.out.println("Init db connection");
                    dbC = new DbCommunication(uc.getUserID());
                    Object [] resultArr = dbC.exeSQL(uc); //resultset and resultSetMetaData

                    //executeQuery returns result set
                    if(uc.getCommandType().toLowerCase().equals("select")){
                        // ResultSet rs = dbC.getResultSet();
                        // ResultSetMetaData rsm = rs.getMetaData();
                        ResultSet rs = (ResultSet)resultArr[0];
                        ResultSetMetaData rsm = (ResultSetMetaData)resultArr[1];
                        int columnCount = rsm.getColumnCount();
                        resultData = new ArrayList<>();

                        //result set index starting from 1
                        //first row = column name
                        ArrayList<String> columnName = new ArrayList<>();
                        for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
                            columnName.add(rsm.getColumnName(columnIndex));
                        }
                        resultData.add(columnName);
                        //start loading data into resultData from the rs
                        while(rs.next()){
                            ArrayList<String> row = new ArrayList<>();
                            for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++){ //result set index starting from 1
                                row.add(rs.getString(columnIndex));
                            }
                            resultData.add(row);
                        }
                        System.out.println(resultData);
                        sr.setRsObj(resultData); //write the arraylist with outputs into the obj that's going to be passed back to user

                    }else{ //executeUpdate returns int
                        int rowAffected = dbC.getRowAffected();
                    }
                    oos.writeObject(sr); //return the response back to user

                }else if(sthFromUser instanceof Authentication){ //get trigger when user trying to login
                   user = (Authentication) sthFromUser;
                   dbC = new DbCommunication();
                   Authentication verifiedUser = dbC.verifyUser(user);
                   oos.writeObject(verifiedUser);
                }


            }
        }catch(Exception e){
                e.printStackTrace();
                System.out.println(e);
        }
    }

    @Override
    //for multi thread
    public void run() {

    }
}
