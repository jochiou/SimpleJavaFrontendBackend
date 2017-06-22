/**
 * Created by josephchiou on 6/21/17.
 */
import java.io.Serializable;
import java.util.ArrayList;

public class ServerResponse extends DataObject implements Serializable {
    private ArrayList<ArrayList<String>> rsObj;
    public ServerResponse(){
        rsObj = new ArrayList<>();
    }

    public void setRsObj(ArrayList<ArrayList<String>> rsObj) {
        this.rsObj = rsObj;
    }
    public ArrayList<ArrayList<String>> getRsObj() {
        return rsObj;
    }
}
