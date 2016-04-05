package occ.cs272.ic09;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
/**
 *  CS 272 Example: JTree class demo
 *
 */

public class TreeDemo extends JPanel
{
    public TreeDemo()
    {
        setLayout(new BorderLayout());
        
        // 1A. Create the root node ("Coast Community College District")
        
        // 1B. Create a JTree using the root

        // 1C. Add the tree to the center of the pane

        final JTextArea ta = new JTextArea(3, 60);
        add(new JScrollPane(ta), BorderLayout.SOUTH);
        
        // 3. Hook up event listening for the tree
    }

    /**
     * Create nodes and add them to the root
     */
    private void addNodes(DefaultMutableTreeNode root)
    {
        // 2A. Create nodes for the three colleges

        // 2B. Add colleges to the root
        
        // 2C. Add two divisions to OCC

        // Add some departments to busdiv
//        busdiv.add(new DefaultMutableTreeNode("Accounting"));
//        busdiv.add(new DefaultMutableTreeNode("Business"));
//        busdiv.add(new DefaultMutableTreeNode("Computer Information Systems"));
//        busdiv.add(new DefaultMutableTreeNode("Computer Science"));
//        busdiv.add(new DefaultMutableTreeNode("Computerized Office Technology"));
//        busdiv.add(new DefaultMutableTreeNode("Management"));
//        busdiv.add(new DefaultMutableTreeNode("Marketing"));
//        busdiv.add(new DefaultMutableTreeNode("Real Estate"));
    }
    
    // Stuff you can customize
    private static String appName = "Tree Demo 1";
    private static int DEFAULT_WIDTH = 600;
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
                TreeDemo app = new TreeDemo();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
}
