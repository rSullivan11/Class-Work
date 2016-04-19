package occ.cs272.ic11;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import static java.awt.RenderingHints.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * CS 272 - Example Illustrates rectangular shapes.
 */
public class RectangularShapes extends JPanel
{
    // RectanglularShape fields
    private RectangularShape left, right, union, intersect;
    private RectangularShape oval;
    private RectangularShape open, pie, chord;

    public RectangularShapes()
    {
        // Exercise 1: Initialize the 2 rectangles, left and right
        // left: x->120.2, y->80.3, width->100.2, height->50.5)
        // right: x->180.2, y->50.3, width->100.2, height->50.5);
        left = new Rectangle2D.Double(100.2, 80.3, 100.2, 50.5);
        right = new Rectangle2D.Double(180.2, 50.3, 100.2, 50.5);
        
        // Exercise 2: Initialize the union and the intersection
        // Note that the result is always a Rectangle2D
        //  - For any rectangular shape, you can call getBounds2D()
        //  - With that, you can call createUnion
        //  - The argument also has to be a Rectangle2D, so you'll have
        //    to call getBounds2D() there as well
        union = left.getBounds2D().createUnion(right.getBounds2D());
        intersect = left.getBounds2D().createIntersection(right.getBounds2D());

        // Exercise 3: Initialize the oval
        // oval: x->200.0, y->100.0, width->100, height->70
        oval = new Ellipse2D.Double(200.0, 100.0, 100, 70);

        // Exercise 4: Initialize the three arc portions
        // open: x->20, y->210, width->100, height->50, start->45, end->90, type->OPEN
        // pie: x->130, y->210, width->100, height->50, start->45, end->90, type->PIE
        // chord: x->250, y->210, width->100, height->50, start->45, end->90, type->CHORD
        open = new Arc2D.Double(20, 210, 100, 50, 45, 90, Arc2D.OPEN);
        pie = new Arc2D.Double(130, 210, 100, 50, 45, 90, Arc2D.OPEN);
        chord = new Arc2D.Double(250, 210, 100, 50, 45, 90, Arc2D.OPEN);

        setBackground(Color.WHITE);
    }

    /**
     * Paint the components.
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

        // Draw the union as an outline
        g2.draw(union);

        // Fill the left and right as green and blue
        g2.setPaint(Color.GREEN);
        g2.fill(left);
        g2.setPaint(Color.BLUE);
        g2.fill(right);

        // Now, fill the intersection as white
        g2.setPaint(Color.WHITE);
        g2.fill(intersect);

        // Set the pen back to black and draw the oval
        g2.setPaint(Color.BLACK);
        g2.draw(oval);

        // Draw the three arcs at the bottom of the screen
        g2.draw(open);
        g2.draw(pie);
        g2.draw(chord);

        g2.draw(new Line2D.Double(180, 235, 230, 185));
        g2.draw(new Rectangle2D.Double(130, 210, 100, 50));
        g2.draw(new Ellipse2D.Double(130, 210, 100, 50));
    }

    // Stuff you can customize
    private static String appName = "Rectangular Shape Demo";
    private static int DEFAULT_WIDTH = 450;
    private static int DEFAULT_HEIGHT = 350;

    /**
     * Generic main() method for SwingGUI App
     * @param args
     */
    public static void main(String[] args)
    {
        // Set the system look and feel
        try  {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }  catch (Exception e)  { /* nothing to do */ }

        // Always start Swing programs on the event queue
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                JFrame mainFrame = new JFrame(appName);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                RectangularShapes app = new RectangularShapes();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
        });
    }
}
