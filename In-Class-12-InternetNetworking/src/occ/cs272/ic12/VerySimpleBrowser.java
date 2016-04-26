package occ.cs272.ic12;
import java.net.*;
import java.io.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 *  CS 272 Example: VerySimpleBrowser.java
 *
 *  Connects to a web server to download a file
 *  Exercises suggest extensions to handle other file types.
 *
 *  EX01 - Modify the program to use the HEAD method which
 *      just sends headers and doesn't ask for a resource
 *      in response.
 *
 *  EX02 - Modify the example to pass a URL instead of passing
 *      the host, port, and path. The URL methods getHost(),
 *      getPort(), getPath(), and getProtocol() will be helpful.
 */

public class VerySimpleBrowser extends JPanel implements ActionListener
{
    /**
     * Construct the UI.
     */
    public VerySimpleBrowser()
    {
        setLayout(new BorderLayout(5,5));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Add an input area to the top
        Box top = Box.createHorizontalBox();
        top.add(new JLabel("Host : ", JLabel.RIGHT));
        top.add(hostTF);
        top.add(new JLabel(" Port : ", JLabel.RIGHT));
        top.add(portTF);
        top.add(new JLabel(" Page : ", JLabel.RIGHT));
        top.add(pageTF);
        add(top, BorderLayout.NORTH);

        // Add the output area to the center
        add(new JScrollPane(outputTA), BorderLayout.CENTER);

        // Hook up the input text fields
        hostTF.addActionListener(this);
        portTF.addActionListener(this);
        pageTF.addActionListener(this);
    }
    
    /**
     *  Called when ENTER pressed in input text area
     *  Connect to the URL, read the contents, place in output are
     */
    public void actionPerformed(ActionEvent ae)
    {
        System.out.println("Starting actionPerformed()");
        // Clear output area
        outputTA.setText("");

        try
        {
            Socket server = null;
            InputStream input = null;
            PrintWriter output = null;

            try
            {

                // Retrieve the port and URL and then connect
                int port = Integer.parseInt(portTF.getText());
                String host = hostTF.getText();
                String path = pageTF.getText();

                server = new Socket(host ,port);
                server.setSoTimeout(1000);  // 1 second time out

                input = server.getInputStream();
                output = new PrintWriter(server.getOutputStream(),true);
                Scanner cin = new Scanner(input);

                System.out.println("Sending the request");
                // Send our request up to the server
                output.println("GET " + path + " HTTP/1.1");
                output.println("Host: " + host + ':' + port);
                output.println();

                System.out.println("Reading response headers");
                // Throw away all of the response headers; look for a blank line
                while (!(cin.nextLine()).equals(""))
                {
                    System.out.print("+");
                    continue;
                }
                System.out.println();

                System.out.println("Now reading the body");
                // Now, read the body from the server
                while (cin.hasNext())
                {
                    System.out.print(".");
                    outputTA.append(cin.nextLine());
                    outputTA.append("\n");
                }
                System.out.println();
            }
            finally
            {
                server.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
            System.out.println("Ending actionPerformed()");
    }

    // Stuff you can customize
    private static String appName = "Very Simple Web Browser";
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
        UIManager.put("TextField.font", new Font("Consolas", Font.BOLD, 14));
        
        // Always start Swing programs on the event queue
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame mainFrame = new JFrame(appName);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JPanel app = new VerySimpleBrowser();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
    /**
     * Instance variables.
     */
    private JTextField hostTF = new JTextField("faculty.orangecoastcollege.edu", 20);
    private JTextField portTF = new JTextField("80", 4);
    private JTextField pageTF = new JTextField("/sgilbert/index.html", 20);
    private JTextArea outputTA = new JTextArea(1, 1);
}
        
