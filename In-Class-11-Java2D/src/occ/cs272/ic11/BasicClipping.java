package occ.cs272.ic11;

/**
 *  CS 272 - Example
 *
 *  Illustrates clipping
 *
 *  EX01: Modify the program to clip to an ellipse rather than
 *        a rectangle. How does the figure change?
 */

import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

public class BasicClipping extends JPanel
{
    public BasicClipping()
    {
        setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setFont(new Font("Jokerman", Font.BOLD, 72));

        String msg = "CS272";
        
        // 1. Draw at 50 over and 150 down
        g2.drawString(msg, 50f, 150f);

        // 2. Install a clipping region 70 over and 250 down
        //      Make it 250 wide and 40 deep
        //      Draw a line around it so we can see it
        Ellipse2D clip = new Ellipse2D.Double(70,250,250,40);
        g2.draw(clip);
        g2.setClip(clip);

        // 3. Draw at 50 over and 300 down
        g2.setPaint(Color.CYAN);
        g2.drawString(msg, 50f, 300f);

        // 4. Draw at 50 over and 150 down
        g2.setPaint(Color.MAGENTA);
        g2.drawString(msg, 70, 150);
    }

    // Stuff you can customize
    private static String appName = "Basic User-space Clipping";
    private static int DEFAULT_WIDTH = 400;
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
                BasicClipping app = new BasicClipping();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
}
