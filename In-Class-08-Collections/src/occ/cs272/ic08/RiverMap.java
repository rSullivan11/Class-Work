package occ.cs272.ic08;
import java.util.*;
/**
 *  CS 272 Example: RiverMap.java
 *
 *  Illustrates Map methods
 */

public class RiverMap
{
    public static void main(String[] args)
    {
        // 1. Our two Map objects using different implementations
        Map<String,Integer> riversHash = new HashMap<String,Integer>(37);   // Predefined size
        Map<String,Integer> riversTree = new TreeMap<String,Integer>();

        // 2. Read data from a properties file and store in riversHash
        ResourceBundle bundle = ResourceBundle.getBundle("rivers");
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements())
        {
            String key = keys.nextElement();
            String value = bundle.getString(key);
            riversHash.put(key, new Integer(value));
        }

        // 3. Let's use some Map methods
        //      Get the size and search for a key and a value
        System.out.println(
            "The size of riversHash is: "
            + riversHash.size());

        System.out.println(
            "riversHash contains key 'Congo': "
            + riversHash.containsKey("Congo"));

        System.out.println(
            "riversHash contains value 3500: "
            + riversHash.containsValue(new Integer(3500)));
        System.out.println();

        // 4. Let's print a set of all of the keys as well as the value
        System.out.println("riversHash set of keys:");
        System.out.println(riversHash.keySet());
        System.out.println();
        System.out.println("riversHash including keys and values:");
        System.out.println(riversHash);
        System.out.println("\n");

        // 5. Copy all riversHash to riversTree and print the contents
        riversTree.putAll(riversHash);
        System.out.println("riversTree");
        System.out.println(riversTree);
        System.out.println();

        // 6. Iterate through riversHash printing selected items
        System.out.println("Rivers with length > 3000");
        for(Map.Entry<String,Integer> item : riversHash.entrySet())
        {
            int size = item.getValue();
            if (size >= 3000)
                System.out.println("\t" + item.getKey() + '\t' + size);
        }

        // 7. Retrieve "Columbia" 100,000 times from riversHash
        long starttime = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++)
            riversHash.get("Columbia");

        long stoptime = System.currentTimeMillis();
        System.out.println(
            "\nTime for 100000 gets in a hash map is "
            + (stoptime-starttime));

        // 8. Do the same for the tree version
        starttime = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++)
            riversTree.get("Columbia");

        stoptime = System.currentTimeMillis();
        System.out.println(
            "Time for 100000 gets in a tree map is "
            + (stoptime-starttime));
    }
}

