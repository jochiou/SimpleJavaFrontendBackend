import java.io.Serializable;
import java.sql.*;

/**
 * Created by josephchiou on 6/15/17.
 */
public class Admin extends Member implements Runnable, Serializable {
    int memberID;
    int accessLevel = 10;
    String firstName, lastName;

    //methods to modify other user's data
    public void setFirstName(Connection conn, int targetMemberID, String firstName){
        try {
            String updateString = "update MemberInfo set firstName = ? where memberID = ?";
            PreparedStatement sqlUpdate = conn.prepareStatement(updateString);
            sqlUpdate.setString(1,firstName);
            sqlUpdate.setInt(2,targetMemberID);
            sqlUpdate.executeUpdate();
            conn.commit();
        }catch(SQLException sqle){

        }
    }
    public void setLastName(Connection conn, int targetMemberID, String lastName){
        try {
            String updateString = "update MemberInfo set lastName = ? where memberID = ?";
            PreparedStatement sqlUpdate = conn.prepareStatement(updateString);
            sqlUpdate.setString(1,lastName);
            sqlUpdate.setInt(2,targetMemberID);
            sqlUpdate.executeUpdate();
            conn.commit();
        }catch(SQLException sqle){

        }
    }
    public void run(){

    }
}
