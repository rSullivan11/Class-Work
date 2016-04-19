package occ.cs272.ic11;
/**
 *  CS 272 - Exercise
 *
 *  Combining area using the add, subtract, exclusive OR
 *  and intersect operations.
 *
 *  EX01: Change the rectangles in this example to ellipses.
 *        What happens?
 *
 *  EX02: Change only the "left" rectangles in this example to ellipses.
 *        What happens?
 *
 *  EX03: Modify the example to add an ellipse that overlaps with
 *        all four rectangles on the left, and fill the intersection of
 *        it with the top two rectangles and with the bottom two.
 */

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import static java.awt.RenderingHints.*;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class AreaDemo extends JPanel
{
    private Rectangle2D left1, right1, left2, right2,
                         left3, right3, left4, right4;

    private Area area1, area2, area3, area4,
                  area5, area6, area7, area8;

    private Shape overlapped;
    private Area intersect;
    
    public AreaDemo()
    {
        setBackground(Color.white);

        // Initialize each of the rectangles
        left1  = new Rectangle2D.Double(20,50,100,50);
        right1 = new Rectangle2D.Double(80,30,100,50);
        left2  = new Rectangle2D.Double(200,50,100,50);
        right2 = new Rectangle2D.Double(260,30,100,50);
        left3  = new Rectangle2D.Double(20,160,100,50);
        right3 = new Rectangle2D.Double(80,140,100,50);
        left4  = new Rectangle2D.Double(200,160,100,50);
        right4 = new Rectangle2D.Double(260,140,100,50);
                
        // Create Areas from the Rectangles
        area1 = new Area(left1);
        area2 = new Area(right1);
        area3 = new Area(left2);
        area4 = new Area(right2);
        area5 = new Area(left3);
        area6 = new Area(right3);
        area7 = new Area(left4);
        area8 = new Area(right4);

        // Perform operations on the areas
        area1.add(area2);
        area3.subtract(area4);
        area5.intersect(area6);
        area7.exclusiveOr(area8);
        
        overlapped = new Ellipse2D.Double(20, 50, 100, 150);
        intersect = new Area(overlapped);
        intersect.exclusiveOr(area1);
//        intersect.intersect(area2);
//        intersect.intersect(area5);
//        intersect.intersect(area6);
    }

    // Display each of the different rectangles
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

        // Fill the areas with green
        g2.setPaint(Color.GREEN);
        g2.fill(area1);
        g2.fill(area3);
        g2.fill(area5);
        g2.fill(area7);
        g2.setPaint(Color.MAGENTA);
        g2.fill(intersect);

        // Draw each of the outlines
        g2.setPaint(Color.BLACK);
        g2.draw(left1);
        g2.draw(right1);

        g2.draw(left2);
        g2.draw(right2);

        g2.draw(left3);
        g2.draw(right3);

        g2.draw(left4);
        g2.draw(right4);
        
        g2.draw(overlapped);

        // Display the labels
        g2.drawString("add", 30, 130);
        g2.drawString("subtract", 210, 130);
        g2.drawString("intersect", 30, 240);
        g2.drawString("exclusive OR", 210, 240);
    }

    
    // Stuff you can customize
    private static String appName = "An Area Demo";
    private static int DEFAULT_WIDTH = 400;
    private static int DEFAULT_HEIGHT = 300;
    
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
                AreaDemo app = new AreaDemo();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
}
