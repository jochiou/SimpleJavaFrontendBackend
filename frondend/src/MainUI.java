import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * Created by josephchiou on 6/15/17.
 */
public class MainUI extends JPanel implements ActionListener{
    //lists of keys of the panels
    public static final String [] panelKeys = {"LOGIN", "MEMBER_PANEL","ADMIN_PANEL"};
    public static final String LOGIN = "LOGIN";
    public static final String MEMBER_PANEL = "MEMBER_PANEL";
    public static final String ADMIN_PANEL = "ADMIN_PANEL";

    //variables
    CardLayout cardLayout;
    JPanel cards;
    JButton b1,b2,b3;
    boolean isAdmin = false;

    public MainUI(String title){
       // super(title);
        JButton jb = new JButton("Testing from MainUI constructor");

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.add(createLoginPanel(), "LOGIN");
        cards.add(createAdminPanel(), "ADMIN_PANEL");
        cards.add(createMemberPanel(),"MEMBER_PANEL");
        this.add(cards);
        if(isAdmin){

        }else{
            //MemberPanel mp = new MemberPanel();
        }
        /*//MemberPanel mp = new MemberPanel();
        b1=new JButton("Apple");
        b2=new JButton("Boy");
        b3=new JButton("Cat");
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);


        JPanel loginCard = new JPanel();
        loginCard.add(new JLabel("Username: "));
        loginCard.add(new JLabel("Password: "));

        //MemberPanel mp = new MemberPanel();

        JPanel card2 = new JPanel();
        card2.add(b2);
        card2.add(new JButton("card2"));

        JPanel card3 = new JPanel();
        card3.add(b3);


       // c.add("a",b1);c.add("b",b2);c.add("c",b3);
       // c.add(mp, "Card 1");
        c.add(card2, "Card 2");
        c.add(card3, "Card 3");
        //cardLayout.next(c);
        this.setSize(500,300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLocationRelativeTo(null);*/


    }

    public JPanel createLoginPanel(){
        JPanel loginPanel = new JPanel();
        JButton btnSubmit  = new JButton("Login");
        JTextField tfUsername = new JTextField();
        JTextField tfPassword = new JTextField();

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapView("MEMBER_PANEL");
            }
        });

        loginPanel.add(new JLabel("Username: "));
        loginPanel.add(tfUsername);
        loginPanel.add(new JLabel("Password: "));
        loginPanel.add(tfPassword);
        loginPanel.add(btnSubmit);


        return loginPanel;
    }

    public JPanel createAdminPanel(){
       JPanel adminPanel = new JPanel();
       JButton btnTest = new JButton("AdminTest");
       adminPanel.add(btnTest);
       return adminPanel;
    }
    public JPanel createMemberPanel(){
        JPanel memberPanel = new JPanel();
        JButton btnTest = new JButton("MemberTest");
        memberPanel.add(btnTest);
        return memberPanel;
    }

    public void swapView(String key) {
        cardLayout.show(cards, key);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cardLayout.next(cards);

    }
}
