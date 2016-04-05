package occ.cs272.ic09;
import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;
/**
 *  CS 272 Example: JTree class demo -- Adding Elements
 */

public class TreeDemo3 extends JPanel
{
    public TreeDemo3()
    {
        setLayout(new BorderLayout());
        
        DefaultMutableTreeNode root =
            new DefaultMutableTreeNode("Coast Community College District", true);
        addNodes(root);

        // Allow empty parent nodes to display correctly
        JTree tree = new JTree(root, true);

        add(new JScrollPane(tree), BorderLayout.CENTER);
    }

    /**
     * Add nodes handling children correctly.
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
    private static String appName = "Tree Demo 3";
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
                TreeDemo3 app = new TreeDemo3();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }

}
