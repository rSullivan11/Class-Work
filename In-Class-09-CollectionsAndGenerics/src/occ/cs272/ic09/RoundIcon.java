package occ.cs272.ic09;


/**
 *  CS 272 - Example.
 *
 *  A generic class that implements the Icon interface
 *  to draw a solid colored circle
 *
 *  @version AJIA 2nd ed. page 211
 *  @author Stephen Gilbert based on code from Art Gittleman
 *
 *  Original version was an inner class in Example 5.4.
 *  Modified version allows passing size to constructor
 *
 */

import java.awt.*;
import javax.swing.*;

class RoundIcon implements Icon
{
    private final int SIZE; //  blank final field
    private Color color;

    // Working constructor
    public RoundIcon(Color c, int size)
    {
        color = c;
        SIZE = size;
    }

    // Default construtor
    public RoundIcon(Color c)
    {
        this(c, 10);    // default size of 10
    }

    // The Icon interface methods
    public int getIconWidth()  {  return SIZE;  }
    public int getIconHeight() {  return SIZE;  }

    public void paintIcon(Component c, Graphics g2,
                            int x, int y)
    {
        // Make sure we save the "state" of the Graphics context
        Graphics2D g = (Graphics2D) g2;
        Color oldColor = g.getColor();
        g.setColor(color);
        g.fillOval(x, y, SIZE, SIZE);
        g.setColor(Color.BLACK);
        g.drawOval(x,  y, SIZE, SIZE);
        g.setColor(oldColor);
    }
}
