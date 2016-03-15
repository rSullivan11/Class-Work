package occ.cs272.ic07;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
/**
 *  CS 272 Example: JList03
 *
 *  Writing a ListModel for more complex data
 *
 *  @author Stephen Gilbert
 *  @version Spring 2016
 *
 */

public class JList03 extends JFrame
{
    /**
     * List that maintains Customers in sorted order
     */
    private class SortedListModel extends AbstractListModel
    {
        private Vector<Customer> data = new Vector<Customer>();

        /**
         * Return the size.
         */
        public int getSize() { return data.size(); }

        /**
         * Get a Customer.
         */
        public String getElementAt(int i)
        {
            Customer c = data.elementAt(i);
            return c.getName();
        }

        /**
         * Get the Customer's phone number.
         */
        public String getValueAt(int i)
        {
            Customer c = data.elementAt(i);
            return c.getPhoneNo();
        }

        /**
         * Add a Customer.
         */
        public void addElement(Customer cust)
        {
            data.addElement(cust);
            Collections.sort(data);
            fireContentsChanged(this, 0, getSize());
        }
    }

    /**
     * Our Customer class
     */
    class Customer implements Comparable<Customer>
    {
        private String name, phoneNo;
        public Customer(String name, String phoneNo)
        {
            this.name = name;
            this.phoneNo = phoneNo;
        }

        public String getName() { return name; }
        public String getPhoneNo() { return phoneNo; }

        /**
         * Implement the Comparable interface
         */
        public int compareTo(Customer c)
        {
            // Allow String compareTo() to do the actual work
            return name.compareTo(c.getName());
        }
    }

    /**
     * Construct the application.
     */
    public JList03()
    {
        super("The JList03 App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Add some data to the model
        listData.addElement(new Customer("Malaty, M.", "555-5673"));
        listData.addElement(new Customer("Karasuda, S.", "555-0123"));
        listData.addElement(new Customer("Gilbert, S.", "555-1234"));
        listData.addElement(new Customer("Gilbert, J.", "555-2345"));

        // 1 view, 1 model
        JList list01 = new JList(listData);
        list01.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list01.setSelectedIndex(0);
        list01.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent e)
            {
                if (e.getValueIsAdjusting() == false)
                {
                    JList src = (JList)e.getSource();
                    int i = src.getSelectedIndex();
                    String phone = listData.getValueAt(i);
                    output.setText(phone);
                }
            }
        });

        // Arrange the components
        Container c = getContentPane();
        c.add(output, BorderLayout.NORTH);
        c.add(new JScrollPane(list01), BorderLayout.CENTER);
    }

    /**
     * Instance variables.
     */
    private JLabel output = new JLabel("Phone number shows here");
    private SortedListModel listData = new SortedListModel();

    /**
     * Start everything running.
     */
    public static void main(String[] args)
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JList03 app = new JList03();
        app.setSize(200, 150);
        app.setVisible(true);
    }
 }
