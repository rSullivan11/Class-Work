package occ.cs272.ic08;
import java.util.*;
/**
 *  CS 272 Example: BadCompare.java
 *
 *  Shows that the Name class does not behave properly
 *  using inherited equals and hashCode methods.
 */

public class BadCompare
{
    public static void main(String[] args)
    {
        // 1. Create two Name objects with same contents
        Name president = new Name ("George", "Washington");
        Name first = new Name ("George", "Washington");

        // 2. See if they are equal
        System.out.println("Should be equal, but equals returns: " + first.equals(president));

        // 3. See what their hashcode values are and compare them
        int presidentCode = president.hashCode();
        int firstCode = first.hashCode();

        System.out.print("The hash codes for first [" + firstCode );
        System.out.print("] and president [" + presidentCode);
        System.out.println("] are: " + (presidentCode == firstCode ? "equal" : "not equal"));

        // 4. See how the hashcode values affect the HashSet class
        Set<Name> s = new HashSet<Name>();
        s.add(president);
        System.out.print("Should contain George Washington, ");
        System.out.println("but contains(first) returns: " + s.contains(first));

        // 5. See how the hashcode values affect the HashMap class
        Map<Name,String> m = new HashMap<Name,String>();
        m.put(president, "first");
        System.out.println("Should get 'first', but get returns: " + m.get(first));

        // See what happens with toString()
        System.out.println("toString() overridden so first is: " + first);
    }
}
