package occ.cs272.ic10;
/**
 * Simplified interface for CS 272 sequences.
 */
public interface Sequence<E>
{
    public void add(Iterator<E> iter, E element);
    public E remove(Iterator<E> iter);
    public Iterator<E> iterator();
}


