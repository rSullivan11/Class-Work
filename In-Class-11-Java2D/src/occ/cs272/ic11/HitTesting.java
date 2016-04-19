package occ.cs272.ic11;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

import javax.swing.*;

/**
 *  CS 272 - Example
 *
 *  Illustrates using the methods of the rectangular shapes.
 *  A MouseListener reports the location of the mouse
 *  and whether it is inside of the target rectangle
 */
public class HitTesting extends JPanel
{
    // RectanglularShape fields
    private Rectangle2D left, right, union, intersect;
    private JLabel output = new JLabel("Here I am");

    public HitTesting()
    {
        // Initialize each of the objects
        left = new Rectangle2D.Double(120.2, 80.3, 100.2, 50.5);
        right = new Rectangle2D.Double(180.2, 50.3, 100.2, 50.5);
        union = left.createUnion(right);
        intersect = left.createIntersection(right);
        
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        add(output, BorderLayout.NORTH);

        addMouseMotionListener(new MouseMotionAdapter()
        {
            public void mouseMoved(MouseEvent me)
            {
                displayLocation(intersect, me.getX(), me.getY());
            }

            public void mouseDragged(MouseEvent me)
            {
                if (intersect.contains(me.getPoint()))
                {
                    double width = intersect.getWidth();
                    double height = intersect.getHeight();
                    intersect.setFrame(me.getX() - width / 2, me.getY()
                            - height / 2, width, height);
                    repaint();
                }
            }
        });
    }

    public void displayLocation(RectangularShape r, int x, int y)
    {
        int loc = r.getBounds2D().outcode(x, y);
        String position = "Mouse [" + x + "," + y + "] : ";

        if (loc == 0)
            position += "Inside ";
        else
        {
            if ((loc & Rectangle2D.OUT_BOTTOM) != 0) position += "Below ";
            if ((loc & Rectangle2D.OUT_LEFT) != 0) position += "Left ";
            if ((loc & Rectangle2D.OUT_RIGHT) != 0) position += "Right ";
            if ((loc & Rectangle2D.OUT_TOP) != 0) position += "Above ";
        }

        position += "Rectangle2D intersect";
        output.setText(position);
    }

    /**
     * Paint the components.
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw the union as an outline
        g2.draw(union);

        // Fill the left and right as green and blue
        g2.setPaint(Color.GREEN);
        g2.fill(left);
        g2.setPaint(Color.BLUE);
        g2.fill(right);

        // Now, fill the intersection as pink
        g2.setPaint(Color.PINK);
        g2.fill(intersect);
    }

    // Stuff you can customize
    private static String appName = "Hit Testing";
    private static int DEFAULT_WIDTH = 400;
    private static int DEFAULT_HEIGHT = 200;

    /**
     * Generic main() method for SwingGUI App
     * @param args
     */
    public static void main(String[] args)
    {
        // Set the system look and feel
        try   {
            UIManager.setLookAndFeel(UIManager
                    .getSystemLookAndFeelClassName());
        }  catch (Exception e)  { /* nothing to do */  }

        // Always start Swing programs on the event queue
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                JFrame mainFrame = new JFrame(appName);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                HitTesting app = new HitTesting();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
        });
    }
}
