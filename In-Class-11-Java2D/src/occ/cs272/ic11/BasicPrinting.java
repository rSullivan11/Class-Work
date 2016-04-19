package occ.cs272.ic11;

/**
 *  CS 272 - Example
 *
 *  Prints shapes and text
 *
 *  EX01. Omit the call to printDialog(). Does the example still
 *        work as before?
 */

import java.awt.*;
import java.awt.print.*;
import java.awt.geom.*;
import java.awt.event.*;

import javax.swing.*;
import static java.awt.BasicStroke.*;

public class BasicPrinting extends JPanel implements Printable
{
    public BasicPrinting()
    {
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        final JButton button = new JButton(" Print ");
        buttonPanel.add(button);
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPrintable(BasicPrinting.this);
                if (job.printDialog())
                {
                    try {
                        job.print();
                    }
                    catch(PrinterException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        add(buttonPanel, BorderLayout.SOUTH);
        
        setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        drawPage(g2);
    }

    private void drawPage(Graphics2D g2)
    {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        Stroke sqMi = new BasicStroke(8f, CAP_ROUND, JOIN_MITER);
        g2.setStroke(sqMi);
        g2.setFont(new Font("Monospaced",Font.BOLD, 30));
        g2.drawString("Hi there", 200, 150);
        g2.draw(new Line2D.Double(100,150,140,90));
        g2.draw(new Line2D.Double(180,150,140,90));
    }

    public int print(Graphics g, PageFormat f, int i)
    {
        System.out.println("Page no = " + i);
        if (i != 0)
            return NO_SUCH_PAGE;
        drawPage((Graphics2D)g);
        return PAGE_EXISTS;
    }

    // Stuff you can customize
    private static String appName = "Basic Printing";
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
                JPanel app = new BasicPrinting();
                mainFrame.getContentPane().add(app);
                mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mainFrame.setVisible(true);
            }
         });
    }
    
}
