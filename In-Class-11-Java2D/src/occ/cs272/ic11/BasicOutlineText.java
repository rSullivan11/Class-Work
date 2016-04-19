package occ.cs272.ic11;

/**
 *  CS 272 - Example
 *
 *  Using text as an outline.
 *  
 *  EX01: Can we fill it with a Texture Paint?
 *  EX02: Can we fill this with a Gradient Paint?
 *  
 */

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

public class BasicOutlineText extends JPanel
{
    private String s = "CS272 is fun";
    private String d = "Duke is cute";
    private AffineTransform affine = new AffineTransform();

    public BasicOutlineText()
    {
        setBackground(Color.WHITE);
        setFont(new Font("Jokerman", Font.PLAIN, 72));
        affine.rotate(Math.PI/6, 20, 100);
        affine.translate(20, 140);  // position of drawing
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // Hint for both text and for regular drawing
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Get the outline of the current text
        FontRenderContext context = g2.getFontRenderContext();
        TextLayout tls = new TextLayout(s, g2.getFont(), context);
        TextLayout tld = new TextLayout(d, g2.getFont(), context);

        Shape outlines = tls.getOutline(null);
        Shape outlined = tld.getOutline(affine);

        // Exercise 1: fill with a texture paint
        // Exercise 2: fill with a gradient paint
        
        // Set the stroke and paint
        g2.setStroke(new BasicStroke(3));
        g2.translate(20, 90);   // move to position
        g2.setPaint(Color.BLUE);
        g2.draw(outlines);     // draw the outline
        g2.setTransform(new AffineTransform());

        // Draw the second string in a red outline
        g2.setStroke(new BasicStroke(1));
        g2.setPaint(Color.RED);
        g2.draw(outlined);
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
    private static int DEFAULT_WIDTH = 500;
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
                BasicOutlineText app = new BasicOutlineText();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
}
