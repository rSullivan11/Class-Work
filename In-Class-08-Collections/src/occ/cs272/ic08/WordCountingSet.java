package occ.cs272.ic08;

import java.util.*;

public class WordCountingSet
{
    public static void main(String[] args)
    {
        Set<String> mySet = new TreeSet<String>();
        Scanner cin = new Scanner(System.in);
        while (cin.hasNext())
        {
            String word = cin.next();
            word = word.toLowerCase();
            String result = "";
            for (int i = 0; i < word.length(); i++)
                if (Character.isLowerCase(word.charAt(i)))
                    result += word.charAt(i);
            mySet.add(result);
        }
        int i = 0;
        for (String word : mySet)
        {
            i++;
            System.out.println(word);
        }
        System.out.println("Size: " + i);
    }
}