package occ.cs272.ic11;
/**
 *  CS 272 - Example
 *
 *  Illustrates line stroke styles
 *
 *  EX01: Modify the program to draw the dashed rectangle
 *        with an alternating pattern of short and long dashes.
 *
 *  EX02: Modify the program to draw the solid rectangle using a
 *        JOIN_ROUND style. How does the appearance of the
 *        rectangle change?
 */
import static java.awt.BasicStroke.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class BasicStrokes extends JPanel
{
    private Rectangle2D rect, rect1;
    private Ellipse2D oval;
    private Stroke buBeDa, roRo, buBe, sqMi, sqBeDa;

    public BasicStrokes()
    {
        setBackground(Color.WHITE);

        // Initialize the shapes
        rect = new Rectangle2D.Double(20,50,300,100);
        oval = new Ellipse2D.Double(20,160,300,80);
        rect1 = new Rectangle2D.Double(20,250,300,100);

        // intialize all of the strokes
        buBeDa = new BasicStroke(
                5,                      // 5 pixels thick
                CAP_BUTT,   // No extra end-handling
                JOIN_BEVEL, // bevel the connections at join
                0,                      // No miter limit
                new float[] {10, 5, 5, 5},    // 10 dash with 5 space
                0                       // No dash offset (phase) to start
        );

        roRo = new BasicStroke(
                10,                     // 10 wide
                CAP_ROUND,  // Round ends on lines
                JOIN_ROUND  // round corners when connecting
        );

        buBe = new BasicStroke(
                12,                     // 12 wide
                CAP_BUTT,   // No extra decoration on end
                JOIN_BEVEL  // connect outer corners
        );

        sqMi = new BasicStroke(8);      // 8 wide

        sqBeDa = new BasicStroke(
                5,                      // width of line
                CAP_SQUARE, // square ends
                JOIN_BEVEL, // connects outer corners
                0,                      // miter limit not needed
                new float[] {15,10},    // 10 pixel wide dash
                0                       // dash phase (where to start)
        );
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        g2d.setPaint(Color.RED);
        g2d.setStroke(buBeDa);  // 1) 5 pixel dashed
        g2d.draw(rect);

        g2d.setPaint(Color.CYAN);
        g2d.setStroke(roRo);    // 2) 20 pixel round ends
        g2d.draw(oval);
        g2d.draw(new Line2D.Double(100,200,160,180));
        g2d.draw(new Line2D.Double(160,180,220,200));

        g2d.setPaint(Color.MAGENTA);
        g2d.setStroke(roRo);    // 3) 8-pixel default line
        g2d.draw(new Line2D.Double(100,340,140,280));
        g2d.draw(new Line2D.Double(180,340,140,280));
        g2d.draw(rect1);

        g2d.setPaint(Color.GREEN);
        g2d.setStroke(buBe);    // 4) 12-pixel solid line
        g2d.draw(new Line2D.Double(30,340,50,280));
        g2d.draw(new Line2D.Double(70,340,50,280));

        g2d.setPaint(Color.GRAY);
        g2d.setStroke(sqBeDa);  // 5) 5-pixel even dash
        g2d.draw(new Line2D.Double(20,75,300,75));
    }

    // Stuff you can customize
    private static String appName = "Basic Stroking Styles";
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
                BasicStrokes app = new BasicStrokes();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
   
}
