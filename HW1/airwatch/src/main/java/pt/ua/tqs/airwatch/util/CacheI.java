package pt.ua.tqs.airwatch.util;

public interface CacheI<T> {

    public T put(String k, CacheValue<T> v);
    public T get(String k);
    public T remove(String v);
    public int getHits();
    public int getMisses();
    public int getTotalRequests();
    
}
