package occ.cs272.ic06.gui;
/**
 *  CS 272 Example.
 *
 *  Illustrates JRadioButton and JComboBox.
 *
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class SelectItem extends JPanel
{
    // TODO: Create two radio buttons
    private JRadioButton square;
    private JRadioButton circle;    // make this one checked

    private Color[] theColor = {Color.red,Color.green,Color.blue};
    @SuppressWarnings("unused")
    private String[] colorName = {"Red","Green","Blue"};

    // TODO: // initialize with color names
    @SuppressWarnings("rawtypes")
    private JComboBox color;    
    
    public SelectItem()
    {
        // Add the JComboBox
        //add(color);

        // Create a ButtonGroup and then add the radio
        // buttons to both the group and to the app
        //add(square);
        //add(circle);

        // Add the output area
        add(canvas);
        canvas.setPreferredSize(new Dimension(150,150));

        // Use the ActionListener interface
        // color.addActionListener(canvas);
        // square.addActionListener(canvas);
        // circle.addActionListener(canvas);
    }


    // Stuff you can customize
    private static String appName = "CS272 Select Item Application";
    private static int DEFAULT_WIDTH = 450;
    private static int DEFAULT_HEIGHT = 350;
    
    /**
     * Generic main() method for SwingGUI App
     * @param args
     */
    public static void main(String[] args)
    {
        // Always start Swing programs on the event queue
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame mainFrame = new JFrame(appName);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainFrame.getContentPane().add(new SelectItem());
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });

    }
    
    // Our display class for output
    private DrawOn canvas = new DrawOn();
    // Output panel for display
    class DrawOn extends JPanel implements ActionListener
    {
        boolean isCircle = true;

        public void actionPerformed(ActionEvent event)
        {
            Object source = event.getSource();
            if (source == circle)
                isCircle = true;
            else if (source == square)
                isCircle = false;
            repaint();
        }

        // Display the output area
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.setColor(theColor[color.getSelectedIndex()]);
            if (isCircle)
                g.fillOval(20,20,100,100);
            else
                g.fillRect(20,20,100,100);
        }
    }
}
