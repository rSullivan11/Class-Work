package occ.cs272.ic08;
import java.util.*;
/**
 *  CS 272 Example: Ordering.java
 *
 *  Illustrates the SortedSet interface with user-defined class
 *
 */

public class Ordering
{
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

