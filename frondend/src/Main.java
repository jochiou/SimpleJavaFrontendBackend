/**
 * Created by josephchiou on 6/15/17.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main extends JPanel implements ActionListener{
    static JButton btnSubmit;
    static JTextField tfPassword, tfUsername;
    static MainUI mu;

    public Main(){
        mu = new MainUI("main UI");

       // ButtonPanel buttonPanel = new ButtonPanel();
      //  buttonPanel.setMainUI(mu);


       // buttonPanel.setBorder(BorderFactory.createTitledBorder("Button Panel"));
        mu.setBorder(BorderFactory.createTitledBorder("CS602"));
        setLayout(new BorderLayout());
        add(mu,BorderLayout.CENTER);
       // add(buttonPanel, BorderLayout.SOUTH);

    }
    public static void genUI(){
        JFrame frame = new JFrame("Client app");
        frame.setSize(800,500);
        frame.getContentPane().add(new Main());
  //      frame.getContentPane().add(mu,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    public static void main(String [] args){
       // MainUI mu = new MainUI("test");

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                genUI();
            }
        });

       // mu.init();

      // Login l = new Login("Login");
       //l.init();

//       Member admin = new Admin();
//       admin.setFirstName("Joseph");
//       //admin.setLastName("Chiou");
//       admin.setMessage(admin.getFirstName()+ " from client");
//       System.out.println("Message sent: " + admin.getMessage());
//
//
//       try {
//           Socket socketToServer = new Socket("localhost", 9487);
//           ObjectOutputStream oos = new ObjectOutputStream(socketToServer.getOutputStream());
//           ObjectInputStream ois = new ObjectInputStream(socketToServer.getInputStream());
//           System.out.println("before write");
//           oos.writeObject(admin);
//           admin = (Member)ois.readObject();
//       }catch(Exception e){
//            System.out.println(e);
//       }
//       System.out.println(admin.getMessage());
//
//       //sql testing
//        try {
//            Connection conn;
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost/CS602", "root", "root");
//            System.out.println("After establish connection");
//
//        }catch(Exception e){
//            System.out.println(e);
//        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

/*class ButtonPanel extends JPanel {
    private MainUI mainUI;

    public ButtonPanel() {
        setLayout(new GridLayout(1, 0, 10, 0));
        // go through String array making buttons
        for (final String keyText : MainUI.panelKeys) {
            JButton btn = new JButton(keyText);
            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (mainUI != null) {
                        mainUI.swapView(keyText);
                    }
                }
            });
            add(btn);
        }
    }

    public void setMainUI(MainUI mainUI) {
        this.mainUI = mainUI;
    }
}*/
