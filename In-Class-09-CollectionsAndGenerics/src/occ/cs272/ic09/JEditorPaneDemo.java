package occ.cs272.ic09;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
/**
 *  CS 272 Example: JEditorPane demo - a simple browser
 *
 */

public class JEditorPaneDemo extends JPanel
{
    public JEditorPaneDemo()
    {
        setLayout(new BorderLayout());
        
        String homePage = 
                "http://faculty.orangecoastcollege.edu/sgilbert/index.html";
        Panel p = new Panel(new FlowLayout(FlowLayout.LEFT));
        
        final JTextField url = new JTextField(homePage, 40);
        final JEditorPane editor = new JEditorPane();
        editor.setEditable(false);
        editor.addHyperlinkListener(new HyperlinkListener()
        {
            public void hyperlinkUpdate(HyperlinkEvent e)
            {
                if (e.getEventType() ==
                    HyperlinkEvent.EventType.ACTIVATED)
                {
                    url.setText(e.getURL().toString());
                    setPage(editor, e.getURL().toString());
                }
            }
        });
        setPage(editor, homePage);

        JButton go = new JButton("Go");
        go.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                setPage(editor, url.getText());
            }
        });
        url.addActionListener(go.getActionListeners()[0]);

        p.add(url);
        p.add(go);
        add(p, BorderLayout.NORTH);

        add(new JScrollPane(editor), BorderLayout.CENTER);
    }

    private void setPage(JEditorPane editor, String url)
    {
        try {
            editor.setPage(url);
        } catch (Exception e) {
            editor.setText(e.getMessage());
        }
    }

    // Stuff you can customize
    private static String appName = "JEditorPane Demo";
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
                JEditorPaneDemo app = new JEditorPaneDemo();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
}
