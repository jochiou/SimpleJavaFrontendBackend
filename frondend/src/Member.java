import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by josephchiou on 6/15/17.
 */

public class Member extends DataObject implements Serializable {
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

    public List<Member> getMemberInfo(Connection conn, int memberID){
        List<Member> memberList = null;
        try{
            String selectString ="select * from MemberInfo where memberID =?";
            PreparedStatement sqlSelect = conn.prepareStatement(selectString);
            sqlSelect.setInt(1, memberID);
            ResultSet rs = sqlSelect.executeQuery();   //copy the selected table to rs
            while(rs.next()){
                //instantiate a temp member object, filled in data retrieved from db and add it to the list
                Member m = new Member();
                m.setMemberID(rs.getInt("memberID"));
                m.setFirstName(rs.getString("firstName"));
                m.setLastName(rs.getString("lastName"));
                memberList.add(m);
            }
        }catch(Exception e){
        }
        return memberList;
    }

    public List<Member> getAllMemberInfo(Connection conn){
        List<Member> memberList = null;
        try{
            String selectString ="select * from MemberInfo";
            PreparedStatement sqlSelect = conn.prepareStatement(selectString);

            ResultSet rs = sqlSelect.executeQuery();   //copy the selected table to rs
            while(rs.next()){
                Member m = new Member();
                m.setMemberID(rs.getInt("memberID"));
                m.setFirstName(rs.getString("firstName"));
                m.setLastName(rs.getString("lastName"));
                memberList.add(m);
            }
        }catch(Exception e){
        }
        return memberList;
    }


    //member can only modify his/her own record

    //this method meant to be private; no one can change anyone's id; it's used when retrieving data from database only
    private void setMemberID(int id){
        this.memberID = id;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setFirstName(Connection conn, String firstName){
        this.firstName = firstName;

        try {
            String updateString = "update MemberInfo set firstName = ? where memberID = ?";
            PreparedStatement sqlUpdate = conn.prepareStatement(updateString);
            sqlUpdate.setString(1,firstName);
            sqlUpdate.setInt(2,this.memberID);
            sqlUpdate.executeUpdate();
            conn.commit();
        }catch(SQLException sqle){

        }
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setLastName(Connection conn, String lastName){
        this.lastName = lastName;

        try {
            String updateString = "update MemberInfo set lastName = ? where memberID = ?";
            PreparedStatement sqlUpdate = conn.prepareStatement(updateString);
            sqlUpdate.setString(1,lastName);
            sqlUpdate.setInt(2,this.memberID);
            sqlUpdate.executeUpdate();
            conn.commit();
        }catch(SQLException sqle){

        }
    }

}
