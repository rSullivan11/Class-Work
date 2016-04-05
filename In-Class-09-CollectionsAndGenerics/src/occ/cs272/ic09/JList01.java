package occ.cs272.ic09;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 *  CS 272 Example
 *  Changing the underlying data model with a JList
 *
 *  @author Stephen Gilbert
 *  @version Spring 2016
 *
 */
public class JList01 extends JFrame
{
    private JTextField input = new JTextField();
    private Vector<String> listData = new Vector<String>();

    public JList01()
    {
        super("The JList01 App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Add some data to the model
        listData.addElement("One");
        listData.addElement("Two");

        // 2 views, 1 model (final so we can access from inner class)
        final JList<String> list01 = new JList<>(listData);
        final JList<String> list02 = new JList<>(listData);

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

                // Use setListData to notify each view
            }
        });
    }

    public static void main(String[] args)
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JList01 app = new JList01();
        app.setSize(250, 300);
        app.setVisible(true);
    }
 }
