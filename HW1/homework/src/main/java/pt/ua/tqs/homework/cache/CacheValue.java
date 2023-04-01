package pt.ua.tqs.homework.cache;

public class CacheValue<T> {

    private T value;
    private long expirationTime;

    
    public CacheValue(T value, long ttl) {
        this.value = value;
        expirationTime = System.currentTimeMillis() + ttl;
    }


    public boolean isExpired() {
        return System.currentTimeMillis() > expirationTime;
    }

    
    public T getValue() {
        return value;
    }


    public long getExpirationTime() {
        return expirationTime;
    }

}
