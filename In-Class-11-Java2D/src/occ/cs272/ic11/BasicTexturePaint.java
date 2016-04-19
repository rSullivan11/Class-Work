package occ.cs272.ic11;

/**
 *  CS 272 - Example
 *
 *  Illustrates the acyclic and cyclic gradient paint
 *  and texture paint replicating an image.
 *
 *  EX01: Why does the image of the Mona Lisa not start in the
 *       upper-left corner of the rectangle. How
 *       could you modify the program to make the upper-left
 *       corner of the image coincide with the upper-left
 *       corner of the rectangle?
 *
 */

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.imageio.ImageIO;

import java.io.*;
import java.net.URL;

public class BasicTexturePaint extends JPanel
{
    private Rectangle2D rect, rect1;
    private Ellipse2D oval;

    public BasicTexturePaint()
    {
        setBackground(Color.WHITE);

        rect  = new Rectangle2D.Double(20,20,300,100);
        rect1 = new Rectangle2D.Double(20,240,300,100);
        oval  = new Ellipse2D.Double(20,130,300,100);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D d = (Graphics2D)g;

        // Create a gradient from orange to blue
        GradientPaint acyclic =
            new GradientPaint(120f, 100f, Color.ORANGE, // pt1
                              220f, 100f, Color.BLUE);  // pt2

        // Fill rect with this gradient
        d.setPaint(acyclic);
        d.fill(rect);

        // Create a cyclic (repeating) gradient
        GradientPaint cyclic =
            new GradientPaint(120f, 200f, Color.ORANGE,
                              220f, 200f, Color.BLUE,
                              true);
        
        // Fill the oval with this gradient
        d.setPaint(cyclic);
        d.fill(oval);

        BufferedImage mona = loadImage("MonaLisa.gif");

        // This is where the image will be aligned
        Rectangle2D anchor = new Rectangle2D.Double(
                mona.getWidth() / 2, mona.getHeight() /2,                   // upper-left of rectangle
                mona.getWidth(),    // width, height of image
                mona.getHeight());
        
        // Here's the texture
        TexturePaint texture = new TexturePaint(mona, anchor);

        // Fill rect1 with the texture
        d.setPaint(texture);
        d.fill(rect1);
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
    private static String appName = "Texture Painting";
    private static int DEFAULT_WIDTH = 375;
    private static int DEFAULT_HEIGHT = 425;
    
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
                BasicTexturePaint app = new BasicTexturePaint();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
}
