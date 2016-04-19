package occ.cs272.ic11;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class BasicGradientPaint extends JPanel
{
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        float height = (float) getHeight();
        float width = (float) getWidth();

        GradientPaint gp = new GradientPaint(
                width / 2, height / 4, // center, top quarter 
                new Color(0, 0, 128),  // dark blue 
                width / 2, height / 4 * 3, // center bottom quarter 
                new Color(255, 196, 196));  // light pink
        g2.setPaint(gp);

        Rectangle2D.Float rect = new Rectangle2D.Float(0f, 0f, width, height);
        g2.fill(rect);

        g2.setPaint(Color.YELLOW);
        float radius = (float) getSize().width / 8;
        Ellipse2D sun = new Ellipse2D.Float(radius * 3, height - radius,
                radius * 2, radius * 2);
        g2.fill(sun);

        g2.setPaint(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 72));
        g2.drawString("GradientPaint", 50f, 100f);
    }

    // Stuff you can customize
    private static String appName = "Gradient Painting";
    private static int DEFAULT_WIDTH = 600;
    private static int DEFAULT_HEIGHT = 325;

    /**
     * Generic main() method for SwingGUI App
     * @param args
     */
    public static void main(String[] args)
    {
        // Set the system look and feel
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        { /* nothing to do */
        }

        // Always start Swing programs on the event queue
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                JFrame mainFrame = new JFrame(appName);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JPanel app = new BasicGradientPaint();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
        });
    }
}
