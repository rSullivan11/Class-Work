package occ.cs272.ic11;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * Texture Painting Demo
 */

public class TexturePaintDemo extends JPanel
{
    public TexturePaintDemo()
    {
        setBackground(Color.BLACK);
    }
    
    /**
     * Nothing but painting.
     */
    public void paintComponent(Graphics g)
    {
        // Paint the background
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        
        // Exercise: Set some hints for the text
        
        
        int height = getHeight();
        int width = getWidth();

        Rectangle2D rect = new Rectangle2D.Float(
                width / 5F,
                height / 5f, 
                width - (2f * width / 5f), 
                height - (2f * height / 5f));
        
        g2.setPaint(getTexture("Water1.jpg", this));
        g2.fill(rect);

        Ellipse2D.Float ellipse = new Ellipse2D.Float(width / 3,
                height / 3, width / 3, height / 3);
        g2.setPaint(getTexture("Rocks.jpg", this));
        g2.fill(ellipse);

        g2.setPaint(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 36));
        g2.drawString("TexturePaint", (width / 5 * 1.6f), (height / 5) - 5);
        
        // Exercise: center the text
    }

    /**
     * Helper method to load a TexturePaint
     * @param filename to load
     * @param c component to render texture onto.
     * @return null if can't load texture; new texture otherwise.
     */
    private TexturePaint getTexture(String filename, Component c)
    {
        BufferedImage img = null;
        try
        {
            URL u = getClass().getResource(filename);
            if (u == null)
                u = getClass().getClassLoader().getResource(filename);
            if (u == null)
                u = new File(filename).toURI().toURL();
            if (u != null)
                img = ImageIO.read(u);
        }
        catch (IOException e)
        {
            System.err.println("Cannot load " + filename);
            return null;
        }
        
        int imgWidth = img.getWidth(c);
        int imgHeight = img.getHeight(c);
        BufferedImage bImg = new BufferedImage(imgWidth, imgHeight,
                BufferedImage.TYPE_INT_ARGB);

        // 3. Render your Image onto your BufferedImage object
        Graphics2D g2 = bImg.createGraphics();
        g2.drawImage(img, new AffineTransform(), c);

        // 4. Create a TexturePaint object from your rendered image
        TexturePaint tp = new TexturePaint(bImg, 
                new Rectangle(0, 0, imgWidth,imgHeight));
        g2.dispose();
        
        return tp;
    }

    // Stuff you can customize
    private static String appName = "Texture Painting Demo";
    private static int DEFAULT_WIDTH = 550;
    private static int DEFAULT_HEIGHT = 550;

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
                TexturePaintDemo app = new TexturePaintDemo();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
        });
    }
    
}
