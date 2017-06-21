import java.io.Serializable;
import java.sql.*;

/**
 * Created by josephchiou on 6/15/17.
 */
public class Admin extends Member implements Runnable, Serializable {
    int memberID;
    int accessLevel = 10;
    String firstName, lastName;

    public void run(){

    }
}
