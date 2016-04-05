package occ.cs272.ic09;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
/**
 *  CS 272 Example: A custom renderer and a simple model
 *  @author Stephen Gilbert
 */

public class TableDemo6 extends JPanel
{
    /**
     * Constructor.
     */
    public TableDemo6()
    {
        setLayout(new BorderLayout());
        
        JTable table = new JTable(new NumberModel());
        
        // Exercise 1 - Use a custom renderer
        
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    /**
     * Simple model to handle numbers.
     */
    class NumberModel extends AbstractTableModel
    {
        // Original methods from example below
        public int getRowCount()    { return 10;  }
        public int getColumnCount() { return 10;   }

        public Object getValueAt(int row, int col)
        {
            if (col == 0) return new Integer(row);
            if (col % 2 == 0)
                return new Double(-(row * col));
            else
                return new Double(row * col);
        }

        // Exercise 2 - Handle the individual column classes
        // Override getColumnClass
    }

    /**
     * Renderer for Numbers.
     */
    public class Renderer extends JLabel
                            implements TableCellRenderer
    {
        public Renderer()
        {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
        }

        public Component getTableCellRendererComponent(
                            JTable jt, Object value,
                            boolean isSelected, boolean hasFocus,
                            int row, int col)
        {
            double num = ((Double)value).doubleValue();
            setBackground(isSelected
                ? jt.getSelectionBackground()
                : jt.getBackground());
            setForeground(num < 0
                ? Color.red
                : isSelected
                    ? jt.getSelectionForeground()
                    : jt.getForeground());
            setText("" + num);
            return this;
        }
    }
    
    // Stuff you can customize
    private static String appName = "Table Demo 6";
    private static int DEFAULT_WIDTH = 500;
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
                TableDemo6 app = new TableDemo6();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
}



