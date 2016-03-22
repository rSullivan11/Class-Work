package occ.cs272.ic08;

import java.util.*;
import java.io.*;

public class Words
{
   public static void main(String[] args)
   {
      Scanner in = new Scanner(System.in);
      // TODO: ...
      Set<String> set = new HashSet<>();
      while (in.hasNextLine())
      {
          System.out.println(in.next());
         // TODO: ...
          //set.add(in.next().toLowerCase());
      }
      System.out.println(set.size() /* TODO: ... */);
   }
}