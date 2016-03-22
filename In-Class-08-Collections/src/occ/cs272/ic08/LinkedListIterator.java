package occ.cs272.ic08;
import java.util.*;

/**
 *  CS 272 Example: LinkedListIterator.java
 *
 *  Illustrates List and ListIterator methods.
 *
 */

public class LinkedListIterator
{
    public static void main(String[] args)
    {
        // Our List variable using the LinkedList implementation
        List<Date> linkImp = new LinkedList<Date>();

        // We'll store this variable in each element, start with 10,000 items
        Date today = new Date();
        for(int i = 0; i < 10000; i++)
            linkImp.add(today);

        // Get an Iterator and move it to position 50
        ListIterator<Date> iterator = linkImp.listIterator(50);
        System.out.println("Previous index is " + iterator.previousIndex());

        // Add 10,000 items to the list _using the iterator_
        long time1, time2;
        time1 = System.currentTimeMillis();

        for(int i = 0; i < 10000; i++)
            iterator.add(today);

        time2 = System.currentTimeMillis();
        System.out.println("Time for 10000 adds at position 50: " + (time2 - time1));

        // Use the previousIndex() and previous() Iterator methods
        int previousIndex = iterator.previousIndex();
        System.out.println("Previous index is " + previousIndex);
        System.out.println("Previous item is: " + iterator.previous());

        Date tomorrow = new Date(today.getTime() +1000L * 60 * 60 * 24);

        // Replace the current node with a Point object
        iterator.set(tomorrow);
        System.out.println("The item at the previous index is now: "
                           + linkImp.get(previousIndex));
        System.out.println("Next index is " + iterator.nextIndex());

        // Retrieve a subList from the middle of the existing list
        List<Date> threeItems = linkImp.subList(10048,10051);

        // Print the sublist in reverse order
        System.out.println("The sublist in reverse is:");
        for (ListIterator<Date> i = threeItems.listIterator(threeItems.size()); i.hasPrevious();)
            System.out.println("\t" + i.previous());

        // Replace the first item in the sublist with a new Point
        threeItems.set(0, tomorrow);
        System.out.println("Changing threeItems(0) changes linkImp(10048) to:");
        System.out.println("\t" + linkImp.get(10048));
    }
}
