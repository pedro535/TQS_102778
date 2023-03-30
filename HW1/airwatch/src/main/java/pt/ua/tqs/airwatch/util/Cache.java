package pt.ua.tqs.airwatch.util;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class Cache<T> implements CacheI<T> {

    private Map<String, CacheValue<T>> map;
    private int misses;
    private int hits;
    
    @Value("${cache.ttl}")
    private long ttl;


    public Cache() {
        map = new HashMap<>();
        misses = 0;
        hits = 0;
    }


    @Scheduled(fixedRateString = "${cache.cleaner.interval}")
    public void cleanCache() {
        System.out.println("DELETE CACHE!");
    }
    
    
    public T put(String k, CacheValue<T> v) {
        return null;
    }
    
    
    public T get(String k) {
        return null;
    }
    
    
    public T remove(String v) {
        return null;
    }
    
    
    public int getHits() {
        return 0;
    }
    
    
    public int getMisses() {
        return 0;
    }
    
    
    public int getTotalRequests() {
        return 0;
    }
    
    
    public long getTtl() {
        return ttl;
    }
    
    
    public void setTTL(long ttl) {
        this.ttl = ttl;
    }

}
