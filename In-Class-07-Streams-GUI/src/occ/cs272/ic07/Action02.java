package occ.cs272.ic07;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

/**
 *  CS 272 Example: More features of the action interface
 *
 *  @author Stephen Gilbert
 *  @version Spring 2014
 *
 */
public class Action02  extends JPanel
{
    /**
     * Clipboard actions with more features.
     */
    class ClipAction extends AbstractAction implements CaretListener
    {
        private JTextComponent component;

        public ClipAction(String name, Icon icon, JTextComponent comp,
                String desc, KeyStroke accelerator)
        {
            super(name, icon);
            component = comp;

            // Exercise # 1 - Add description and accelerator key

            if (! name.equalsIgnoreCase("paste"))
                component.addCaretListener(this);
        }

        /**
         * Action for cut, copy, paste
         */
        public void actionPerformed(ActionEvent ae)
        {
            String cmd = (String)getValue(NAME);
            if (cmd.equalsIgnoreCase("cut"))
                component.cut();
            else if (cmd.equalsIgnoreCase("copy"))
                component.copy();
            else if (cmd.equalsIgnoreCase("paste"))
                component.paste();
        }

        /**
         * Enable and disable cut and copy
         */
        public void caretUpdate(CaretEvent e)
        {
            // Exercise # 2 - handle events for copy and cut
            // CaretEvent methods
            //  - getDot() is the location of the caret
            //  - getMark() is the location of the other end of a selection
            //
            // Enable this action when they are not the same.
        }
    }

    // Instance variables
    private JTextArea output = new JTextArea();
    private Action editCut, editCopy, editPaste;

    /**
     * Construct the interface.
     */
    public Action02()
    {
        setLayout(new BorderLayout());
        
        // Let's add a JTextArea to the center
        add(new JScrollPane(output), BorderLayout.CENTER);
        output.setFont(new Font("Monospaced", Font.PLAIN, 14));
        output.requestFocus();
    }
    
    /**
     * Install the menu on the JFrame
     */
    private void installMenu(JFrame frame)
    {
        // 1. Create a menu bar
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

        // Example - anonymous actions
        /**
         *  File menu items
         */
        Action fileNew = new AbstractAction("New",
               getIcon("images/New16.gif"))
        {
            public void actionPerformed(ActionEvent ae)
            {
                JOptionPane.showMessageDialog(
                    Action02.this, "New Document",
                    "New Document",
                    JOptionPane.PLAIN_MESSAGE);
            }
        };
        file.add(fileNew).setMnemonic(KeyEvent.VK_N);

        Action fileOpen = new AbstractAction("Open",
                getIcon("images/Open16.gif"))
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Open document here");
            }
        };

        file.add(fileOpen).setMnemonic(KeyEvent.VK_N);

        Action fileExit =
            new AbstractAction("Exit")
            {
                public void actionPerformed(ActionEvent e)
                {
                    System.exit(0);
                }
            };
        JMenuItem fileExitMnu = file.add(fileExit);
        fileExitMnu.setMnemonic(KeyEvent.VK_X);
        fileExitMnu.setAccelerator(
            KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK));

        /**
         *  Edit menu items
         */
        editCut = new ClipAction("Cut", 
                getIcon("images/Cut16.gif"), 
                output,
                "Cut selected text, place in clipboard",
                KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        edit.add(editCut);

        editCopy = new ClipAction("Copy", 
                getIcon("images/Copy16.gif"), 
                output,
                "Copy selected text, and place in clipboard",
                KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        edit.add(editCopy);

        editPaste = new ClipAction("Paste",
                getIcon("images/Paste16.gif"),
                output,
                "Paste contents of clipboard at the caret position",
                KeyStroke.getKeyStroke( KeyEvent.VK_V, InputEvent.CTRL_MASK));
        edit.add(editPaste);

        // Add a toolbar
        JToolBar bar = new JToolBar();
        add(bar, BorderLayout.NORTH);
        bar.setFloatable(false);
        bar.setRollover(true);
        bar.add(editCut);
        bar.add(editCopy);
        bar.add(editPaste);
    }    
    
    /**
     * Helper method for loading an icon on the classpath.
     */
    private ImageIcon getIcon(String icon)
    {
        URL iconURL = getClass().getResource(icon);  // "in package"
        if (iconURL == null)
            iconURL = getClass().getClassLoader().getResource(icon);
        if (iconURL != null)
            return new ImageIcon(iconURL);
        return null;
    }
    
    // Stuff you can customize
    private static String appName = "Action Demo 2";
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
                Action02 app = new Action02();
                app.installMenu(mainFrame);
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
    
}
