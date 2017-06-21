import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by josephchiou on 6/15/17.
 */

public class Member implements Serializable {
    int memberID;
    int accessLevel; //from 1 to 10 when 10 is the admin
    String firstName, lastName, message;

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }

    //member can browse other people's info

    //passing/getting message for testing
    public String getMessage(){
        return message;
    }
    public void setMessage(String msg){
        this.message = msg;
    }

    //this method meant to be private; no one can change anyone's id; it's used when retrieving data from database only
    private void setMemberID(int id){
        this.memberID = id;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

}
