package occ.cs272.ic11;

/**
 *  CS 272 - Example
 *
 *  Illustrates using text attributes.
 *
 *  EX01: When calling drawString(), we used aString.getIterator()
 *        as the first call. What happens if we just use aString instead?
 *
 *  EX02: Modify the program to insert a shape at the beginning of
 *        the second line rather than an image. Draw a little happy face.
 */

import java.awt.*;
import java.awt.font.*;
import java.text.*;

import javax.swing.*;

public class BasicStyledText extends JPanel
{
    // These are the two strings that we'll print
    private AttributedString aString, dString;

    public BasicStyledText()
    {
        setBackground(Color.WHITE);

        // Create two Font objects
        Font joker = new Font("Jokerman", Font.PLAIN, 36);
        Font roman = new Font("Serif", Font.PLAIN, 36);

        // Create the first AttributedString
        aString = new AttributedString("CS 272 is fun");
        // Create the second AttributedString
        dString = new AttributedString("\ufffc is cute");
        
        
        // Set the "base" font to joker, the first 6 characters to roman 
        // First 2 to underlined
        aString.addAttribute(TextAttribute.FONT, joker);
        aString.addAttribute(TextAttribute.FONT, roman , 0, 6);
        aString.addAttribute(TextAttribute.UNDERLINE,
                            TextAttribute.UNDERLINE_ON, 0, 2);

        // Set the foreground and background colors for characters "fun"
        aString.addAttribute(TextAttribute.FOREGROUND, Color.MAGENTA, 10, 13);
        aString.addAttribute(TextAttribute.BACKGROUND, Color.CYAN, 10, 13);

        // Create an ImageGraphicAttribute using "duke.gif"
        ImageIcon duke = new ImageIcon(getClass()
                .getClassLoader().getResource("duke.gif"));
        ImageGraphicAttribute image =
            new ImageGraphicAttribute(duke.getImage(),
                            GraphicAttribute.TOP_ALIGNMENT);

        // Set the text style and the replacement character
        dString.addAttribute(TextAttribute.FONT, joker);
        dString.addAttribute(TextAttribute.CHAR_REPLACEMENT,
                             image, 0, 1); // 1st character
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        g2d.drawString(aString.getIterator(), 50, 70);
        g2d.drawString(dString.getIterator(), 50, 150);
    }

    // Stuff you can customize
    private static String appName = "Styled Text Demo";
    private static int DEFAULT_WIDTH = 350;
    private static int DEFAULT_HEIGHT = 250;
    
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
                BasicStyledText app = new BasicStyledText();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
}
