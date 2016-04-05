package occ.cs272.ic09;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
/**
 *  CS 272 Example: JList02
 *
 *  Writing your own ListModel that keeps data in sorted order
 *
 *  @author Stephen Gilbert
 *  @version Spring 2016
 *
 */

public class JList02 extends JFrame
{
    /**
     * Our custom ListModel
     */
    class SortedListModel extends AbstractListModel<String>
    {
        private Vector<String> data = new Vector<String>();

        /**
         * Number of elements
         */
        public int getSize() { return data.size(); }

        /**
         * Specific String.
         */
        public String getElementAt(int i) { return data.elementAt(i); }

        /**
         * Add a new item.
         */
        public void addElement(String str)
        {
            data.addElement(str);
            Collections.sort(data);
            fireContentsChanged(this, 0, getSize());
        }
    }

    /**
     * Construct the app.
     */
    public JList02()
    {
        super("The JList04 App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Add some data to the model
        listData.addElement("One");
        listData.addElement("Two");

        // 2 views, 1 model (no longer final)
        JList<String> list01 = new JList<>(listData);
        JList<String> list02 = new JList<>(listData);

        // Arrange the components
        JPanel p = new JPanel(new GridLayout(2,0));
        p.add(new JScrollPane(list01));
        p.add(new JScrollPane(list02));

        Container c = getContentPane();
        c.add(input, BorderLayout.NORTH);
        c.add(p, BorderLayout.CENTER);

        // Controller adds data to the model, then updates the view
        input.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                listData.addElement(input.getText());
                input.selectAll();
            }
        });
    }

    /**
     * Instance variables.
     */
    private JTextField input = new JTextField();
    private SortedListModel listData = new SortedListModel();

    /**
     * Start the ball rolling.
     */
    public static void main(String[] args)
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JList02 app = new JList02();
        app.setSize(250, 300);
        app.setVisible(true);
    }
 }
