package occ.cs272.ic07;
/**
 *  CS 272 - JProgressBarDemo.java
 *
 *  Shows how to use the JProgressBar widget
 *
 *  @author Stephen Gilbert
 *  @version Spring 2014
 *
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class JProgressBarDemo extends JPanel
{
    // Create a JProgressBar and a JButton
    private JProgressBar bar = new JProgressBar();
    private JButton      btn = new JButton("Start");

    /**
     * Constructs the UI
     */
    public JProgressBarDemo()
    {
        setLayout(new BorderLayout());

        // Initialize the JProgressBar, add to App
        bar.setBorderPainted(true);
        bar.setStringPainted(true);
        bar.setValue(0);
        add(bar, BorderLayout.CENTER);

        // 3. Add JButton to south in FlowLayout panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btn);
        add(buttonPanel, BorderLayout.SOUTH);

        // 4. Start a new task when Start button pressed
        btn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                new Thread(new Runnable() {
                    // Task when Start button is pressed
                    public void run()
                    {
                        // 1. Disable the Start button
                        btn.setEnabled(false);

                        // 2. Choose a random job length
                        int jobLength = (int)(Math.random() * 1000) + 100;

                        // 3. Initialize the JProgressBar
                        bar.setMaximum(jobLength);  // How long is the job?
                        bar.setString(null);        // Display % while painting
                        bar.setValue(0);            // Start at 0

                        for (int i = 0; i < jobLength; i++)
                        {
                            // 4. After each "chunk", update the progress bar
                            bar.setValue(i);

                            try  {
                                Thread.sleep(10);
                            } catch (InterruptedException ie) { }
                        }

                        // 5. Change the string displayed in the progress bar
                        bar.setValue(jobLength);
                        bar.setString("Job Finished");

                        // 6. Reset the Start button
                        btn.setEnabled(true);
                    }
                    
                }).start();
            }
        });
    }


    // Stuff you can customize
    private static String appName = "JProgressBar Demo Program";
    private static int DEFAULT_WIDTH = 300;
    private static int DEFAULT_HEIGHT = 100;
    
    
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
                mainFrame.getContentPane().add(new JProgressBarDemo());
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
}
