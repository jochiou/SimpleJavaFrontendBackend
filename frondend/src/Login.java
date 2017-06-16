import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;

/**
 * Created by josephchiou on 6/15/17.
 */
public class Login extends JFrame{
    String userid, password;
    public Login(String title){
        super(title);
    }
    public void init(){
        JPanel control = new JPanel();
        GridLayout gridLayout = new GridLayout(3,3,5,5);
        this.setLayout(gridLayout);
        JLabel lblUserid = new JLabel("User id:");
        JLabel lblPassword = new JLabel("Password:");
        JLabel lblErrorMsg = new JLabel();
        JTextField tfUserid = new JTextField();
        JTextField tfPassword = new JTextField();
        JButton btnLogin = new JButton("Login");


        this.setSize(300,200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(lblUserid);
        this.add(tfUserid);
        this.add(lblPassword);
        this.add(tfPassword);
        this.add(lblErrorMsg);
        this.add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean correct = true;
                userid = tfUserid.getText().trim();
                password = tfPassword.getText().trim();

                // verify()
                if(userid.equals(password)) {
                    dispose();
                    MainUI mainPage = new MainUI("CS602");
                    //mainPage.init();
                    try {
                        Member m = new Member();
                        Connection conn;
                        Class.forName("com.mysql.jdbc.Driver");
                        conn = DriverManager.getConnection("jdbc:mysql://localhost/CS602", "root", "root");
                        System.out.println("After establish connection");
                        List<Member> memberList = m.getAllMemberInfo(conn);

                    }catch(Exception e1){
                        System.out.println(e1);
                    }
                    //mainPage.setVisible(true);

                    //dispose();
                   // return true;
                }else{
                    tfUserid.setText("");
                    tfPassword.setText("");
                    lblErrorMsg.setText("user id and password doesnt match");
                    actionPerformed(e);
                }

            }
        });
       // getContentPane().add(lblUserid, new  GridLayout(0,3));

    }


}
