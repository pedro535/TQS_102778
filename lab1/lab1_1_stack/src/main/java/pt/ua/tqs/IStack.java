package pt.ua.tqs;

public interface IStack<T> {

    public void push(T v);
    public T pop();
    public T peek();
    public int size();
    public boolean isEmpty();
    
}
