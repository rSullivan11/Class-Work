package occ.cs272.ic11;

/**
 *  CS 272 - Example
 *
 *  Illustrates affine transformations: rotate, translate,
 *  scale, and shear. Uses two methods to apply transformations,
 *  first to each object, then to the graphics context.
 *
 *  EX01: Suppose that the shear transform used in this problem
 *        used a y argument of .5 instead of 0. What would be the
 *        coordinates of the endpoints of the upper sheared
 *        rectangle?
 *
 *  EX02: Modify the program to do all of the transformations using
 *        graphics context methods, rather than creating new shapes
 *
 *  EX03: Modify the program to do all of the transformations using
 *        new shapes, rather than using graphics context transformations
 */


import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

public class BasicTransforms extends JPanel
{
    private Ellipse2D oval = new Ellipse2D.Double(20,150,100,50);
    Shape rotated, translated, sheared, scaled;
    
    private Rectangle2D rect = new Rectangle2D.Double(20,50,100,50);
    AffineTransform affine = new AffineTransform();

    public BasicTransforms()
    {
        setBackground(Color.WHITE);

        // Change the AffineTransform object and create transformed shapes
        affine.rotate(Math.PI/6, 20, 100);             // rotate
        rotated = affine.createTransformedShape(rect); // rotated rect

        affine.rotate(-Math.PI/6, 20, 100);            // rotate back
        affine.translate(120, 0);                       // move 120 to right
        translated = affine.createTransformedShape(rect);

        affine.shear(.5, 0);    // tranform x,y to x + .5y, y
        sheared = affine.createTransformedShape(rect);

        affine.translate(0,50);     // move 50 down
        affine.scale(1.2,1.2);      // multiply by 1.2
        scaled = affine.createTransformedShape(rect);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        // Let's hint it up a bit
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the 5 variations of the rectangle
        g2.draw(rect);         // 1
        g2.draw(rotated);      // 2
        g2.draw(translated);   // 3
        g2.draw(sheared);      // 4
        g2.draw(scaled);       // 5

        // Draw the oval
        g2.draw(oval);         // 6

        // Draw some more by changing the Graphics2D context
        g2.rotate(Math.PI/12, 20, 200);
        g2.draw(oval);      // 7

        g2.rotate(-Math.PI/12, 20, 200); // put things back
        g2.transform(affine); // apply the AffineTransform to g2
        g2.draw(oval); // sheared, translated, rotated, etc.
    }

    // Stuff you can customize
    private static String appName = "Basic Affine Transforms";
    private static int DEFAULT_WIDTH = 425;
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
                BasicTransforms app = new BasicTransforms();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
}
