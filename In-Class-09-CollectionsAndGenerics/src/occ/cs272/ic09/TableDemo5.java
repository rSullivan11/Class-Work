package occ.cs272.ic09;
import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
/**
 *  CS 272 Example: Using DefaultTableModel
 *
 */

public class TableDemo5 extends JPanel
{
    // Pre-defined options for the table model
    private DefaultTableModel model;

    public TableDemo5()
    {
        setLayout(new BorderLayout());
        
        String[] headings =  { "Last name", "First", "Email", "Phone"};
        model = new DefaultTableModel(headings, 0);
        add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);

        AddDialog dlg = new AddDialog();
        dlg.pack();
        dlg.setLocation(400, 0);
        dlg.setVisible(true);
    }

    /**
     * Just a Frame class to show data for adding
     */
    class AddDialog extends JFrame
    {
        private JTextField lastTF = new JTextField(20);
        private JTextField firstTF = new JTextField(20);
        private JTextField emailTF = new JTextField(20);
        private JTextField phoneTF = new JTextField(20);

        public AddDialog()
        {
            super("Add a Client");
            JPanel c = new JPanel(new GridBagLayout());
            c.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

            // Set the basic constraints
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.weightx = .30;
            gbc.weighty = 1.0;
            gbc.gridx = gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;

            // Add the labels on the left
            c.add(new JLabel("Last name : ", JLabel.RIGHT), gbc);
            gbc.gridy = 1;
            c.add(new JLabel("First : ", JLabel.RIGHT), gbc);
            gbc.gridy = 2;
            c.add(new JLabel("Email : ", JLabel.RIGHT), gbc);
            gbc.gridy = 3;
            c.add(new JLabel("Phone : ", JLabel.RIGHT), gbc);

            // Add the input areas on the right
            gbc.gridy = 0;
            gbc.gridx = 1;
            gbc.weightx = .70;
            c.add(lastTF, gbc);
            gbc.gridy = 1;
            c.add(firstTF, gbc);
            gbc.gridy = 2;
            c.add(emailTF, gbc);
            gbc.gridy = 3;
            c.add(phoneTF, gbc);

            // Add a spacer row
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.BOTH;
            c.add(new JLabel(""));

            // Add the command-button panel on the bottom
            JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton reset = new JButton(" Reset ");
            JButton add = new JButton("  Add  ");
            p.add(reset);
            p.add(add);

            gbc.gridy = 4;
            c.add(p, gbc);

            // Code to resent the entries in a record
            reset.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    lastTF.setText("");
                    firstTF.setText("");
                    emailTF.setText("");
                    phoneTF.setText("");
                    lastTF.requestFocus();
                }
            });

            // Code to add a record
            add.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    model.addRow(new Object[]
                    {
                        lastTF.getText(),
                        firstTF.getText(),
                        emailTF.getText(),
                        phoneTF.getText()
                    });
                }
            });
            setContentPane(c);
        }
    }

    // Stuff you can customize
    private static String appName = "Table Demo 5";
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
                TableDemo5 app = new TableDemo5();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
}
