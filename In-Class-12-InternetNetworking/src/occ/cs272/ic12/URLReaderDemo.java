package occ.cs272.ic12;
import java.net.*;
import java.io.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 *  CS 272 Example: URLReaderDemo.java
 *
 *  Displays the resource specified by the URL
 *  entered into the text area
 *
 *  EX02:Use the program to display the file URLReaderDemo.java
 *       Use a "file URL" to get the program from the
 *       local disk. For example, using Windows, if the
 *       file were in the directory C:\java, use the URL
 *       file:///c:/java/TryURL.java
 */

public class URLReaderDemo extends JPanel
{
    /**
     * Construct the GUI.
     */
    public URLReaderDemo()
    {
        setLayout(new BorderLayout(5,5));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Add an input area to the top
        Box top = Box.createHorizontalBox();
        top.add(new JLabel("Enter URL and press ENTER : ", JLabel.RIGHT));
        top.add(urlTF);
        add(top, BorderLayout.NORTH);
        urlTF.setText("http://faculty.orangecoastcollege.edu/sgilbert/");
        urlTF.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Add the output area to the center
        output.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(output), BorderLayout.CENTER);

        // Hook up the input text field
        urlTF.addActionListener(new ActionListener(){
            /**
             *  Called when ENTER pressed in input text area
             *  Connect to the URL, read the contents, place in output are
             */
            public void actionPerformed(ActionEvent ae)
            {
                // Clear output area
                output.setText("");
    
                try {
                 // Try to construct a URL from the input text area
                    URL url = new URL(urlTF.getText());
                    
                    try (InputStream input = url.openStream();
                         Scanner cin = new Scanner(input)) {
    
                        // TODO: Exercise 1
                        // If successful, open the input stream and then
                        // construct a Scanner from the input stream
                        // Read line by line until finished
                        
                        while (cin.hasNext())
                            output.append(cin.nextLine() + "\n");
                    }
                    finally {
                        urlTF.requestFocus();
                        urlTF.selectAll();
                    }
                }
                catch(Exception e) {
                    output.setText(e.getMessage());
                }
            }
        });
    }

    // Stuff you can customize
    private static String appName = "URL Reader Demo";
    private static int DEFAULT_WIDTH = 640;
    private static int DEFAULT_HEIGHT = 480;
    
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
                JPanel app = new URLReaderDemo();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
    
    /**
     * Instance variables
     */
    private JTextField urlTF = new JTextField();
    private JTextArea output = new JTextArea(1, 1);
}

