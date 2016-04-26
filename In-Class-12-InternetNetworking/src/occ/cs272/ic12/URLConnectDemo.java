package occ.cs272.ic12;
import java.net.*;
import java.io.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 *  CS 272 Example: URLConnectDemo.java
 *
 *  Displays the resource specified by the URL
 *  passed as the first program argument, with the
 *  MIME types acceptable for the response passed
 *  as the second program argument.
 *
 *  EX01: Use the getHeaderField() method to get the content type
 *        instead of using the getContentType() method.
 */

public class URLConnectDemo extends JPanel
{
    /**
     * Construct the UI.
     */
    public URLConnectDemo()
    {
        setLayout(new BorderLayout(5,5));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Add an input area to the top
        Box top = Box.createHorizontalBox();
        top.add(new JLabel("Enter URL, press ENTER : ", JLabel.RIGHT));
        top.add(urlTF);
        top.add(new JLabel(" Mime : ", JLabel.RIGHT));
        top.add(mimeTF);
        add(top, BorderLayout.NORTH);

        // Add the output area to the center
        output.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(output), BorderLayout.CENTER);

        // Hook up the input text field
        urlTF.addActionListener(handler);
        mimeTF.addActionListener(handler);
    }

    private ActionListener handler = new ActionListener()
    {
        /**
         *  Called when ENTER pressed in input text area
         *  Connect to the URL, read the headers, place in output are
         */
        public void actionPerformed(ActionEvent ae)
        {
            // Clear output area
            output.setText("");
        
            try  {
                // Construct a URL from the input text area
                URL url = new URL(urlTF.getText());
                URLConnection c = url.openConnection();
                //c.setRequestProperty("Accept", mimeTF.getText());
        
                // TODO: Display the various header information
                output.append("content-length: " + c.getContentLength() + "\n");
                output.append("Date modified: " + c.getDate());
        
                // Now display the contents themselves
                output.append("\n\nContents:");
        
                InputStream input = null;
                try
                {
                    // Use getInputStream (not openStream) with a URLConnection
                    input = c.getInputStream();
                    Scanner cin = new Scanner(input);
        
                    while (cin.hasNext())
                        output.append(cin.nextLine() + "\n");
        
                    urlTF.requestFocus();
                    urlTF.selectAll();
                    cin.close();
                }
                finally {
                    input.close();
                }
            }
            catch(Exception e) {
                output.append(e.getMessage());
            }
        }
    };
    
    // Stuff you can customize
    private static String appName = "Connection Response Demo";
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
                JPanel app = new URLConnectDemo();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
    
    /**
     * Instance variables.
     */
    private JTextField urlTF = new JTextField(20);
    private JTextField mimeTF = new JTextField("text/plain");
    private JTextArea output = new JTextArea(1, 1);
}
