import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

/**
 * Created by josephchiou on 6/15/17.
 */
//convert data into DataObject
public class Encoding {

    //from row data to obj
    public static void encoding(List<Member> memberList, Socket goingOut){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(goingOut.getOutputStream());
            oos.writeObject(memberList);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
