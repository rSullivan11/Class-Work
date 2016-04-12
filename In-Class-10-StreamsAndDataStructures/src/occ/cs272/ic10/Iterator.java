package occ.cs272.ic10;
// A forward iterator
public interface Iterator<T>
{
    public T get();
    public void set(T e);
    public void next();
    public boolean hasNext();
}


