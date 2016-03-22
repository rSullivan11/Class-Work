package occ.cs272.ic08;
import java.util.*;

/**
 *  CS 272 Example: Algorithms.java
 *
 * Illustrates algorithms in the Collections class.
 *
 */

public class Algorithms
{
    public static void main(String[] args)
    {
        // 1. Create a list of words
        List<String> list = Arrays.asList(
                new String[]{"sat","tat","hat","fat","vat",
                              "cat","rat","bat","mat","oat","pat"}
            );

        // 1. Reverse the list
        Collections.reverse(list);
        System.out.println("Reverse of list is: " + list);

        // 2. Find extreme values
        System.out.println("Max is: " + Collections.max(list));
        System.out.println("Min is: " + Collections.min(list));

        // 3. Sort the list
        Collections.sort(list);
        System.out.println("Sorted list is: " + list);

        // 4. Search the list using a binary search
        System.out.println(
            "Index of rat is: " + Collections.binarySearch(list, "rat"));
        System.out.println(
            "Searching for potato returns: " + Collections.binarySearch(list, "potato"));

        // 5. Unsort (shuffle) the list
        Collections.shuffle(list);
        System.out.println("Shuffled list is: " + list);

        // 6. Copy three elements from the list and copy them to the beginning of the list
        Collections.copy(list,list.subList(5,8));
        System.out.println("List changed at indices 0-2 is:\n\t " + list);

        // 7. Fill in the first three elements with word "fill"
        Collections.fill(list.subList(0,3),"fill");
        System.out.println("List filled at indices 0-2 is:\n\t " + list);

        System.out.println("List with 5 fives is: " + Collections.nCopies(5,"five"));
    }
}
