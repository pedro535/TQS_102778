package pt.ua.tqs;

import java.util.LinkedList;


public class TqsStack<T> implements IStack<T> {

    private LinkedList<T> collection;

    public TqsStack() {
        collection = new LinkedList<>();
    }

    public T pop() {
        return collection.removeLast(); 
    }

    public int size() {
        return collection.size();
    }

    public T peek() {        
        return collection.getLast();
    }

    public void push(T v) {
        collection.addLast(v);
    }

    public boolean isEmpty() {
        if (collection.size() == 0)
            return true;
        return false;
    }

    
}
