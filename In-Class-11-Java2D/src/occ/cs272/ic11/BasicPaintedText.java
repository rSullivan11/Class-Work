package occ.cs272.ic11;

/**
 *  CS 272 - Example
 *
 *  Illustrates transforming and painting text.
 *
 *  EX01:    Is it possible to set the stroke to make the
 *           text thicker so that the image shows up more?
 *
 *  EX02:    Modify the program to display "CS 272 is Fun"
 *           vertically at the left, and "Duke is cute"
 *           vertically at the right.
 */

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

public class BasicPaintedText extends JPanel
{
    private String s = "CS 272 is fun";
    private String d = "Duke is cute";
    private AffineTransform affine = new AffineTransform();

    public BasicPaintedText()
    {
        setBackground(Color.WHITE);
        setFont(new Font("Impact", Font.PLAIN, 72));
        affine.rotate(Math.PI/6, 20, 100);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        BufferedImage mona = loadImage("MonaLisa.gif");

        // Create a TexturePaint object using mona
        double width = mona.getWidth() / 2;
        double height = mona.getHeight() / 2;
        
        Rectangle2D anchor = new Rectangle2D.Double(0, 0, width, height);
        TexturePaint texture = new TexturePaint(mona, anchor);

        // Set the background to the mona lisa texture
        g2.setPaint(texture);

        // Draw the two strings, transforming the second
        g2.drawString(s, 20, 90);
        g2.setTransform(affine);
        g2.drawString(d, 20, 140);
    }

    /**
     * Loads an image from "somewhere"
     * @param fname the name of the image.
     * @return a BufferedImage or null
     */
    private BufferedImage loadImage(String fname)
    {
        try {
            URL u = getClass().getResource(fname);
            if (u == null) u = getClass().getClassLoader().getResource(fname);
            if (u == null) u = new File(fname).toURI().toURL();
            return ImageIO.read(u);
        } catch (Exception e) { return null; }
    }
    
    // Stuff you can customize
    private static String appName = "Painted and Tranformed Text";
    private static int DEFAULT_WIDTH = 450;
    private static int DEFAULT_HEIGHT = 450;
    
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
                BasicPaintedText app = new BasicPaintedText();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
}
