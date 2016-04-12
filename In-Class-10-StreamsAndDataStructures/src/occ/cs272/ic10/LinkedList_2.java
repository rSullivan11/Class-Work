package occ.cs272.ic10;

public class LinkedList_2<E> implements Sequence<E>
{
    // Then, eliminate all of the other add/remove methods
    public E remove(Iterator<E> iterator)
    {
        E result = iterator.get();
        SLLIterator<E> iter = (SLLIterator<E>) iterator;
        --size;
        
        if (! iter.hasNext()) {
            if (iter.prev == null) head = tail = null;
            else {
                iter.prev.next = null;
                tail = iter.prev;
            }
        } else if (iter.prev == null) head = head.next;
        else
            iter.prev.next = iter.cur.next;
        
        return result;
    }

    public void add(Iterator<E> iterator, E element)
    {
        // Your code goes here
        SLLIterator<E> iter = (SLLIterator<E>)iterator;
        Node<E> n = new Node<>();
        n.data = element;
        
        ++size;
        if (! iter.hasNext()) {
            if (head == null) head = n;
            else tail.next = n;
            tail = n;
        } else if (iter.prev == null) {
            n.next = head;
            head = n;
        } else {
            n.next = iter.cur;
            iter.prev.next = n;
        }
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


