package occ.cs272.ic12;
import java.net.*;
import java.io.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
/**
 *  CS 272 Example: Response Demo
 *
 *  Uses a URLConnection to find the response status
 *  and headers sent by the server.
 *
 *  EX01: Connect to several different Web sites and
 *       see what kind of response is generated
 *
 */

public class ResponseDemo extends JPanel
{
    /**
     * Construct and hook up the UI.
     */
    public ResponseDemo()
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
        add(new JScrollPane(output), BorderLayout.CENTER);

        // Hook up the input text field
        urlTF.addActionListener(new ActionListener()
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
                    // TODO: Construct a URL from the input text area
                    URL url = new URL(urlTF.getText());
                    URLConnection c = url.openConnection();
                    
                    output.setText("Status line:\n");
                    // TODO: Display the response status line
                    output.append(c.getHeaderField(0) + "\n");

                    output.append("Response headers:\n");
                    int i = 1; String ans = "";
                    
                    while ((ans = c.getHeaderFieldKey(i)) != null)
                        output.append("   " + ans + "=" + 
                                      c.getHeaderField(i++)+ "\n");
                    
                }
                catch(Exception e) {
                    output.append(e.getMessage());
                }
            }
        });
    }

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
                JPanel app = new ResponseDemo();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }

    /**
     * Instance variables.
     */
    private JTextField urlTF = new JTextField();
    private JTextArea output = new JTextArea(1, 1);
}
