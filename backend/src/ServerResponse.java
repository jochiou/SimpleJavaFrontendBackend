import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by josephchiou on 6/20/17.
 */
public class ServerResponse extends DataObject implements Serializable {
    private ArrayList<ArrayList<String>> rsObj = new ArrayList<>();
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
