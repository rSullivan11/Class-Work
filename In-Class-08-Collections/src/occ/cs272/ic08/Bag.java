package occ.cs272.ic08;
/**
 *  CS 272 Example
 *
 *  Shows conversion between different types of collections
 *
 *  @author Stephen Gilbert
 *  @version Fall 2008
 *
 */

import java.util.*;

public class Bag
{
    public static void main(String[ ] args) throws Exception
    {
        String[] array = {"Frodo", "Bilbo", "Samwise", "Gandalf", "Sauron"};
        Collection<String> c = Arrays.asList(array); // Conversion
        Collection<String>  set01 = new HashSet<String>(c);
        Collection<String>  set02 = new TreeSet<String>(c);
        Collection<String> list01 = new ArrayList<String>(c);
        Collection<String> list02 = new LinkedList<String>(c);
        
        // Now lets' see what we have
        showInfo("HashSet", set01);
        showInfo("TreeSet", set02);
        showInfo("ArrayList", list01);
        showInfo("LinkedList", list02);
    }

    public static void showInfo(String desc, Collection<?> c)
    {
        // Use size and toString
        System.out.println(desc + ", size(" + c.size() + ")->" + c);
        
        // Print using an iterator
        System.out.print("Using an iterator ...");
        Iterator<?> i = c.iterator();
        while (i.hasNext()) System.out.print(" " + i.next());
        System.out.println();
    }
}
