package occ.cs272.ic08;
import java.util.*;
import java.io.*;
/**
 *  CS 272 Example: SetMethods.java
 *
 *  Illustrates the methods of the Set interface.
 */

public class SetMethods
{
    public static void main(String[ ] args) throws Exception
    {
        // 1. Open the file and set it read only words
        Scanner in = new Scanner(new File("gettysburg.txt"));
        in.useDelimiter("\\W");


        // 2. Create a HashSet and add all the words to the Set
        Set<String> set = new HashSet<String>();
        while(in.hasNext())
        {
            set.add(in.next().toLowerCase());
        }

        // 3. Check the contains() method
        System.out.println("Contains \"fourscore\"? " + set.contains("fourscore"));
        System.out.println("Contains \"computer\"? " + set.contains("computer"));

        // 4. Create a second HashSet and add the first 4 words to it
        Set<String> start = new HashSet<String>();
        start.add("fourscore");
        start.add("and");
        start.add("seven");
        start.add("years");

        // 5. Test the containsAll() method
        System.out.println("Contains \"fourscore, and, seven, years\" ? "
                           + set.containsAll(start));

        // 6. Create a third HashSet() and use addAll() to add second to it
        Set<String> newStart = new HashSet<String>();
        newStart.addAll(start);

        // Are the second and third Sets equal?
        System.out.println("newStart equals start? " + newStart.equals(start));

        // 7. Add "computer" to the third one and check equality again
        newStart.add("computer");
        System.out.println("newStart with \"computer\" equals start? " + newStart.equals(start));
        System.out.println("The newStart set is now " + newStart);

        // 8. Remove everything from the third that is not in the second
        newStart.retainAll(set);
        System.out.println("newStart equals start after retainsAll? " + newStart.equals(start));

        // 9. Convert to an Object array and print
        String[] startArray = start.toArray(new String[0]);
        System.out.print("The start array is now: ");
        for (int i = 0; i < startArray.length; i++)
            System.out.print(startArray[i] + " ");
        System.out.println();

        // 10. Add different types of data to the Set?
        // start.add(new Integer(5));

        // 11. Try to remove an element from the Set
        System.out.println("Removing and worked? " + start.remove("and"));
        System.out.println("The start set is now " + start);

        // 12. Use the bulk method removeAll()
        System.out.println("Removing newStart elements from start worked? "
                           + start.removeAll(newStart));
        System.out.println("The start set is now " + start);

        // 13. Remove the short words from the set, using an iterator
        Iterator<String> setIterator =  set.iterator();
        while(setIterator.hasNext())
        {
            String next = setIterator.next();
            if (next.length() < 8)
                setIterator.remove();
        }
        System.out.println("The long words of gettysburg.txt are: " + set);
    }
}
