package occ.cs272.ic07;
/** CS 272 - Example
*
*  Using multiple streams with buffering. 
*  Time the results.
*
*/
import java.io.*;

public class BufferedIO
{
    /**
     * Copy one input stream to an output stream.
     * @param input the stream to read from.
     * @param output the stream to write to.
     */
    public static void copyBytes(InputStream input, OutputStream output)
    {
        // TODO: Let's add some buffering to this stream
        
       
        int i;
        try (BufferedInputStream in = new BufferedInputStream(input);
             BufferedOutputStream out = new BufferedOutputStream(output))
        {
            while ((i = in.read()) != -1)
                out.write(i);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String [] args)
    {
        // Hard-coded filenames
        String inFile = "DesignPatternsJavaCompanion.pdf";
        String outFile = "DPJC.pdf";
        
        // Start time
        long start = System.currentTimeMillis();
        System.err.println("Copying " + inFile + " to " + outFile);
        System.err.println("Please wait ...");
        try
        {
            FileInputStream in = new FileInputStream(inFile);
            FileOutputStream out = new FileOutputStream(outFile);
            try
            {
                copyBytes(in, out);
            }
            finally
            {
                in.close();
                out.close();
                // Print ending time
                long done = System.currentTimeMillis();
                System.err.printf("Time: %.2f seconds%n",
                    (double) (done-start)/1000);
            }
        }
        catch(IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
