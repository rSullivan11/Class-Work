package occ.cs272.ic07;

/*
 * This relies on having the Java Look and Feel Graphics Repository
 * (jlfgr-1_0.jar) in the class path.  
 */

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class ActionDemo extends JPanel
{
    private JTextArea textArea;
    private String newline = "\n";
    private Action leftAction, middleAction, rightAction;
    private JCheckBoxMenuItem[] cbmi;

    /**
     * Constructor lays out the window.
     */
    public ActionDemo()
    {
        super(new BorderLayout());

        // Create a scrolled text area.
        textArea = new JTextArea(5, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Lay out the content pane.
        setPreferredSize(new Dimension(450, 150));
        add(scrollPane, BorderLayout.CENTER);

        // Create the actions shared by the toolbar and menu.
        leftAction = new LeftAction("Go left", createNavIcon("Back24"),
                "This is the left button.", new Integer(KeyEvent.VK_L));
        middleAction = new MiddleAction("Do something",
                createNavIcon("Up24"), "This is the middle button.",
                new Integer(KeyEvent.VK_M));
        rightAction = new RightAction("Go right",
                createNavIcon("Forward24"), "This is the right button.",
                new Integer(KeyEvent.VK_R));
    }

    /**
     * Install the menu on the JFrame
     */
    private void installMenu(JFrame frame)
    {
        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // Create the first menu.
        JMenu mainMenu = new JMenu("Menu");

        Action[] actions = { leftAction, middleAction, rightAction };
        for (int i = 0; i < actions.length; i++)
        {
            JMenuItem menuItem = new JMenuItem(actions[i]);
            menuItem.setIcon(null); // arbitrarily chose not to use icon
            mainMenu.add(menuItem);
        }

        // Set up the menu bar.
        menuBar.add(mainMenu);
        menuBar.add(createEnableMenu());
        
        // Create the toolbar
        createToolBar();
    }    
    
    /**
     * Uses files in jlfgr-1_0.jar
     * @param imageName in toolbarButtonGraphics/navigation
     * @return ImageIcon or null
     */
    private ImageIcon createNavIcon(String imageName)
    {
        String imgLocation = "toolbarButtonGraphics/navigation/" 
                + imageName + ".gif";
        try {
            return new ImageIcon(getClass()
                    .getClassLoader()
                    .getResource(imgLocation));
        } catch (Exception e) {
            System.err.println("Resource not found: " + imgLocation);
            return null;
        }
    }
    
    /**
     * Helper method to create the toolbar
     */
    private void createToolBar()
    {
        // Create the toolbar.
        JToolBar toolBar = new JToolBar();
        add(toolBar, BorderLayout.PAGE_START);

        // first button
        Action[] actions = { leftAction, middleAction, rightAction };
        for (Action a : actions)
        {
            JButton button = new JButton(a);
            if (button.getIcon() != null)
            {
                button.setText(""); // an icon-only button
            }
            toolBar.add(button);
        }
    }

    /**
     * Helper method to create the Enable menu
     * @return the completed menu.
     */
    protected JMenu createEnableMenu()
    {
        JMenu ableMenu = new JMenu("Action State");
        cbmi = new JCheckBoxMenuItem[3];

        cbmi[0] = new JCheckBoxMenuItem("First action enabled");
        cbmi[1] = new JCheckBoxMenuItem("Second action enabled");
        cbmi[2] = new JCheckBoxMenuItem("Third action enabled");

        for (int i = 0; i < cbmi.length; i++)
        {
            cbmi[i].setSelected(true);
            cbmi[i].addItemListener(new ItemListener()
            {
                @Override
                public void itemStateChanged(ItemEvent e)
                {
                    JCheckBoxMenuItem mi = (JCheckBoxMenuItem) (e.getSource());
                    boolean selected = (e.getStateChange() == ItemEvent.SELECTED);

                    // Set the enabled state of the appropriate Action.
                    if (mi == cbmi[0])      leftAction.setEnabled(selected);
                    else if (mi == cbmi[1]) middleAction.setEnabled(selected);
                    else if (mi == cbmi[2]) rightAction.setEnabled(selected);
                }
            });
            ableMenu.add(cbmi[i]);
        }

        return ableMenu;
    }

    /**
     * Display the result of the action in he text area.
     * @param actionDescription description of the action
     * @param e the action event
     */
    private void displayResult(String actionDescription, ActionEvent e)
    {
        String s = ("Action event detected: " + actionDescription + newline
                + "    Event source: " + e.getSource() + newline);
        textArea.append(s);
    }

    public class LeftAction extends AbstractAction
    {
        public LeftAction(String text, ImageIcon icon, String desc,
                Integer mnemonic)
        {
            super(text, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        public void actionPerformed(ActionEvent e)
        {
            displayResult("Action for first button/menu item", e);
        }
    }

    public class MiddleAction extends AbstractAction
    {
        public MiddleAction(String text, ImageIcon icon, String desc,
                Integer mnemonic)
        {
            super(text, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        public void actionPerformed(ActionEvent e)
        {
            displayResult("Action for second button/menu item", e);
        }
    }

    public class RightAction extends AbstractAction
    {
        public RightAction(String text, ImageIcon icon, String desc,
                Integer mnemonic)
        {
            super(text, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        public void actionPerformed(ActionEvent e)
        {
            displayResult("Action for third button/menu item", e);
        }
    }

    // Stuff you can customize
    private static String appName = "Action Demo";
    private static int DEFAULT_WIDTH = 600;
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
                ActionDemo app = new ActionDemo();
                app.installMenu(mainFrame);
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
    
}
