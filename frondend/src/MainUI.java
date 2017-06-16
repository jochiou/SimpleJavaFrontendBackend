import javax.swing.*;

/**
 * Created by josephchiou on 6/15/17.
 */
public class MainUI extends JFrame{

    public MainUI(String title){
        super(title);
    }

    public void init(){
        JButton test = new JButton("Testing");
        this.setSize(300,200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

    }
}
