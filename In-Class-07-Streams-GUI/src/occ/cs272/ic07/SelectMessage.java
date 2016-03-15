package occ.cs272.ic07;
/**
 *  CS 272 Example
 *
 *  Illustrates the use of JList, JScrollPane, and
 *  JCheckbox components.
 *
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.*;

public class SelectMessage extends JPanel
{
    // Array of color names
    private String[] colorName = {
        "Black","Blue","Cyan", "Dark Gray","Gray", "Green",
        "Light Gray", "Magenta","Orange","Pink","Red","White","Yellow"
    };

    // TODO # 1: initialize with colorName array
    private JList<String> names = new JList<>(colorName);
    
    private JLabel msgLabel = new JLabel("Hi There", JLabel.CENTER);
    
    /**
     * Displays messages in response to selections.
     */
    public SelectMessage() 
    {
        // Create an overall Border layout.
        setLayout(new BorderLayout(5, 5));

        // Add the label in the center
        msgLabel.setFont(msgLabel.getFont().deriveFont(64.0F));
        msgLabel.setOpaque(true);
        msgLabel.setBackground(Color.BLACK);
        add(msgLabel, BorderLayout.CENTER);

        // TODO # 2: Put the list in a jscrollpane
        add(new JScrollPane(names), BorderLayout.WEST);

        
        // TODO # 3: Adjust the JList characteristics
        names.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        names.setFixedCellWidth(80);
        names.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        names.setSelectedIndex(0);

        // Listen for selection events
        // Change the color as the list changes
        names.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                // TODO # 4: Set the msgLabel foreground color to the
                //           color selected in the list box.
                if (!e.getValueIsAdjusting()) {
                    msgLabel.setForeground(theColor[names.getSelectedIndex()]);
                }
            }
        });

        // Handle the checkboxes at the bottom of the screen
        JPanel p = new JPanel();
        p.add(bold);
        p.add(italic);
        add(p, BorderLayout.SOUTH);

        // Create an ItemListener for the checkboxes
        ItemListener styleChanger = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                if (italic.isSelected())
                    if (bold.isSelected())
                        setStyle(Font.BOLD + Font.ITALIC);
                    else 
                        setStyle(Font.ITALIC);
                else if (bold.isSelected())
                    setStyle(Font.BOLD);
                else 
                    setStyle(Font.PLAIN);
            }
            /**
             * Helper method for handler.
             * @param style - new font style
             */
            private void setStyle(int style) {
                msgLabel.setFont(msgLabel.getFont().deriveFont(style));
            }
        };
        
        // Use same item listener for bold and italic
        italic.addItemListener(styleChanger);
        bold.addItemListener(styleChanger);
        
    }

    // Add an italic and a bold JCheckbox
    private JCheckBox italic = new JCheckBox("Italic");
    private JCheckBox bold = new JCheckBox("Bold");

    private Color [] theColor =
    {
        Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY,
        Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
        Color.PINK, Color.RED, Color.WHITE, Color.YELLOW
    };

    // Stuff you can customize
    private static String appName = "Select Message (JList) Demo Program";
    private static int DEFAULT_WIDTH = 450;
    private static int DEFAULT_HEIGHT = 200;
    
    
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
                mainFrame.getContentPane().add(new SelectMessage());
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
}
