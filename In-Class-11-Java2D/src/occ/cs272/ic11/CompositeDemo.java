package occ.cs272.ic11;
import static java.awt.AlphaComposite.CLEAR;
import static java.awt.AlphaComposite.DST_IN;
import static java.awt.AlphaComposite.DST_OUT;
import static java.awt.AlphaComposite.DST_OVER;
import static java.awt.AlphaComposite.SRC;
import static java.awt.AlphaComposite.SRC_IN;
import static java.awt.AlphaComposite.SRC_OUT;
import static java.awt.AlphaComposite.SRC_OVER;
import static java.awt.AlphaComposite.getInstance;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * CompositeDemo.
 * 
 * Shows the effect of different compositing rules.
 * 
 * @author Stephen Gilbert
 * @version Mar 19, 2014, 12:43:59 PM
 */
public class CompositeDemo extends JPanel
{
    private int theRule = 0;
    
    private final int[] theRules = { 
        CLEAR, DST_IN, DST_OUT, DST_OVER, 
        SRC, SRC_IN, SRC_OUT, SRC_OVER, 
    };

    private final String[] theRuleNames = { 
        "CLEAR", "DST_IN", "DST_OUT", "DST_OVER", 
        "SRC", "SRC_IN", "SRC_OUT", "SRC_OVER", 
    };

    public CompositeDemo()
    {
        setBackground(Color.WHITE);
        setOpaque(true);
        setFont(new Font("SansSerif", Font.BOLD, 24));
        
        addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent evt)
            {
                theRule++;
                if (theRule >= theRules.length) theRule = 0;
                repaint();
            }
        });
    }

    /**
     * Use this method for painting JComponents
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);    // always call the parent
        Graphics2D pen = (Graphics2D) g;  // grab the graphics context
        
        // Set some rendering hints
        pen.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Find out how many pixels the message takes
        String msg = "Compositing rule:  " + theRuleNames[theRule];
        FontMetrics fm = pen.getFontMetrics();
        int adv = fm.stringWidth(msg);
     
        // Paint it centered in black
        pen.setPaint(Color.BLACK);
        float x = getWidth() / 2 - adv / 2;
        float y = getHeight() / 5;
        pen.drawString(msg, x, y); 

        x = getWidth() / 5; 
        y += fm.getHeight();            // Move y down to top of circle
        float size = getHeight() / 2;       // Size of each circle
        
        // Draw first circle in red
        pen.setPaint(Color.RED);
        Ellipse2D.Float circle = new Ellipse2D.Float(x, y, size, size);
        pen.fill(circle);

        // Create a compositing rule (using .075f)
        AlphaComposite comp = getInstance(theRules[theRule], 0.75f);
        pen.setComposite(comp);
        pen.setPaint(Color.BLUE);

        // Draw the second circle
        circle = new Ellipse2D.Float(2 * x, y, size, size);
        pen.fill(circle);
    }
    
    // Stuff you can customize
    private static String appName = "CompositeDemo";
    private static int DEFAULT_WIDTH = 450;
    private static int DEFAULT_HEIGHT = 350;
    
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
                CompositeDemo app = new CompositeDemo();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
   
}
