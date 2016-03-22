package occ.cs272.ic08;
import java.util.*;
/**
 *  CS 272 Example: ArrayListTiming.java
 *
 *  Checks time needed for list operations
 *  using an ArrayList implementation.
 *
 */

public class ArrayListTiming
{
    public static void main(String[] args)
    {
        // Our List variable using the ArrayList implementation
        List<Date> arrayImp = new ArrayList<Date>();

        // We'll store this variable in each element
        Date today = new Date();

        /**
                 *  1. Measure the time for adding to the end of an array
                 */
        //  a. Get the starting time
        long time1, time2;
        time1 = System.currentTimeMillis();

        //  b. Add 10,000 times at the end of the array
        for(int i = 0; i < 100000; i++)
            arrayImp.add(today);

        // c.   Measure the ending time
        time2 = System.currentTimeMillis();
        System.out.println("Time for 100000 adds: " + (time2 - time1));

        /**
         *  2. Measure the time for inserting 10,000 elements at position 50
         */
        time1 = System.currentTimeMillis(); // start time

        for(int i = 0; i < 10000; i++)
            arrayImp.add(50, today);

        time2 = System.currentTimeMillis(); // end
        System.out.println("Time for 10000 adds at position 50: " + (time2 - time1));

        /**
         *  3. Measure the time accessing the 5,000th element, 10,000 times
         */
        time1 = System.currentTimeMillis(); // start

        for(int i = 0; i < 10000; i++)
            arrayImp.get(5000);

        time2 = System.currentTimeMillis(); // end
        System.out.println("Time for 10000 gets at position 5000: " + (time2 - time1));
    }
}
