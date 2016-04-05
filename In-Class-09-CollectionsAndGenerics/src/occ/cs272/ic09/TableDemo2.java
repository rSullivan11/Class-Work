package occ.cs272.ic09;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *  CS 272 Example: 
 *  Simplest JTable - display editing features
 *
 *  @author Stephen Gilbert
 *  @version Spring 2016
 *
 */

public class TableDemo2 extends JPanel
{
    public TableDemo2()
    {
        setLayout(new BorderLayout());
        
        String[] columns = { "Last", "First", "Email" };
        final Object[][] customers = {
                {"Gates", "William", "billg@msn.com"},
                {"Gosling", "James", "jag@liquidr.com"},
                {"Torvalds", "Linus", "linus@losl.org"},
                {"Wozniak", "Steve", "sw@woz.org"}
            };
        JTable table = new JTable(customers, columns);
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        // Show that the default model allows editing
        JButton dump = new JButton(new AbstractAction("Display Customers")
        {
            public void actionPerformed(ActionEvent ae)
            {
                for (int i = 0; i < customers.length; i++)
                {
                    System.out.println(
                        customers[i][0] + ", " +
                        customers[i][1] + ", " +
                        customers[i][2]);
                }
            }
        });
        add(dump, BorderLayout.SOUTH);
    }

    // Stuff you can customize
    private static String appName = "Table Demo 2";
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
                TableDemo2 app = new TableDemo2();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
}



