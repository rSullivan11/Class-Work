package occ.cs272.ic08;
import java.util.*;

/**
 *  CS 272 Example: LinkedListTiming.java
 *
 *  Checks time needed for list operations
 *  using a LinkedList implementation.
 */

public class LinkedListTiming
{
    public static void main(String[] args)
    {
        // Our List variable using the LinkedList implementation
        List<Date> linkImp = new LinkedList<Date>();

        // We'll store this variable in each element
        Date today = new Date();

        /**
         *  1. Measure the time for adding to the end of a linked list
         */
        //  a. Get the starting time
        long time1, time2;
        time1 = System.currentTimeMillis(); // start

        for(int i = 0; i < 10000; i++)
            linkImp.add(today);

        time2 = System.currentTimeMillis(); // end
        System.out.println("Time for 10000 adds: " + (time2 - time1));

        /**
         *  2. Measure the time for inserting 10,000 elements at position 5000
         */
        time1 = System.currentTimeMillis(); // start

        for(int i = 0; i < 10000; i++)
            linkImp.add(5000, today);

        time2 = System.currentTimeMillis(); // end
        System.out.println("Time for 10000 adds at position 5000: " + (time2 - time1));

        /**
         *  3. Measure the time accessing the 5,000th element, 10,000 times
         */
        time1 = System.currentTimeMillis(); // start
        for(int i = 0; i < 10000; i++)
            linkImp.get(5000);
        time2 = System.currentTimeMillis(); // end
        System.out.println("Time for 10000 gets at position 5000: " + (time2 - time1));
    }
}
