package occ.cs272.ic10;

import occ.cs272.ic10.LinkedList_2.Node;

/**
 * This is the simplified array list from Chapter 16 of Big Java.
 * Modify it to:
 *  a) Use a generic type parameter
 *  b) Implement the Sequence<T> interface
 */
public class ArrayList<E> implements Sequence<E>
{
    private Object[] elements;
    private int currentSize;

    /**
     * Constructs an empty array list.
     */
    public ArrayList()
    {
        final int INITIAL_SIZE = 10;
        elements = new Object[INITIAL_SIZE];
        currentSize = 0;
    }

    /**
     * Gets the size of this array list.
     * 
     * @return the size
     */
    public int size()
    {
        return currentSize;
    }

    /**
     * Throws an IndexOutOfBoundsException if the checked index is out of bounds
     * 
     * @param n the index to check
     */
    private void checkBounds(int n)
    {
        if (n < 0 || n >= currentSize)
        {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Gets the element at a given position.
     * 
     * @param pos the position
     * @return the element at pos
     */
    public E get(int pos)
    {
        checkBounds(pos);
        return (E) elements[pos];
    }

    /**
     * Sets the element at a given position.
     * 
     * @param pos the position
     * @param element the new value
     */
    public void set(int pos, E element)
    {
        checkBounds(pos);
        elements[pos] = element;
    }

    /**
     * Removes the element at a given position.
     * 
     * @param pos the position
     * @return the removed element
     */
    public E remove(int pos)
    {
        checkBounds(pos);

        E removed = (E) elements[pos];

        for (int i = pos + 1; i < currentSize; i++)
        {
            elements[i - 1] = elements[i];
        }

        currentSize--;

        return removed;
    }

    /**
     * Adds an element after a given position.
     * 
     * @param pos the position
     * @param newElement the element to add
     */
    public boolean add(int pos, E newElement)
    {
        growIfNecessary();
        currentSize++;

        checkBounds(pos);

        for (int i = currentSize - 1; i > pos; i--)
        {
            elements[i] = elements[i - 1];
        }

        elements[pos] = newElement;
        return true;
    }

    /**
     * Adds an element after the end of the array list
     * 
     * @param newElement the element to add
     */
    public boolean addLast(E newElement)
    {
        growIfNecessary();
        currentSize++;

        elements[currentSize - 1] = newElement;
        return true;
    }

    /**
     * Grows the elements array if the current size equals the capacity.
     */
    private void growIfNecessary()
    {
        if (currentSize == elements.length)
        {
            Object[] newElements = new Object[2 * elements.length];
            for (int i = 0; i < elements.length; i++)
            {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }

    @Override
    public void add(Iterator<E> iter, E element)
    {
        add(((ArrayListIterator<E>)iter).index, element);  
    }

    @Override
    public E remove(Iterator<E> iter) {
        return remove(((ArrayListIterator<E>)iter).index);
    }

    @Override
    public Iterator<E> iterator()
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public String toString()
    {
        String result = "head->";
        Node<E> current = head;
        
        while (current != null) {
            result += "[" + current.data + "]->";
            current = current.next;
        }
        
        return result + "null";
    }
    
    class ArrayListIterator<T> implements Iterator<T>
    {
        int index;
        ArrayList<T> list;
        public ArrayListIterator(ArrayList<T> a) { list = a; }

        // Implement Iterator<T> interface
        public T get() { return list.get(index); }
        public void set(T e) { list.set(index,  e); }
        public void next() { ++index; }
        public boolean hasNext() { return index != list.size(); }
    }    
}
