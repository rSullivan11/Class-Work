package occ.cs272.ic11;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class TransformDemo extends JPanel
{
    private BufferedImage theImage = loadImage("DigitalJ.jpg");

    /**
     * Now paint it.
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        AffineTransform transform;

        int imgWidth = theImage.getWidth();
        int imgHeight = theImage.getHeight();
        int width = getWidth();
        int height = getHeight();

        Rectangle2D rect = new Rectangle2D.Double(0, 0, width, height);
        g2.setPaint(Color.BLACK);
        g2.fill(rect);

        // Draw the gray lines
        g2.setPaint(Color.GRAY);
        g2.draw(new Line2D.Float(width / 4, 0, width / 4, height));
        g2.draw(new Line2D.Float(width / 4 * 3, 0, width / 4 * 3, height));
        g2.draw(new Line2D.Float(0, height / 4, width, height / 4));
        g2.draw(new Line2D.Float(0, height / 4 * 3, width, height / 4 * 3));
        
        // Draw the 4 titles centered
        g2.setPaint(Color.WHITE);
        Font f = new Font("Arial", Font.BOLD, 18);
        g2.setFont(f);
        FontRenderContext context = g2.getFontRenderContext();
        String msg = "Translate";
        Rectangle2D bounds = f.getStringBounds(msg, context);
        float y = (float)(bounds.getHeight());
        float x = (float) (width / 4 - bounds.getWidth() / 2);
        g2.drawString(msg, x, y);

        msg = "Rotate";
        bounds = f.getStringBounds(msg, context);
        x = (float) (width / 2 + width / 4 - bounds.getWidth() / 2);
        g2.drawString(msg, x, y);

        msg = "Shear";
        bounds = f.getStringBounds(msg, context);
        x = (float) (width / 4 - bounds.getWidth() / 2);
        y = (float) (height / 2 + bounds.getHeight());
        g2.drawString(msg, x, y);

        msg = "Scale";
        bounds = f.getStringBounds(msg, context);
        x = (float) (width / 2 + width / 4 - bounds.getWidth() / 2);
        g2.drawString(msg, x, y);
        
        // Change the rect to the image size.
        rect = new Rectangle2D.Double(0, 0, imgWidth, imgHeight);
        
        // this is the transformation we'll work with
        g2.draw(rect);
        transform = new AffineTransform();
        x = width / 4 - imgWidth / 2;
        y = height / 4 - imgHeight / 2;
        transform.translate(x, y);
        g2.drawImage(theImage, transform, this); // draw the picture

        // This is the Rotate transformation
        transform = new AffineTransform();
        x = width / 4 * 3 - imgWidth / 2;
        transform.translate(x, y);
        Shape shape = transform.createTransformedShape(rect); // transform it
        g2.draw(shape);      // Where the picture would be before rotation
        transform.rotate(0.5 * Math.PI);
        g2.drawImage(theImage, transform, this);

        // This is the Shear transformation
        transform = new AffineTransform();
        x = width / 4 - imgWidth / 2;
        y = height / 4 * 3 - imgHeight / 2;
        transform.translate(x, y);
        shape = transform.createTransformedShape(rect); // transform it
        g2.draw(shape);      // Where the picture would be before rotation
        
        transform.shear(0.2, 0.2);
        g2.drawImage(theImage, transform, this);

        // Here is the Scale transform
        transform = new AffineTransform();
        x = width / 4 * 3 - imgWidth / 2;
        transform.translate(x, y);
        shape = transform.createTransformedShape(rect); // transform it
        
        transform.scale(2.5, 1.5);
        g2.drawImage(theImage, transform, this);
        g2.draw(shape);      // Where the picture would be before scaling
    }
    
    /**
     * Try to find the image everywhere.
     */
    private BufferedImage loadImage(String imgName)
    {
        BufferedImage img = null;
        try {
            URL u = getClass().getResource(imgName);
            if (u == null)
                u = getClass().getClassLoader().getResource(imgName);
            if (u == null)
                u = new File(imgName).toURI().toURL();
            if (u != null)
                img = ImageIO.read(u);

        } catch (Exception e) { }
        
        return img;
    }

    // Stuff you can customize
    private static String appName = "Transformation Demo";
    private static int DEFAULT_WIDTH = 600;
    private static int DEFAULT_HEIGHT = 400;

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
                TransformDemo app = new TransformDemo();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
        });
    }
}
