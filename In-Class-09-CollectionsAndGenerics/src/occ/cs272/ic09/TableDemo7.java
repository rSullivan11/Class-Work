package occ.cs272.ic09;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.*;  // Formatters for text

import java.awt.*;
import java.awt.event.*;
import java.util.*; // EventObject class
/**
 *  CS 272 Example: Custom Editors
 *  @author Stephen Gilbert
 */

public class TableDemo7 extends JPanel
{
    public TableDemo7()
    {
        setLayout(new BorderLayout());
        
        JTable table = new JTable(new EditorModel());
        table.setRowHeight(table.getRowHeight() + 8);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Insert an editor for the food column
        TableColumn foodCol = table.getColumnModel().getColumn(2);
        JComboBox<String> foodCombo = new JComboBox<>();
        foodCombo.setBackground(table.getBackground());
        foodCombo.addItem("Spam");
        foodCombo.addItem("Dorritos");
        foodCombo.addItem("Pizza");
        foodCombo.addItem("Coffee");
        foodCombo.addItem("Chocolate");
        foodCombo.addItem("All");
        foodCol.setCellEditor(new DefaultCellEditor(foodCombo));

        // Insert a renderer and an editor for the Color type
        table.setDefaultRenderer(Color.class, new ColorRenderer());
        table.setDefaultEditor(Color.class, new ColorEditor());

        // Editor for the phone number field
        try
        {
            MaskFormatter fmt = new MaskFormatter("(###) ###-####");
            fmt.setAllowsInvalid(false);
            JFormattedTextField phoneEdit = new JFormattedTextField(fmt);

            TableColumn phoneCol = table.getColumnModel().getColumn(3);
            phoneCol.setCellEditor(new DefaultCellEditor(phoneEdit));
        }
        catch (Exception e) { }

    }

    /**
     * The data model.
     */
    class EditorModel extends AbstractTableModel
    {
        // Notice different types for data
        String[] headings =
            { "Name", "Color", "Food", "Phone" };
        Object[][] data = {
            { "Bill", Color.red,      "Spam",   "(714) 555-1234"},
            { "Bob",  Color.blue,     "Doritos","(949) 555-2345"},
            { "Sally", Color.green,   "Pizza",  "(213) 555-4567"},
            { "George", Color.yellow, "Coffee", "(714) 555-5678"}
        };

        // 2 Extra methods to allow editing fields
        public boolean isCellEditable(int row, int col)
        {
            return col > 0; // edit all cells but first
        }

        public void setValueAt(Object obj, int row, int col)
        {
            data[row][col] = obj;

            // Let the table know the contents of the data model have changed
            fireTableCellUpdated(row, col);
        }

        // Add this method for default formatting
        public Class<?> getColumnClass(int col)
        {
            return getValueAt(0, col).getClass();
        }

        // Original methods from example below
        public int getRowCount()
        {
            return data.length;
        }

        public int getColumnCount()
        {
            return data[0].length;
        }

        public Object getValueAt(int row, int col)
        {
            return data[row][col];
        }

        public String getColumnName(int i)
        {
            return headings[i];
        }
    }

    /**
     * Renderer for the Color field (simplified)
     */
    class ColorRenderer extends JLabel
                        implements TableCellRenderer
    {
        public ColorRenderer()
        {
            setOpaque(true); //MUST do this for background to show up.
        }

        public Component
        getTableCellRendererComponent(
                    JTable table, Object color,
                    boolean isSelected, boolean hasFocus,
                    int row, int column)
        {
            setBackground((Color)color);
            setBorder(BorderFactory
                    .createMatteBorder(2, 2, 2, 2, isSelected
                        ? table.getSelectionBackground()
                        : table.getBackground()));
            return this;
        }
    }

    /**
     * Editor for Color cells
     */
    class ColorEditor extends AbstractCellEditor
        implements TableCellEditor
    {
        private JColorChooser colorChooser;
        private JDialog colorDialog;

        public ColorEditor()
        {
            colorChooser = new JColorChooser();
            colorDialog = JColorChooser.createDialog(
                null,
                "Select a Color",
                false,
                colorChooser,
                new ActionListener() // OK button
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        fireEditingStopped(); // finished
                    }
                },
                new ActionListener() // Cancel button
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        fireEditingCanceled(); // don't update
                    }
                });
        }

        public Object getCellEditorValue()
        {
            return colorChooser.getColor();
        }

        public Component
        getTableCellEditorComponent(JTable table,
            Object value, boolean isSelected,
            int row, int col)
        {
            colorChooser.setColor((Color)value);
            ColorRenderer view = new ColorRenderer();
            return view.getTableCellRendererComponent(
                table, value, isSelected, true, row, col);
        }

        public boolean isCellEditable(EventObject e)
        {
            return true;
        }

        public boolean shouldSelectCell(EventObject e)
        {
            colorDialog.setVisible(true);
            return true;
        }

        public void cancelCellEditing()
        {
            colorDialog.setVisible(false);
        }

        public boolean stopCellEditing()
        {
            colorDialog.setVisible(false);
            return true;
        }
    }

    // Stuff you can customize
    private static String appName = "Table Demo 7";
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
                TableDemo7 app = new TableDemo7();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
}



