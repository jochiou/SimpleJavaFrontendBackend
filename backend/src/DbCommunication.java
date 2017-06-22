import java.sql.*;
import java.util.HashMap;

/**
 * Created by josephchiou on 6/20/17.
 */
public class DbCommunication{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/jcc45";
    static final String DB_URL1 = "jdbc:mysql://sql2.njit.edu/jcc45";
    //db credentials
    static final String USER1 = "jcc45";
    static final String PASS1 = "cs602";
    static final String USER = "root";
    static final String PASS= "root";
    //variables
    String currentUser;
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    ResultSetMetaData rsm = null;
    int rowAffected = 0;

    public DbCommunication(){} //for verifying user
    public DbCommunication(String user){
        this.currentUser = user;
    }// for login user

    public Object[] exeSQL(UserCommand uc) throws SQLException, ClassNotFoundException{

        Object [] resultArr = new Object[2]; //this obj stores the resultSet + resultSetMetaData
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
       switch (uc.getCommandType().toLowerCase().trim()){
           case "select":  // only support select *
                if(uc.getWhereOperator().trim().equals("<")){
                    stmt = conn.prepareStatement("select * from memberInfo where ? < ?");
                }else if(uc.getWhereOperator().trim().equals(">")){
                    stmt = conn.prepareStatement("select * from memberInfo where ? > ?");
                }else if(uc.getWhereOperator().trim().equals("=")){
                    stmt = conn.prepareStatement("select * from memberInfo where ? = ?");
                }
                stmt.setString(1,uc.getConditionTarget());
                stmt.setString(2,uc.getWhereValue());

                //for testing only
                //stmt = conn.prepareStatement("select * from memberInfo");
                System.out.println("Stmt: "+ stmt.toString());
                rs = stmt.executeQuery();
                rsm = rs.getMetaData();
                resultArr[0] = rs;
                resultArr[1] = rsm;
                return resultArr;
           case "insert":
                stmt = conn.prepareStatement("insert into ? (?) values (?)");
                stmt.setString(1, uc.getTargetTable());
                stmt.setString(2,uc.getColumnNameSets());
                stmt.setString(3,uc.getValueSets());
               break;
           case "update":
               if(uc.getWhereOperator().trim().equals("<")){
                   stmt = conn.prepareStatement("update ? set ? = ? where ? < ?");
               }else if(uc.getWhereOperator().trim().equals(">")){
                   stmt = conn.prepareStatement("update ? set ? = ? where ? > ?");
               }else if(uc.getWhereOperator().trim().equals("=")){
                   stmt = conn.prepareStatement("update ? set ? = ? where ? = ?");
               }
                stmt.setString(1,uc.getTargetTable());
                stmt.setString(2,uc.getColumnNameSets());
                stmt.setString(3,uc.getValueSets());
                stmt.setString(4,uc.getConditionTarget());
                stmt.setString(5,uc.getWhereValue());
               break;
           case "delete":
               if(uc.getWhereOperator().trim().equals("<")){
                   stmt = conn.prepareStatement("delete from ? where ? < ?");
               }else if(uc.getWhereOperator().trim().equals(">")){
                   stmt = conn.prepareStatement("delete from ? where ? > ?");
               }else if(uc.getWhereOperator().trim().equals("=")){
                   stmt = conn.prepareStatement("delete from ? where ? = ?");
               }
               stmt.setString(1,uc.getTargetTable());
               stmt.setString(2,uc.getConditionTarget());
               stmt.setString(3,uc.getWhereValue());
               break;
           default:
               System.out.println("user command not found");
               stmt = conn.prepareStatement("select \"COMMAND NOT FOUND\" " );
               break;
       }
        conn.close();
       return resultArr;
    }
    //Resultset might remain null if user's command type is not "select"
    public ResultSet getResultSet() throws NullPointerException{
        return rs;
    }
    public ResultSetMetaData getResultSetMetaData() throws NullPointerException{
        return rsm;
    }

    public Authentication verifyUser(Authentication user)throws SQLException, ClassNotFoundException{
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        user.isLogin=false;

        ResultSet rs;
        stmt = conn.prepareStatement("select * from credential where userid =? and password =?");
        stmt.setString(1, user.getUserName());
        stmt.setString(2,user.getPassword());
        rs = stmt.executeQuery();

        user.setPassword(""); // reset user's password for failed login
        while(rs.next()){
            user.setUserName(rs.getString("userID"));
            user.setMemberType(rs.getString("memberType"));
            user.setLogin(true);
        }
        return user;
    }

    public int getRowAffected(){
        return rowAffected;
    }


}
