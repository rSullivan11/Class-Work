package occ.cs272.ic07;
/**
 *  CS 272 - JToolBar Demo
 *
 *  Shows how to use the JToolBar widget
 *
 *  @author Stephen Gilbert
 *  @version Spring 2016
 *
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class JToolBarDemo extends JPanel
{
    // Instance fields
    private JLabel theLabel = new JLabel("", JLabel.CENTER);

    public JToolBarDemo()
    {
        setLayout(new BorderLayout());
        
        // Initialize the toolbar
        JToolBar tbar = new JToolBar();
        addBtn(tbar, "Car1.GIF", "Blue");
        addBtn(tbar, "Car2.GIF", "Red");
        addBtn(tbar, "Car3.GIF", "Yellow");
        addBtn(tbar, "Car4.GIF", "Green");

        add(tbar,     BorderLayout.NORTH);

        // Output label
        add(theLabel, BorderLayout.CENTER);
        theLabel.setFont(new Font("Serif", Font.BOLD+Font.ITALIC, 36));
    }

    /**
     * Helper method to add a button to a toolbar.
     * @param tbar the toolbar to add to.
     * @param file the name of the image file.
     * @param color the color name to use in the message.
     */
    private void addBtn(JToolBar tbar, String file, String color)
    {
        JButton   btn  = new JButton(new ImageIcon(
                getClass().getClassLoader().getResource(file)));

        btn.setToolTipText(color);

        final String op = "Start the " + color + " car.";

        btn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                theLabel.setText(op);
            }
        });
        tbar.add(btn);
    }

    // Stuff you can customize
    private static String appName = "JToolBar Demo Program";
    private static int DEFAULT_WIDTH = 500;
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
                mainFrame.getContentPane().add(new JToolBarDemo());
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
}
