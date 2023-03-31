package pt.ua.tqs.airwatch.cache;

public interface CacheI<T> {

    public T put(String k, CacheValue<T> v);
    public T get(String k);
    public T remove(String v);
    public int getHits();
    public int getMisses();
    public int getTotalRequests();
    public int getSize();
    
}
