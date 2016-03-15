package occ.cs272.ic07;
/**
 *  CS 272 - JFileChooserDemo.java
 *
 *  Shows how to use the JFileChooser widget
 *
 *  @author Stephen Gilbert
 *  @version Spring 2016
 *
 */
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JFileChooserDemo extends JPanel
{
    // Make the chooser an instance variable (or class variable)
    private JFileChooser jfc = new JFileChooser();
    
    /**
     * Construct the UI.
     */
    public JFileChooserDemo()
    {
        // Add some space above my button
        setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        JButton openBtn = new JButton("<html><font face='Arial Black'"
                + "size=7 color='#0000CC'>Open a File");
        add(openBtn);
        
        // filter the files that are shown
        jfc.setFileFilter(
                new FileNameExtensionFilter(
                        "Text Files", "txt", "java", "py", "dat"));
        
        openBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                // Call showOpenDialog
                int answer = jfc.showOpenDialog(JFileChooserDemo.this);

                // If user selects a file
                if (answer == JFileChooser.APPROVE_OPTION)
                {
                    // Get the file from the dialog.
                    File file = jfc.getSelectedFile();
                    
                    // Construct a JFrame
                    JFrame f = new JFrame(file.getName());
                    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    Container c = f.getContentPane();
                    JTextArea text = new JTextArea();
                    JScrollPane scroll = new JScrollPane(text);
                    c.add(scroll);
                    
                    // Java 7 resource catch
                    try(FileReader reader = new FileReader(file))
                    {
                        text.read(reader, file.getName());
                    } catch (IOException e) {
                        text.append(e.getMessage());
                    }
                    
                    // Position it slightly to the right
                    Point xy = JFileChooserDemo.this.getLocation();
                    xy.translate(150, 100);
                    f.setLocation(xy);
                    f.setSize(700, 500);
                    f.setVisible(true);
                }
            }
        });
    }
    
    // Stuff you can customize
    private static String appName = "JFileChooser Demo Program";
    private static int DEFAULT_WIDTH = 350;
    private static int DEFAULT_HEIGHT = 150;
    
    /**
     * Set the system look and feel.
     */
    private static void setSystemLAF()
    {
        try
        {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Generic main() method for SwingGUI App
     * @param args
     */
    public static void main(String[] args)
    {
        // Set the system look and feel
        setSystemLAF();
        
        // Always start Swing programs on the event queue
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame mainFrame = new JFrame(appName);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainFrame.getContentPane().add(new JFileChooserDemo());
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
    
}
