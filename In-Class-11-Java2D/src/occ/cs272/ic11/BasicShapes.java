package occ.cs272.ic11;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import static java.awt.RenderingHints.*; // See below
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Illustrated the Shape interface methods.
 */

public class BasicShapes extends JPanel
{
    // Rounded rectangle
    private Shape rounded;

    // Ellipse to show how the corner works
    private Shape corner;

    // Display the segments that make up the round rectangle
    private PathIterator path;

    // Constructor makes background white
    public BasicShapes()
    {
        // #1. Initialize rounded to a rounded rectangle
        // x->20.2, y->30.3, width->300.2, height->250.5
        // xArc->140.5, yArc->100
        rounded = new RoundRectangle2D.Double(
                20.2, 30.3, 300.2, 250.5,
                140.5, 100);

        // #2. Initialize corner to the "corner" of the round rectangle
        //  x->20.2, y->30.3, width->140.5, height->100.0
        corner = new Ellipse2D.Double(20.2, 30.3, 140.5, 100);
        
        // #3. Initialize path to the path iterator from rounded
        path = rounded.getPathIterator(null);
        
        setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g); // Call superclass method
        Graphics2D g2 = (Graphics2D) g; // Convert to a Graphics2D

        // #4: make it look better by setting rendering hints
        g2.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON); // No need for RenderingHelpers. with static include

        
        // draw the two shapes
        g2.draw(rounded);
        g2.draw(corner);

        // getBounds2D() returns a Rectangle2D of the bounding box
        g2.draw(rounded.getBounds2D());

        // Note that we can pass int arguments, even to the Double shapes
        Shape testRect = new Rectangle2D.Double(50, 50, 340, 200);
        
        g2.setPaint(new Color(255, 196, 196, 128)); // transparent
        
        g2.fill(testRect);
        g2.setPaint(Color.BLACK);
        g2.draw(testRect);
    }

    /**
     * A debugging method to display the segments of a path on the console
     */
    private void displayPath(PathIterator path)
    {
        // Used to store the info about each type of path segment
        double[] p = new double[6];

        // PathIterator has methods isDone(), next(), and currentSegment()
        System.out.println("Printing the contents of this path->" + path);
        while (!path.isDone())
        {
            // Get this segment, put info in the array p
            int type = path.currentSegment(p);

            switch (type)
            {
                case PathIterator.SEG_CLOSE:
                    // Done, so we draw a line back to the origin
                    System.out.println(" - close");
                    break;
                case PathIterator.SEG_CUBICTO:
                    // All 6 elements of p filled in
                    // p[0]-p[3] are the cubic segment's control points
                    // p[4],p[5] is the end point of this segment
                    System.out.printf(" - cubic curve to %.2f, %.2f%n", p[4],
                            p[5]);
                    break;
                case PathIterator.SEG_LINETO:
                    // From previous end-point to p[0],p[1]
                    System.out.printf(" - line to %.2f, %.2f%n", p[0], p[1]);
                    break;
                case PathIterator.SEG_MOVETO:
                    // Starting point of path is in p[0],p[1]
                    System.out.printf(" - move to %.2f, %.2f%n", p[0], p[1]);
                    break;
                case PathIterator.SEG_QUADTO:
                    // Quadratic segment starting at previous end point
                    // and ending at p[2],p[3]. p[0],p[1] is a control point
                    System.out.printf(" - quadratic cureve to %.2f, %.2f%n",
                            p[2], p[3]);
                    break;
            }
            path.next(); // Get the next segment of the path
        }
    }

    public static void main(String[] args)
    {
        JFrame app = new JFrame("The ShapeIt application");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BasicShapes s = new BasicShapes();
        app.setContentPane(s);
        app.setSize(450, 350);
        app.setVisible(true);

        // Some more debugging code
        // Use to test some Shape methods
        System.out.println("Printing info about->" + s.rounded);
        System.out.println("Contains Point2D[300,200]? "
                + s.rounded.contains(300, 200));
        System.out.println("Contains the pink rectangle? "
                + s.rounded.contains(50, 50, 340, 200));
        System.out.println("Intersects the pink rectangle? "
                + s.rounded.intersects(50, 50, 340, 200));

        s.displayPath(s.path);
    }
}
