package occ.cs272.ic08;
/**
 *  CS 272 Example: WordSet.java
 *
 *  Adds the words of the input to a set, counting
 *  the number of duplicates and listing the unique words.
 */

import java.util.*;
import java.io.*;

public class WordsSet
{
    public static void main(String[ ] args) throws Exception
    {

        int dupes = 0;
        int distinctWords = 0;

        // 1. Create a Set to hold the data
        Set<String> set = new HashSet<>();
        
        Scanner in = new Scanner(new File("gettysburg.txt"));
        in.useDelimiter("\\W");
        while(in.hasNext())
        {
            // 2. Add lowercase words.
            // Count if already in set.
            String word = in.next().trim().toLowerCase();
            if (word.length() > 0 && ! set.add(word))
                ++dupes;
        }

        // 3. Calculate the number of distinct words
        distinctWords = set.size();
        
        System.out.println("Input has " + distinctWords + " distinct words.");
        System.out.println ("There are " + dupes + " duplicate words.");
        System.out.println("The distinct words are:");
        // 4. Print the distinct words
        System.out.println(set);
    }
}

