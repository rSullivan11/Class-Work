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
 *  CS 272 Example: JTree class demo -- Adding Elements
 *
 */

public class TreeDemo2 extends JPanel
{
    public TreeDemo2()
    {
        setLayout(new BorderLayout());
        
        // 1. Create the root node
        DefaultMutableTreeNode root =
            new DefaultMutableTreeNode("Coast Community College District");

        // 2. Add leaf nodes
        addNodes(root);

        JTree tree = new JTree(root, true);
        add(new JScrollPane(tree), BorderLayout.CENTER);

    }

    /**
     * Create nodes and add them to the root
     */
    private void addNodes(DefaultMutableTreeNode root)
    {
        // Create nodes for the three colleges
        DefaultMutableTreeNode ccc =
            new DefaultMutableTreeNode("Coastline Community College");
        DefaultMutableTreeNode gwc =
            new DefaultMutableTreeNode("Golden West College");
        DefaultMutableTreeNode occ =
            new DefaultMutableTreeNode("Orange Coast College");

        // Add colleges to the root
        root.add(ccc);
        root.add(gwc);
        root.add(occ);
        
        // This is wrong because occ already has a parent
        // This MOVES the occ node to underneath ccc, not root
        // ccc.add(occ);

        // Add two divisions to OCC
        DefaultMutableTreeNode busdiv =
            new DefaultMutableTreeNode("Business and Computing Division");
        DefaultMutableTreeNode scidiv =
            new DefaultMutableTreeNode("Math and Science Division");
        occ.add(busdiv);
        occ.add(scidiv);

        // Add some departments to busdiv
        busdiv.add(new DefaultMutableTreeNode("Accounting", false));
        busdiv.add(new DefaultMutableTreeNode("Business", false));
        busdiv.add(new DefaultMutableTreeNode("Computer Information Systems", false));
        busdiv.add(new DefaultMutableTreeNode("Computer Science", false));
        busdiv.add(new DefaultMutableTreeNode("Management", false));
        busdiv.add(new DefaultMutableTreeNode("Marketing", false));
        busdiv.add(new DefaultMutableTreeNode("Real Estate", false));
    }

    // Stuff you can customize
    private static String appName = "Tree Demo";
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
                TreeDemo2 app = new TreeDemo2();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
}