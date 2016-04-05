package occ.cs272.ic09;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
/**
 *  CS 272 Example: JTree class demo
 *
 *  @author Stephen Gilbert
 *  @version Fall 2008
 *
 */

public class TreeDemo1 extends JPanel
{
    public TreeDemo1()
    {
        setLayout(new BorderLayout());
        
        // Create the root node
        DefaultMutableTreeNode root =
            new DefaultMutableTreeNode("Coast Community College District");
        JTree tree = new JTree(root);

        add(new JScrollPane(tree), BorderLayout.CENTER);
    }

    // Stuff you can customize
    private static String appName = "Tree Demo 1";
    private static int DEFAULT_WIDTH = 300;
    private static int DEFAULT_HEIGHT = 300;
    
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
                TreeDemo1 app = new TreeDemo1();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
}
