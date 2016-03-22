package occ.cs272.ic08;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 *  CS 272 Example: OrderedName.java
 *
 *  Groups fields for a name. Overrides NewName and
 *  implements the Comparable interface.
 */

public class OrderedName extends Name
    implements Comparable<OrderedName>
{

    public OrderedName(String f, String l)
    {
        super(f,l);
    }

    public OrderedName(String f, char i, String l)
    {
        super(f,i,l);
    }


    // Used to order NewOrderName objects
    public int compareTo(OrderedName name)
    {
        // Compare by last name first; if not equal return ordering by last name
        int lastResult = last.compareTo(name.last);
        if (lastResult != 0)
            return lastResult;
        else
        {
            // Now compare first name; if equal, then compare initial
            int firstResult = first.compareTo(name.first);
            if (firstResult != 0)
                return firstResult;
            else
                return (int)initial - (int)name.initial;
        }
    }

    /**
     * A test program.
     * @param args
     */
    public static void main(String[] args)
    {
        SortedSet<OrderedName> set = new TreeSet<OrderedName>();
        OrderedName jackson = new OrderedName("Andrew", "Jackson");
        OrderedName madison = new OrderedName("James", "Madison");

        set.add(new OrderedName("George", "Washington"));
        set.add(new OrderedName("John", "Adams"));
        set.add(new OrderedName("Thomas", "Jefferson"));
        set.add(madison);
        set.add(new OrderedName("James", "Monroe"));
        set.add(new OrderedName("John", 'Q', "Adams"));
        set.add(jackson);
        set.add(new OrderedName("Martin", "Van Buren"));
        set.add(new OrderedName("William", 'H', "Harrison"));
        set.add(new OrderedName("James", 'K', "Polk"));


        System.out.println("The first element is: " + set.first());
        System.out.println("The last element is: " + set.last());
        System.out.println("The J's are: " + set.subSet(jackson, madison));
        System.out.println("A-I are: " + set.headSet(jackson));
        System.out.println("M-Z are: " + set.tailSet(madison));
    }
}

