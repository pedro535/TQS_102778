package pt.ua.tqs.airwatch.cache;

public class CacheValue<T> {

    private T value;
    private long expirationTime;

    
    public CacheValue(T value, long ttl) {
        this.value = value;
        expirationTime = System.currentTimeMillis() + ttl;
    }


    public boolean isExpired() {
        //verify if this key expired
        return true;
    }

    
    public T getValue() {
        return value;
    }


    public long getExpirationTime() {
        return expirationTime;
    }


    
}
