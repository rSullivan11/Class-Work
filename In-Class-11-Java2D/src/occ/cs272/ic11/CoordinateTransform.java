package occ.cs272.ic11;
/**
 *  CS 272 - Example
 *  Changing the drawing coordinates.
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class CoordinateTransform extends JPanel
{
    public CoordinateTransform()
    {
        setBackground(Color.WHITE);

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        // Set the scale so 1 -> 1/4 inch (graph paper)
        // Step 1: how many pixels per inch?
        //  - Read StackOverflow to see why this is a problem
        float dotsPerInch = Toolkit.getDefaultToolkit().getScreenResolution();
        float quarterInch = dotsPerInch / 4;
        
        g2.translate(getWidth() / 2, getHeight() / 2); // Cartesian coordinates
        g2.scale(quarterInch, -quarterInch);    // Invert the y coordinate
        
        // Draw a dot in the new origin (0, 0)
        g2.setPaint(Color.RED);
        Ellipse2D dot = new Ellipse2D.Float(-.25f, -.25f, .5f, .5f);
        g2.fill(dot);

        // Now draw some lines
        int xLen = (int) (getWidth() / quarterInch / 2) + 1;
        int yLen = (int) (getHeight() / quarterInch / 2) + 1;

        Color blue = new Color(196, 196, 255, 128);
        Color green = new Color(196, 255, 196, 128);
        Color pink = new Color(255, 196, 196, 128);
        Color gray = new Color(196, 196, 196, 128);
        
        g2.setStroke(new BasicStroke(.001F));
        
        // Draw the two grid lines through the origin
        g2.draw(new Line2D.Double(-xLen, 0, xLen, 0));
        g2.draw(new Line2D.Double(0, -yLen, 0, yLen));
        
        // Now, draw the rest
        for (int y = 1; y < yLen; y++) 
        {
            for (int x = 1; x < xLen; x++)
            {
                g2.setPaint(blue);
                g2.draw(new Line2D.Double(x, -yLen, x, yLen));
                g2.setPaint(green);
                g2.draw(new Line2D.Double(-x, -yLen, -x, yLen));
                g2.setPaint(pink);
                g2.draw(new Line2D.Double(-xLen, y, xLen, y));
                g2.setPaint(gray);
                g2.draw(new Line2D.Double(-xLen, -y, xLen, -y));
            }
        }
        
        // Let's graph an equation
        // y = x^2
        g2.setPaint(Color.BLUE);
        for (double x = -3; x <= 3; x += .01)
        {
            g2.fill(new Ellipse2D.Double(x, x * x, .1, .1));
        }
    }

    // Stuff you can customize
    private static String appName = "CoordinateTransform";
    private static int DEFAULT_WIDTH = 350;
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
                CoordinateTransform app = new CoordinateTransform();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
   
}
