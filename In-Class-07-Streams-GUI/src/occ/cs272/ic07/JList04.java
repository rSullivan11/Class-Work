package occ.cs272.ic07;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *  CS 272 Example: JList04.java
 *
 *  Writing a custom cell renderer
 *
 *  @author Stephen Gilbert
 *  @version Spring 2016
 *
 */

public class JList04 extends JFrame
{

    /**
     * Construct the app.
     */
    public JList04()
    {
        super("The JList04 App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Get all of the fonts on the system
        Font[] fonts =  GraphicsEnvironment
                        .getLocalGraphicsEnvironment()
                        .getAllFonts();

        // Add the fonts to a JList
        JList list = new JList(fonts);

        // Set the cell renderer to a new FontCellRenderer
        list.setCellRenderer(new FontCellRenderer());

        // Add the list to the application
        Container c = getContentPane();
        c.add(new JScrollPane(list), BorderLayout.CENTER);
    }

    /**
     * Class to render the fonts.
     */
    class FontCellRenderer implements ListCellRenderer
    {
        /* Ultra-simple renderer doesn't handle selection, etc. */
        public Component
        getListCellRendererComponent(   JList list,
                                        final Object value,
                                        int index,
                                        boolean isSelected,
                                        boolean hasFocus)
        {
            Font font = (Font)value;
            font = font.deriveFont(14.0f); // set size to 14 point
            JLabel lbl = new JLabel(font.getFontName());
            lbl.setFont(font);
            return lbl;
        }
    }

    /**
     * Starts the program running.
     */
    public static void main(String[] args)
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JList04 app = new JList04();
        app.setSize(250, 350);
        app.setVisible(true);
    }
 }
