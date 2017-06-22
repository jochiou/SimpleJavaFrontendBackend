import com.mysql.fabric.Server;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by josephchiou on 6/15/17.
 */
public class MainUI extends JPanel implements ActionListener{
    //lists of keys of the panels
    public static enum panelKeys {LOGIN, MEMBER_PANEL,ADMIN_PANEL};
   // public static final String [] panelKeys = {"LOGIN", "MEMBER_PANEL","ADMIN_PANEL"};
    public static final String LOGIN = "LOGIN";
    public static final String MEMBER_PANEL = "MEMBER_PANEL";
    public static final String ADMIN_PANEL = "ADMIN_PANEL";
    public static String LoginUser;

    //variables
    CardLayout cardLayout;
    JPanel cards;
   // JButton b1,b2,b3;
    boolean isAdmin = false;

    public MainUI(String title){
       // super(title);
        JButton jb = new JButton("Testing from MainUI constructor");

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.setSize(800,500);
        cards.add(createLoginPanel(), panelKeys.LOGIN.toString());
        cards.add(createAdminPanel(), panelKeys.ADMIN_PANEL.toString());
        cards.add(createMemberPanel(),panelKeys.MEMBER_PANEL.toString());
        this.add(cards);
        if(isAdmin){

        }else{
            //MemberPanel mp = new MemberPanel();
        }
    }

    public JPanel createLoginPanel(){

        JPanel loginPanel = new JPanel();
        loginPanel.setBorder(BorderFactory.createTitledBorder("Login Page"));
       // loginPanel.setLayout(new BorderLayout());
        loginPanel.setSize(800,500);
        JButton btnSubmit  = new JButton("Login");
        JTextField tfUsername = new JTextField("",25);
        JTextField tfPassword = new JTextField("",25);

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Authentication user = new Authentication();
                String username = tfUsername.getText().trim().toLowerCase();
                String password = tfPassword.getText().trim();
                user.setUserName(username);
                user.setPassword(password);
                user = (Authentication)Main.connectServer(user);

                if(user.isLogin){
                    switch(user.memberType.toLowerCase()){
                        case "admin":
                            System.out.println(user.userName+ " " + user.memberType);
                                swapView(panelKeys.ADMIN_PANEL.toString());
                            break;

                        case "member":
                                System.out.println(user.userName+ " " + user.memberType);
                                swapView(panelKeys.MEMBER_PANEL.toString());
                            break;
                    }
                }else{
                    System.out.println(user.userName+ " " + user.memberType);
                    System.out.println("Login fails");
                }

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
       adminPanel.setBorder(BorderFactory.createTitledBorder("User Type: admin"));
       adminPanel.setLayout(new BorderLayout());
       adminPanel.setSize(800,500);
       JButton btnTest = new JButton("AdminTest");
       adminPanel.add(btnTest, BorderLayout.NORTH);


        JTabbedPane queryPanel = createQueryPanel();
       // JPanel selectTab = createSelectPanel();
        JPanel insertTab = createInsertPanel();
        JPanel updateTab = createUpdatePanel();
        JPanel deleteTab = createDeletePanel();
     //   queryPanel.add("Browse data", selectTab);
        queryPanel.add("Add data", insertTab);
        queryPanel.add("Update data", updateTab);
        queryPanel.add("Delete data", deleteTab);
        queryPanel.setBounds(50,50,200,200);
        queryPanel.setSize(getPreferredSize());
        adminPanel.add(queryPanel, BorderLayout.CENTER);

        btnTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // swapView("ADMIN_PANEL");
                UserCommand uc = new UserCommand("jo","select","firstName","memberInfo","0","=","0");
                Main.connectServer(uc);
            }
        });
       return adminPanel;
    }
    public JPanel createMemberPanel(){
        JPanel memberPanel = new JPanel();
        memberPanel.setBorder(BorderFactory.createTitledBorder("User Type: member"));
        JButton btnTest = new JButton("MemberTest");
        memberPanel.add(btnTest);

        JTabbedPane queryPanel = createQueryPanel();
//        JPanel selectTab = createSelectPanel();
        JPanel insertTab = createInsertPanel();
//        queryPanel.addTab("Browse data", selectTab);
        queryPanel.addTab("Add data", insertTab);
        queryPanel.setBounds(50,50,200,200);
        memberPanel.add(queryPanel);
        return memberPanel;
    }

    public JTabbedPane createQueryPanel(){
        JTabbedPane queryPanel = new JTabbedPane();

        return queryPanel;
    }

    public JPanel createSelectPanel(){
        JPanel selectTab = new JPanel();
        JLabel lblSelect = new JLabel("Select: ");
        JTextField tfSelect = new JTextField();
        selectTab.add(lblSelect);
        selectTab.add(tfSelect);

        UserCommand uc = new UserCommand("jo","select","firstName","memberInfo","0","=","0");
        ServerResponse sr = (ServerResponse)Main.connectServer(uc);
        ArrayList<ArrayList<String>> resultArr = sr.getRsObj();

        ArrayList<String> columnName = resultArr.get(0);
        resultArr.remove(0);
        //populating JTable based on the returning arrayList
        JTable table = new JTable(resultArr.size()-1,resultArr.get(0).size()){
            //overriding this method to make cells not editable
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        DefaultTableModel model =(DefaultTableModel)table.getModel();
        System.out.println("Populating table");
        model.addRow(columnName.toArray());

        while(!resultArr.isEmpty()){
            model.addRow(resultArr.get(0).toArray());
            resultArr.remove(0);
        }
        selectTab.add(table);

        return selectTab;
    }
    public JPanel createInsertPanel(){
        JPanel insertTab = new JPanel();
        JLabel lblInsert = new JLabel("Insert: ");
        JTextField tfInsert = new JTextField();
        insertTab.add(lblInsert);
        insertTab.add(tfInsert);
        return insertTab;
    }
    public JPanel createUpdatePanel(){
        JPanel updateTab = new JPanel();
        JLabel lblUserID = new JLabel("UserID: ");
        JTextField tfUpdate = new JTextField();
        updateTab.add(lblUserID);
        updateTab.add(tfUpdate);
        return updateTab;
    }

    public JPanel createDeletePanel(){
        JPanel deleteTab = new JPanel();
        JLabel lblDelete = new JLabel("Delete: ");
        JTextField tfDelete = new JTextField();
        deleteTab.add(lblDelete);
        deleteTab.add(tfDelete);
        return deleteTab;
    }

    public void swapView(String key) {
        cardLayout.show(cards, key);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cardLayout.next(cards);

    }
}
