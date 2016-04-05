package occ.cs272.ic09;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
/**
 *  CS 272 Example: Adding editing and formatting to table model
 *
 */

public class TableDemo4 extends JPanel
{
    public TableDemo4()
    {
        setLayout(new BorderLayout());
        JTable table = new JTable(new QuickModel());
        add(new JScrollPane(table), BorderLayout.CENTER);
    }


    /**
     * Table model with editing features.
     */
    class QuickModel extends AbstractTableModel
    {
        // Notice different types for data
        String[] headings =
            { "Section", "Course Name", "Enrollment", "Online" };
        Object[][] courses = {
            { "33187", "CSS for Web Pages", new Integer(40), Boolean.TRUE},
            { "30530", "Java Programming I", new Integer(40), Boolean.FALSE},
            { "31551", "Intro Web Page Design", new Integer(65), Boolean.TRUE},
            { "34938", "Java Programming II", new Integer(40), Boolean.FALSE}
        };

        // Exercises ///////////////////////////////////////////
        // 1. Make only last two cells editable (isCellEditable)
        
        // 2. Set the value of the cell using setValueAt
        //    Make sure you fireTableCellUpdated

        // 3. Select the default formatting for each column

        
        
        // Original methods from example below
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
    private static String appName = "Table Demo 4";
    private static int DEFAULT_WIDTH = 600;
    private static int DEFAULT_HEIGHT = 350;
    
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
                TableDemo4 app = new TableDemo4();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
}



