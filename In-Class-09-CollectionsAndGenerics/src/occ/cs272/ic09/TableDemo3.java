package occ.cs272.ic09;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;

/**
 *  CS 272 Example:  Illustrates a JTable
 *
 *  EX1: Modify to omit the headings array and
 *       the getColumnName() method. Describe the changes
 *       to the table.
 *
 *  EX2: Modify to add another row to the table.
 *       Make up your own course number and name.
 *
 *  EX3: Modify to add Integer data for the course
 *       number rather than String
 */

public class TableDemo3 extends JPanel
{
    public TableDemo3()
    {
        setLayout(new BorderLayout());
        
        JTable table = new JTable(new QuickModel());
        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setPreferredSize(new Dimension(500,200));

        add(scrollpane);
    }


    /**
     * A simple table model.
     */
    class QuickModel extends AbstractTableModel
    {
        Object[][] courses = { {"261", "Computing with Java"},
                               {"471", "Internet Applications with Java"},
                               {"524", "Topics in Programming Languages"}
                             };
        String[] headings = { "Course Number", "Course Name" };

        public int getRowCount()
        {
            return courses.length;
        }

        public int getColumnCount()
        {
            return courses[0].length;
        }

        public Object getValueAt(int row, int col)
        {
            return courses[row][col];
        }

        public String getColumnName(int i)
        {
            return headings[i];
        }
    }
    // Stuff you can customize
    private static String appName = "Table Demo 3";
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
                TableDemo3 app = new TableDemo3();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
}



