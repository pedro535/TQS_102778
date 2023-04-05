package pt.ua.tqs.homework.cache;

public interface ICache<T> {

    public void put(String k, T v);
    public T get(String k);
    public T remove(String v);
    public boolean contains(String k);
    public int getHits();
    public int getMisses();
    public int getTotalRequests();
    public int getSize();
    
}
