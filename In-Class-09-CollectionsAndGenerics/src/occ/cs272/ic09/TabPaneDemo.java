package occ.cs272.ic09;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

/**
 *  CS 272 Example: Tabs.java
 *
 *   Illustrates a tabbed pane.
 *
 *  TIY: Modify the example to make the Message tab
 *       appear on top initially.
 */

public class TabPaneDemo extends JPanel
{

    public TabPaneDemo()
    {
        setLayout(new BorderLayout());
        
        // 1. Create a JTabbedPane and add it to the center

        // SelectItem and SelectMessage are just sample programs
        SelectItem item = new SelectItem();
        SelectMessage message = new SelectMessage();

        // 2. Add them to the tab pane with appropriate messages

        // 3. Set the first tab active
    }

    /**
     * Sample code.
     */
    class SelectItem extends JPanel
    {
        private DrawOn canvas = new DrawOn();
        private JComboBox<String> color = new JComboBox<String>();
        private ButtonGroup group = new ButtonGroup();
        private JRadioButton square = new JRadioButton("Square");
        private JRadioButton circle = new JRadioButton("Circle");
        private Color [] theColor = {Color.red,Color.green,Color.blue};
        private String [] colorName = {"Red","Green","Blue"};

        public SelectItem()
        {
            setLayout(new FlowLayout());

            add(color);

            group.add(square);
            group.add(circle);
            add(square);
            add(circle);

            add(canvas);

            for (int i=0; i<colorName.length; i++)
                color.addItem(colorName[i]);
            canvas.setPreferredSize(new Dimension(150,150));

            color.addItemListener(canvas);
            square.addItemListener(canvas);
            circle.addItemListener(canvas);
        }

        class DrawOn extends JPanel implements ItemListener
        {
            boolean isCircle = true;
            public void itemStateChanged(ItemEvent event)
            {
                Object source = event.getItem();
                if (source == circle)
                    isCircle = true;
                else if (source == square)
                    isCircle = false;
                repaint();
            }

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

    /**
     * Another sample class.
     */
    class SelectMessage extends JPanel
    {
        private DrawOn canvas = new DrawOn();
        private String [] colorName = {"Black","Blue","Cyan","Dark Gray","Gray",
                                       "Green","Light Gray","Magenta","Orange","Pink","Red","White","Yellow"};
        private JList<?> names = new JList<Object>(colorName);
        private JScrollPane color = new JScrollPane(names);
        private JCheckBox italic = new JCheckBox("Italic");
        private JCheckBox bold = new JCheckBox("Bold");
        private Color [] theColor = {Color.black,Color.blue,Color.cyan,Color.darkGray,
                                     Color.gray,Color.green,Color.lightGray,Color.magenta,Color.orange,
                                     Color.pink,Color.red,Color.white,Color.yellow};
        private String message = "Hi there";

        public SelectMessage()
        {
            setLayout(new FlowLayout());

            add(color);

            add(italic);
            add(bold);

            add(canvas);

            names.setSelectedIndex(0);
            names.addListSelectionListener(canvas);

            canvas.setPreferredSize(new Dimension(150,150));

            italic.addItemListener(canvas);
            bold.addItemListener(canvas);
        }

        class DrawOn extends JPanel implements
                    ItemListener, ListSelectionListener
        {
            int style = Font.PLAIN;
            public void itemStateChanged(ItemEvent event)
            {
                Object source = event.getItem();
                int change = event.getStateChange();
                if (source == italic)
                    if (change == ItemEvent.SELECTED)
                        style += Font.ITALIC;
                    else
                        style -= Font.ITALIC;
                if (source == bold)
                    if (change == ItemEvent.SELECTED)
                        style += Font.BOLD;
                    else
                        style -= Font.BOLD;
                repaint();
            }

            public void valueChanged(ListSelectionEvent e)
            {
                if (e.getValueIsAdjusting() == false)
                    repaint();
            }

            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.setFont(new Font("Serif",style,24));
                g.setColor(theColor[names.getSelectedIndex()]);
                g.drawString(message, 50,50);
            }
        }
    }
    
    // Stuff you can customize
    private static String appName = "Tabbed Pane Demo";
    private static int DEFAULT_WIDTH = 400;
    private static int DEFAULT_HEIGHT = 400;
    
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
                TabPaneDemo app = new TabPaneDemo();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
}



