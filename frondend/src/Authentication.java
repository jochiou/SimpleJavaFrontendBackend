/**
 * Created by josephchiou on 6/21/17.
 */
import java.io.Serializable;
public class Authentication extends DataObject implements Serializable {
    String userName;
    String password;
    String memberType;
    boolean isLogin;

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }


    public boolean isLogin() {
        return isLogin;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getMemberType() {
        return memberType;
    }



}
