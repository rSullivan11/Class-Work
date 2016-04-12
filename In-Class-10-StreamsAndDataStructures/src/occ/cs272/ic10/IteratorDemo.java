package occ.cs272.ic10;
// Some generic methods and iterators
public class IteratorDemo
{
    // TODO: write the method addBack(Sequence<T> list, T ... args)
    
    // TODO: write the method addFront(Sequence<T> list, T ... args)    

    // TODO: write the method insertBefore(Sequence<T> list, T target, T element)
    
    // TODO: write the method T remove(Sequence<T> list, T target)   
    
    public static void main(String[] args)
    {
        System.out.println("Sequences and Iterators");
        System.out.println("------------------------------------------------");
        Sequence<String> list = new LinkedList_2<>();
//        Sequence<String> list = new ArrayList<>();
        System.out.println("1. The addBack generic method.");
        // Write a generic method that adds strings to a list
//        addBack(list, "Frodo", "Gandalf", "Bilbo", "Samwise", "Sauron");
        System.out.println("After creating, list->" + list);
        System.out.println("Expected: head->Frodo->Gandalf->Bilbo->Samwise->Sauron->null");
        
        Iterator<String> iter;
        
        // Reading using the iterator
        System.out.println("\n2. Print each item using the iterator.");
        for (iter = list.iterator(); iter.hasNext(); iter.next())
            System.out.println(iter.get());
        
        // Modifying using the iterator and a for loop
        System.out.println("\n3. Modify each item using the iterator.");
        for (iter = list.iterator(); iter.hasNext(); iter.next())
            iter.set(iter.get().toLowerCase());
        System.out.println("After modification, list->" + list);
        System.out.println("Expected: head->frodo->gandalf->bilbo->samwise->sauron->null");
        
        // Now remove an item in the middle of the list
        System.out.println("\n4. Remove bilbo from the MIDDLE of the list.");
//        String removed = remove(list, "bilbo");
//        System.out.println("Word removed is " + removed);
        System.out.println("List is now->" + list);
        System.out.println("Expected: head->frodo->gandalf->samwise->sauron->null");
        
        // Remove an item from the back of the list
        System.out.println("\n5. Remove sauron from the BACK of the list.");
//        removed = remove(list, "sauron");
//        System.out.println("Word removed is " + removed);
        System.out.println("List is now->" + list);
        System.out.println("Expected: head->frodo->gandalf->samwise->null");
        
        // Remove an item from the front of the list
        System.out.println("\n6. Remove frodo from the FRONT of the list.");
//        removed = remove(list, "frodo");
//        System.out.println("Word removed is " + removed);
        System.out.println("List is now->" + list);
        System.out.println("Expected: head->gandalf->samwise->null");

        // Remove an item from an empty list
        System.out.println("\n7. Remove from an empty list.");
        list = new LinkedList_2<>();
//        list = new ArrayList<>();
//        removed = remove(list, "Gilbert");
//        System.out.println("Word removed is " + removed);
        System.out.println("List is now->" + list);
        System.out.println("Expected: head->null");
        
        // Let's insert into our new list
        System.out.println("\n8. Insert Galadriel right before Sauron.");
//        addFront(list, "Frodo", "Sauron");
//        insertBefore(list, "Sauron", "Galadriel");
        System.out.println("After, list is now->" + list);
        System.out.println("Expected: head->Galadriel->Sauron->Frodo->null");
        
        System.out.println("\n9. Insert Bilbo right before Sauron.");
//        insertBefore(list, "Sauron", "Bilbo");
        System.out.println("After, list is now->" + list);
        System.out.println("Expected: head->Galadriel->Bilbo->Sauron->Frodo->null");
        
        System.out.println("\n10. Insert Samwise right before Stevie.");
//        insertBefore(list, "Stevie", "Samwise");
        System.out.println("After, list is now->" + list);
        System.out.println("Expected: head->Galadriel->Bilbo->Sauron->Frodo->Samwise->null");
    }
}
