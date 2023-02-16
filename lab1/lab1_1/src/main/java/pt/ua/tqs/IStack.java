package pt.ua.tqs;

public interface IStack<T> {

    public T pop();
    public int size();
    public T peek();
    public void push(T v);
    public boolean isEmpty();
    
}
