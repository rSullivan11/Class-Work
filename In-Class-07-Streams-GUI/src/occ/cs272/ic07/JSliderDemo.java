package occ.cs272.ic07;
/**
 *  CS 272 - JSliderApp.java
 *
 *  Shows how to use the JSlider widget
 *
 *  @author Stephen Gilbert
 *  @version Spring 2016
 *
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JSliderDemo extends JPanel
{
    // Instance fields
    private JSlider s1;
    private JLabel output = new JLabel("0", JLabel.CENTER);
    
    /**
     *  Construct the application UI
     */
    public JSliderDemo()
    {
        // TODO # 1: Initialize the JSlider
        //  > make it horizontal with a range of 0 to 150
        //  > give it an initial value of 0
        s1 = new JSlider(JSlider.HORIZONTAL, 0, 125, 25);
        
        // TODO # 2: Customize the JSlider
        //  - set the major tick spacing to 25; default is 0
        //  - set the minor tick spacing to 5; default is 0
        //  - use setPaintTicks(true) to turn on painting; false by default
        //  - use setPaintLabels(true) to paint labels; false by default
        s1.setMajorTickSpacing(25);
        s1.setMinorTickSpacing(5);
        s1.setPaintTicks(true);
        s1.setPaintLabels(true);

        // TODO # 3: Hook up the JSlider using an anonymous inner class
        //          The ChangeListener interface has one method,
        //              stateChanged(ChangeEvent e)
        //          Set the text to the current slider value.
        s1.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e)
            {
                // TODO Auto-generated method stub
               output.setText("" + s1.getValue());
            }
        });

        // Change the display of the output label
        output.setFont(new Font("SansSerif", Font.BOLD, 36));
        output.setBackground(Color.white);
        output.setForeground(Color.red);
        output.setText("" + s1.getValue());

        // Construct the Panel
        setLayout(new GridLayout(0,1));
        add(s1);
        add(output);
    }

    // Stuff you can customize
    private static String appName = "JSlider Demo Program";
    private static int DEFAULT_WIDTH = 400;
    private static int DEFAULT_HEIGHT = 150;
    
    
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
                mainFrame.getContentPane().add(new JSliderDemo());
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
    
}
