package occ.cs272.ic07;
/**
 *  CS 272 Example: illustrates random access in file.
 *
 *  Seek forward and back, and writes and reads
 *  in a random access file.
 *
 *  TIY: Modify RandomAccess.java to write
 *  the same values using type double instead of int. Note that
 *  since a double is 8 bytes instead of 4 bytes, you'll have to
 *  change the way you use seek(). Make the program print out
 *  the values 5.0 and 1.0.
 */

import java.io.*;

public class RandomAccess
{
    public static void main(String [] args)
    {
        try
        {
            RandomAccessFile raf = new RandomAccessFile("random.dat", "rw");

            for (int i=0; i<10; i++)
                raf.writeInt(i);

            raf.seek(20);
            int number = raf.readInt();
            System.out.println("The number starting at byte 20 is " + number);

            raf.seek(4);
            number = raf.readInt();
            System.out.println("The number starting at byte 4 is " + number);

            raf.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

