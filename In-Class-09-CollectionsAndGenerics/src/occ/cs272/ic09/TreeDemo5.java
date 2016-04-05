package occ.cs272.ic09;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
/**
 *  CS 272 Example: JTree class demo -- Responding to events
 *
 *  @author Stephen Gilbert
 *  @version Spring 2016
 *
 */

public class TreeDemo5 extends JPanel
{
    public TreeDemo5()
    {
        setLayout(new BorderLayout());
        
        DefaultMutableTreeNode root =
            new DefaultMutableTreeNode("Coast Community College District", true);
        addNodes(root);
        JTree tree = new JTree(root, true);
        add(new JScrollPane(tree), BorderLayout.CENTER);

        final JTextArea ta = new JTextArea(3, 60);
        add(new JScrollPane(ta), BorderLayout.SOUTH);

        /**
         * Respond with the TreeSelectionListener
         */
        tree.addTreeSelectionListener(new TreeSelectionListener()
        {
            public void valueChanged(TreeSelectionEvent e)
            {
                TreePath path = e.getPath();
                ta.setText("Selected item : "
                    + path.getLastPathComponent().toString()
                    + "\nPath :" + path);
            }
        });
    }

    /**
     * Unchanged.
     */
    private void addNodes(DefaultMutableTreeNode root)
    {
        // Create nodes for the three colleges
        DefaultMutableTreeNode ccc =
            new DefaultMutableTreeNode("Coastline Community College", true);
        DefaultMutableTreeNode gwc =
            new DefaultMutableTreeNode("Golden West College", true);
        DefaultMutableTreeNode occ =
            new DefaultMutableTreeNode("Orange Coast College", true);

        // Set their "allowsChildren" property to true
        ccc.setAllowsChildren(true);
        gwc.setAllowsChildren(true);
        occ.setAllowsChildren(true);

        // Add colleges to the root
        root.add(ccc);
        root.add(gwc);
        root.add(occ);

        // Add two divisions to OCC
        DefaultMutableTreeNode busdiv =
            new DefaultMutableTreeNode("Business and Computing Division", true);
        DefaultMutableTreeNode scidiv =
            new DefaultMutableTreeNode("Math and Science Division", true);
        occ.add(busdiv);
        occ.add(scidiv);

        // Add some departments to busdiv -- don't allow children
        busdiv.add(new DefaultMutableTreeNode("Accounting", false));
        busdiv.add(new DefaultMutableTreeNode("Business", false));
        busdiv.add(new DefaultMutableTreeNode("Computer Information Systems", false));
        busdiv.add(new DefaultMutableTreeNode("Computer Science", false));
        busdiv.add(new DefaultMutableTreeNode("Computerized Office Technology", false));
        busdiv.add(new DefaultMutableTreeNode("Management", false));
        busdiv.add(new DefaultMutableTreeNode("Marketing", false));
        busdiv.add(new DefaultMutableTreeNode("Real Estate", false));
    }

    // Stuff you can customize
    private static String appName = "Tree Demo 5";
    private static int DEFAULT_WIDTH = 700;
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
                TreeDemo5 app = new TreeDemo5();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
}
