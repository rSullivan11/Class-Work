package occ.cs272.ic08;
/**
 *  CS 272 Example: NewName.java
 *
 *  Groups fields for a name. Overrides equals(), hashCode(),
 *  and toString().
 */

public class NewName extends Name
{

    public NewName(String f, String l)
    {
        super(f,l);
    }

    public NewName(String f, char i, String l)
    {
        super(f,i,l);
    }

    // Comparing two objects for value equality
    public boolean equals(Object object) // Must use Object arg to override
    {
        if ( object == this ) return true;

        // Only compare NewName objects, not Name objects
        if (!(object instanceof NewName))
            return false;

        NewName name = (NewName)object;

        // Use built-in String equals
        return first.equals(name.first)
                && initial == name.initial
                && last.equals(name.last);
    }

    // Use String hashCode() method to calculate value
    public int hashCode()
    {
        int result = 17;
        result = 37 * result + first.hashCode();
        result = 37 * result + (int)initial;
        result = 37 * result + last.hashCode();

        return result;
    }
}
