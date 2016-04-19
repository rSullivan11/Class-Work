package occ.cs272.ic11;
/**
 *  CS 272 - FilePrinter.java
 *
 *  Prints a file which extends over
 *  multiple pages. Allows user to select the font.
 *
 *  @author Stephen Gilbert
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.io.*;
import java.util.Vector;

import javax.swing.*;

import java.awt.geom.*;
import java.text.*;

public class FilePrinter extends JPanel
        implements ActionListener, Printable, Pageable
{

    private JButton printFile = new JButton(" None Selected ");
    private JButton print = new JButton(" Print It ");
    private JComboBox<?> fonts;
    private JComboBox<?> fSize;
    private JCheckBox numbered = new JCheckBox("", true);
    private Font font;
    private JFileChooser chooser = new JFileChooser();
    private Vector<String> lines;
    private int lpp, nLines, nPages;
    private float lineHeight;
    private PageFormat pFmt;

    private static final String TAB = "    "; // Quick hack

    /**
     * Constructor just creates the interface
     */
    public FilePrinter()
    {
        JPanel contents = new JPanel();
        contents.setLayout(new BorderLayout(3, 3));

        JPanel labels = new JPanel(new GridLayout(0, 1, 2, 2));
        labels.add(new JLabel("Print File : ", JLabel.RIGHT));
        labels.add(new JLabel("Font : ", JLabel.RIGHT));
        labels.add(new JLabel("Font size : ", JLabel.RIGHT));
        labels.add(new JLabel("Numbering : ", JLabel.RIGHT));
        contents.add(labels, BorderLayout.WEST);

        JPanel widgets = new JPanel(new GridLayout(0, 1, 2, 2));
        widgets.add(printFile);
        printFile.addActionListener(this);

        // Get all of the fonts on the system
        String[] fontList = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getAvailableFontFamilyNames();

        // Add the fonts to a JComboBox
        fonts = new JComboBox<String>(fontList);
        // fonts.setRenderer(new FontCellRenderer());
        // Locate first font named Consolas
        int i = 0;
        for (i = 0; i < fontList.length; i++)
            if (fontList[i].startsWith("Consolas")) break;
        if (i < fontList.length) fonts.setSelectedIndex(i);

        widgets.add(fonts);

        // Add the Font sizes to the widgets
        fSize = new JComboBox<Object>(
                new String[] { "8", "9", "10", "11", "12", "14", "16" });
        fSize.setSelectedIndex(3); // make 11 point the default
        widgets.add(fSize);

        // Add the numbered box and the Print button
        Box b = Box.createHorizontalBox();
        b.add(numbered);
        b.add(Box.createHorizontalGlue());
        b.add(print);
        print.setEnabled(false);
        print.addActionListener(this);
        widgets.add(b);

        contents.add(widgets, BorderLayout.CENTER);
        add(contents);
    }

    /**
     * Event handler for selecting a file and for printing the file
     */
    public void actionPerformed(ActionEvent ae)
    {
        // If source is printFile then select the file to print
        if (ae.getSource() == printFile)
        {
            int returnVal = chooser.showOpenDialog(FilePrinter.this);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                // Read the text into memory
                if (read(chooser.getSelectedFile()))
                {
                    // Print the name on the button
                    printFile.setText(chooser.getSelectedFile().getName());

                    // Enable the print Button
                    print.setEnabled(true);
                }
                else
                {
                    // Nothing read, so disable the print button
                    printFile.setText(" None Selected ");
                    print.setEnabled(false);
                }
            }
            else
            {
                // Nothing read, so disable the print button
                printFile.setText(" None Selected ");
                print.setEnabled(false);
            }
        }
        else if (ae.getSource() == print)
        {

            // Create a PrinterJob and calculate the number of pages
            PrinterJob job = PrinterJob.getPrinterJob();
            calcLines(job.defaultPage());

            // Print the results
            job.setPageable(this);
            if (job.printDialog())
            {
                try
                {
                    job.print();
                }
                catch (PrinterException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Methods for the Pageable interface
     */
    public int getNumberOfPages()
    {
        return nPages;
    }

    public PageFormat getPageFormat(int pageIndex)
    {
        return pFmt;
    }

    public Printable getPrintable(int pageIndex)
    {
        return this;
    }

    /**
     * Print each individual page (implementing the Printable interface)
     */
    public int print(Graphics g, PageFormat pf, int page)
    {
        if (page >= nPages) return NO_SUCH_PAGE;

        // Set the selected font
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(font);

        // Calculate the upper-left corner
        float h = (float) pf.getImageableY();
        float left = (float) pf.getImageableX();

        // Print an outline around the page
        Rectangle2D border = new Rectangle2D.Double(h, left,
                pf.getImageableWidth(), pf.getImageableHeight());
        g2d.draw(border);

        // Create the formatter for the numbering
        NumberFormat formatter = new DecimalFormat("####");
        boolean numbering = numbered.isSelected();

        int i = (page * lpp);
        for (int r = 0; r < lpp && i < nLines; r++, i++)
        {
            g2d.drawString(
                    "  " // 2 spaces
                            + (numbering ? formatter.format(i + 1) + "  "
                                    : "")
                            + lines.elementAt(i),
                    left, h += lineHeight);
        }
        return PAGE_EXISTS;
    }

    /**
     * A helper method that reads the selected file and stores it in the lines
     * Vector instance variable
     * 
     * @param f : the file selected from JFileChooser
     * @returns true if file could be read, false if an error occurs
     */
    private boolean read(File f)
    {
        // Free up old vector
        lines = new Vector<String>();

        // Read the entire file into a Vector
        BufferedReader in = null;
        try
        {
            in = new BufferedReader(new FileReader(f.getAbsolutePath()));
            String line;
            while ((line = in.readLine()) != null)
            {
                if (line.equals("")) line = "     ";
                lines.addElement(line.replaceAll("\t", TAB));
            }
            in.close(); // Really should be in a finally block
        }
        catch (IOException e)
        {
            e.printStackTrace();
            nLines = 0; // Nothing read
            return false;
        }
        return true;
    }

    /**
     * A helper method that calculates the lines per page, the number of lines
     * and the number of pages. The results are stored in the instance variables
     * lpp, nLines and nPages. The method also "tucks away" the PageFormat
     * argument in the instance variable pFmt for use by the Pageable interface
     * method getPageFormat(), and initializes the font instance variable using
     * the values found in the fsize and fonts ComboBoxes
     *
     * @param pf : the PageFormat retrieved from the PrintJob
     *
     *            Note: This method relies on the fact that PageFormat reports
     *            its results in points, and that we know the size of our font
     *            in points. Leading (space between lines) is calcualted at a
     *            fixed 20% of the point size, and stored along with font size,
     *            in the instance variable lineHeight.
     */
    private void calcLines(PageFormat pf)
    {
        // All PageFormat values are in points, as are fonts
        font = new Font(fonts.getSelectedItem().toString(), Font.PLAIN, 1);
        String fSizeStr = (String) (fSize.getSelectedItem());
        float size = Float.parseFloat(fSizeStr);
        font = font.deriveFont(size);

        // Set leading (space between lines) @ 20%
        lineHeight = size += size * .2F;

        // Calculate the number of lines, lpp, pages
        lpp = (int) (pf.getImageableHeight() / lineHeight);
        nLines = lines.size();
        nPages = (nLines / lpp) + (nLines % lpp > 0 ? 1 : 0);
        pFmt = pf;
    }

    // Stuff you can customize
    private static String appName = "Print a File";

    /**
     * Generic main() method for SwingGUI App
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        // Set the system look and feel
        try
        {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            /* nothing to do */}

        // Always start Swing programs on the event queue
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                JFrame mainFrame = new JFrame(appName);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JPanel app = new FilePrinter();
                mainFrame.getContentPane().add(app);
                mainFrame.pack();
                mainFrame.setVisible(true);
            }
        });
    }

}
