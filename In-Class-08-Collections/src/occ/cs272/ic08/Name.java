package occ.cs272.ic08;

import java.util.HashSet;
import java.util.Set;

/**
 *  CS 272 Example: Name.java
 *
 *  Groups fields for a name.
 *  Overrides the toString() method for display.
 *  DOES NOT override hashCode() or equals()
 */

public class Name
{
    protected String first;
    protected char initial;
    protected String last;

    public Name(String f, String l)
    {
        first = f;
        last = l;
    }

    public Name(String f, char i, String l)
    {
        this(f,l);
        initial = i;
    }

    public String toString()
    {
        if (initial == '\u0000')
            return first + " " + last;
        else
            return first + " " + initial + " " + last;
    }
    
    /**
     * Tests this with the HashSet class.
     */
    public static void main(String[] args)
    {
        Set<Name> names = new HashSet<Name>();
        
        names.add(new Name("Stephen", 'D', "Gilbert"));
        names.add(new Name("Stephen", 'D', "Gilbert"));
        names.add(new Name("Stephen", 'D', "Gilbert"));
        names.add(new Name("Robert", 'K', "Zimmerman"));
        
        System.out.println("Here's the set of names");
        System.out.println(names);
    }
}
