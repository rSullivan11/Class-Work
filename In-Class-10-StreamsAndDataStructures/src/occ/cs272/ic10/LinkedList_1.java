package occ.cs272.ic10;

public class LinkedList_1<E>
{
    private Node<E> head;
    private Node<E> tail;
    private int count;
    
    static class Node<T>
    {
        public T data;
        public Node<T> next;
    }

    public void add(E element) {
        Node<E> n = new Node<>();
        
        n.data = element;
        n.next = head;
        head = n;
        ++count;
    }

    @Override
    public String toString() {
        String result = "head->";
        Node<E> current = head;
        
        while (current != null) {
            result += "[" + current.data + "]->";
            current = current.next;
        }
        
        return result + "null";
    }
    
    public void addSecond(E element) {
        Node<E> n = new Node<>(), current = new Node<>();
        n.data = element;
        
        current = head;
        n.next = current.next;
        current.next = n;
        ++count;
    }
    
    public void addBack(E element) {
        Node<E> current = head;
        if (current == null)
            add(element);
        else {
            current = new Node<>();
            current.data = element;
            tail.next = current;
        }
        ++count;
    }
    
    public int size() {
        return count;
    }
    
    // TODO: write the removeFirst method here
    public E removeFirst() {
        E data = head.data;
        head = head.next;
        if (head == null) tail = null;
        --count;
        return data;
    }
    
    // TODO: write the isEmpty method here
    public boolean isEmpty() {
        return head == null;
    }
    
    // TODO: remove last element
    public E removeLast() {
        Node<E> n = head, trail = null;
        
        while(n.next != null) {
            trail = n;
            n = n.next;   
        }
        E data = n.data;
        if (trail != null)
            trail.next = null;
        tail = trail;
        --count;
        return data;
    }
    
    // Trying out the first linked list class
    public static void main(String[] args)
    {
        // SLL-EX01 - add 5 numbers to the Linked List
        System.out.println("Exercise # 1 - add 5 integers to the list");
        LinkedList_1<Integer> list = new LinkedList_1<>();
        for (int i = 0; i < 5; i++)
        {
            System.out.println(" after " + i + " list is " + list);
            list.add(i);
        }
        System.out.println("--done-- list->" + list);
        
        // SLL-EX02 - Write the addSecond method
        System.out.println("\nExercise # 2 - add 40 in second position");
        list.addSecond(40);
        System.out.println("After addSecond(40)->" + list);
        
        // SLL-EX03 - Count the number of elements
        System.out.println("\nExercise # 3 - get the list size()");
        System.out.println("The list has " + list.size() + " elements.");
        
        // SLL-EX04 - Remove the first element
        int first = list.removeFirst();
        System.out.println("\nExercise # 4 - remove the first element");
        System.out.println("After removeFront()->" + list);
        System.out.println("Value removed was->" + first);
        
        // SLL-EX05 - Remove the remaining elements
        System.out.println("\nExercise # 5 - remove remaining with isEmpty");
        while (! list.isEmpty())
            list.removeFirst();
        System.out.println("After removing everything, size is " + list.size());
        
        // SLL-EX06 - Add strings to the back of the list
        // Using the FizzBuzz problem for 1 to 20
        System.out.println("\nExercise # 6 - addBack method");
        LinkedList_1<String> slist = new LinkedList_1<>();
        for (int i = 1; i <= 20; i++)
        {
            if (i % 3 == 0 && i % 5 == 0)
                slist.addBack("FizzBuzz");
            else if (i % 3 == 0)
                slist.addBack("Fizz");
            else if (i % 5 == 0)
                slist.addBack("Buzz");
            else
                slist.addBack("" + i);
        }
        String result = slist.toString();
        System.out.print("After addBack()->" + 
                result.substring(0, 25));
        System.out.println("..." 
                + result.substring(result.length() - 25));
        
        // SLL-EX07 - Remove and print the elements from the
        // back, until the list has only 5 elements.
        System.out.println("\nExercise # 7 - removeLast method");
        while(!slist.isEmpty())
            System.out.print(slist.removeLast() + " ");
        System.out.println();
        System.out.println("After removing, slist->" + slist);
    }
}


