package occ.cs272.ic10;

public class LinkedList_2<E> implements Sequence<E>
{
    // TODO: Implement these two methods from the Sequence Interface
    // Then, eliminate all of the other add/remove methods
    public E remove(Iterator<E> iter)
    {
        return null; // replace this, of course
    }

    public void add(Iterator<E> iter, E element)
    {
        // Your code goes here
    }

    ///////////////// IMPLEMENTING THE ITERATOR INTERFACE
    public Iterator<E> iterator()
    {
        return new SLLIterator<>(head);
    }
    
    class SLLIterator<T> implements Iterator<T>
    {
        Node<T> cur, prev;
        public SLLIterator(Node<T> head) { cur = head; }
        
        // Implement Iterator<T> interface
        public T get() { return cur.data; }
        public void set(T e) { cur.data = e; }
        public void next() { prev = cur; cur = cur.next; }
        public boolean hasNext() { return cur != null; }
    }    

    //////////////// CODE FROM LINKEDLIST_1 ////////////////
    @Override
    public String toString()
    {
        String result = "head->";
        Node<E> current = head;
        while (current != null)
        {
            result = result + current.data + "->";
            current = current.next;
        }
        return result + "null";
    }
    
    public int size() { return size; }
    public boolean isEmpty() { return head == null; }

    private Node<E> head;
    private Node<E> tail;
    private int size;
    
    static class Node<T>
    {
        public T data;
        public Node<T> next;
    }
}


