package occ.cs272.ic06.gui;
/**
 *  Login.java
 *
 *  Uses nested Panels to create a complex layout.
 *
 *  @author Steve Gilbert
 *  @version Spring 2016
 */
import java.awt.*;
import javax.swing.*;

public class Login extends JPanel
{
    /**
     * Initialize the UI.
     */
    public Login()
    {
        setLayout(new BorderLayout());
        // Title
        JLabel title = new JLabel("Please Login", JLabel.CENTER);
        title.setOpaque(true);
        title.setBackground(Color.YELLOW);
        title.setFont(new Font("Ubuntu Mono", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(2, 2, 5, 5));
        // Input area
        
        center.add(new JLabel("User ID: ", JLabel.TRAILING));
        center.add(userIdTF);
        center.add(new JLabel("Password: ", JLabel.TRAILING));
        center.add(passwordTF);
        
        JPanel shim = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        shim.add(center);
        
        add(shim, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        // Button
        panel.add(loginBtn);
        add(panel, BorderLayout.SOUTH);
    }


    // Instance variables
    private JTextField userIdTF = new JTextField(15);
    private JPasswordField passwordTF = new JPasswordField(15);
    private JButton loginBtn = new JButton(" Login ");

    // Stuff you can customize
    private static String appName = "ACE Security Service";
    
    /**
     * Generic main() method for SwingGUI App
     * @param args
     */
    public static void main(String[] args)
    {
        // Always start Swing programs on the event queue
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame mainFrame = new JFrame(appName);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainFrame.getContentPane().add(new Login());
                mainFrame.pack();
                mainFrame.setVisible(true);
            }
         });
    }
}
