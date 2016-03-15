package occ.cs272.ic07;
/** CS 272 - Example
*
*  Write binary values to a file. Read the data back.
*
*/
import java.io.*;

public class BinaryIO
{
    public static void main(String [] args)
    {
        // Hardcoded file names for the example
        final String fname = "binary.dat";
        
        try
        {
            // Create a DataOutputStream with a FileOutputStream
            // Use fname for the file name
            DataOutputStream out = new DataOutputStream(new FileOutputStream(fname));

            // Write 10 ints to the file
            for (int i = 0; i < 10; i++)
                ;

            // Write 10 doubles to the file
            for (double d = 0.0; d < 10.0; d++)
                out.writeDouble(d);

            // Close the file

            
            // Open the same file and read the data written
            // Use DataInputStream wrapped around a FileInputStream
            DataInputStream in = new DataInputStream(
                    new FileInputStream("Binary.dat"));
            for (int i = 0; i < 10; i++)
                System.out.print(" " + in.readInt());
            System.out.println();

            for (int i = 0; i < 10; i++)
                System.out.print(in.readDouble() + " ");
            System.out.println();
            // Close the file
            in.close();
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}
