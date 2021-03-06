package occ.cs272.ic07;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

/**
 *  CS 272 Example: Using the Action interface
 *
 *  Add copy, cut, and paste icons
 *
 *  @author Stephen Gilbert
 *  @version Spring 2014
 *
 */
public class Action01 extends JPanel
{
    /**
     * Class to implement Clipboard Actions
     */
    class ClipAction extends AbstractAction
    {
        // JTextComponent to apply the action to
        private JTextComponent component;

        // Exercise #1 - Complete the constructor.
        //    - just call super and set the component field
        //    - initializes reference to field
        public ClipAction( String name, Icon icon, JTextComponent comp)
        {
        }

        /**
         * Method called when Action is activated.
         */
        public void actionPerformed(ActionEvent ae)
        {
            // See fields defined in Action interface
            String cmd = (String)getValue(NAME);

            // Carry out the actions based on the name
            if (cmd.equalsIgnoreCase("cut"))
                component.cut();
            else if (cmd.equalsIgnoreCase("copy"))
                component.copy();
            else if (cmd.equalsIgnoreCase("paste"))
                component.paste();
        }
        
    }
    
    /** 
     * Helper to create ClipActions.
     */
    private ClipAction makeClipAction(String name, String icon, JTextComponent c)
    {
         return new ClipAction(name, getIcon(icon), c);
    }

    /**
     * Helper method to load images relative to this package.
     */
    private ImageIcon getIcon(String icon)
    {
        URL iconURL = getClass().getResource(icon);
        if (iconURL != null) return new ImageIcon(iconURL);
        System.err.println("Cannot load: " + icon);
        return null;
    }
    
    /**
     * Construct the application and the menu.
     */
    public Action01()
    {
        setLayout(new BorderLayout());
        // Let's add a JTextArea to the center
        add(new JScrollPane(output), BorderLayout.CENTER);
        output.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
    }
    

    /**
     * Install the menu on the JFrame
     */
    private void installMenu(JFrame frame)
    {
        // Menu bar
        JMenuBar mb = new JMenuBar();
        frame.setJMenuBar(mb);

        // 2. Create top-level menus
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");

        // Keyboard mnemonics
        file.setMnemonic(KeyEvent.VK_F);
        edit.setMnemonic(KeyEvent.VK_E);

        mb.add(file);
        mb.add(edit);

        // Add menu items to menus
        /**
         *  File menu items
         */
        JMenuItem fileNew = new JMenuItem("New", getIcon("images/New16.gif"));
        fileNew.setMnemonic(KeyEvent.VK_N);

        JMenuItem fileOpen = new JMenuItem("Open", getIcon("images/Open16.gif"));
        fileOpen.setMnemonic(KeyEvent.VK_N);

        JMenuItem fileExit = new JMenuItem("Exit", KeyEvent.VK_X);

        // An accelerator key
        fileExit.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK));

        /**
         *  Edit menu items
         */
        Action editCut = makeClipAction("Cut", "images/Cut16.gif", output);
        Action editCopy = makeClipAction("Cut", "images/Copy16.gif", output);
        Action editPaste = makeClipAction("Cut", "images/Paste16.gif", output);

        /**
         *  Add items to menus
         */
        file.add(fileNew);
        file.add(fileOpen);
        file.addSeparator();
        file.add(fileExit);

        // Now, add actions to the edit menu
        edit.add(editCut);
        edit.add(editCopy);
        edit.add(editPaste);

        // 4. Hook up the event handlers
        fileExit.addActionListener(fileHandler);
        fileNew.addActionListener(fileHandler);
        fileOpen.addActionListener(fileHandler);
        fileExit.addActionListener(fileHandler);

        JToolBar bar = new JToolBar();
        add(bar, BorderLayout.NORTH);
        bar.setFloatable(false);
        bar.setRollover(true);

        // Exercise # 2: add Action items instead of buttons

    
    }    
    
    /**
     * Instance variables.
     */
    private JTextArea output = new JTextArea();

    private ActionListener fileHandler = new ActionListener()
    {
        // Handle the events generated by the menu
        public void actionPerformed(ActionEvent e)
        {
            String cmd = e.getActionCommand();
            if (cmd.equals("Exit"))
                System.exit(0);
            else
                JOptionPane.showMessageDialog(
                    Action01.this,
                    cmd,
                    "Menu Command",
                    JOptionPane.PLAIN_MESSAGE);
        }
    };
    
    // Stuff you can customize
    private static String appName = "Action Demo 1";
    private static int DEFAULT_WIDTH = 300;
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
                Action01 app = new Action01();
                app.installMenu(mainFrame);
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
    
}
