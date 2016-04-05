package occ.cs272.ic09;
import javax.swing.*;
import java.awt.*;
/**
 *  CS 272 Example: Simplest JTable
 *
 *  @author Stephen Gilbert
 *  @version Spring 2016
 *
 */

public class TableDemo1 extends JPanel
{
    public TableDemo1()
    {
        setLayout(new BorderLayout());
        
        String[] columns = { "Last", "First", "Email" };

        Object[][] customers = {
            {"Gates", "William", "billg@msn.com"},
            {"Gosling", "James", "jag@liquidr.com"},
            {"Torvalds", "Linus", "linus@losl.org"},
            {"Wozniak", "Steve", "sw@woz.org"}
        };

        JTable table = new JTable(customers, columns);

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    // Stuff you can customize
    private static String appName = "Simple Table Demo 1";
    private static int DEFAULT_WIDTH = 400;
    private static int DEFAULT_HEIGHT = 400;
    
    /**
     * Generic main() method for SwingGUI App
     * @param args
     */
    public static void main(String[] args)
    {
        // Set the system look and feel
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { /* nothing to do */}
        
        // Always start Swing programs on the event queue
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame mainFrame = new JFrame(appName);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                TableDemo1 app = new TableDemo1();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
}



