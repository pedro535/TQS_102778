package pt.ua.tqs.homework.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;


@Component
public class Cache<T> implements ICache<T> {

    private Map<String, CacheValue<T>> map;
    private int misses;
    private int hits;
    private static final long TTL = 20000L;
    private static final long CLEAN_INTERVAL = 2000L;


    public Cache() {
        map = new ConcurrentHashMap<>();
        misses = 0;
        hits = 0;
        initializeCleaner();
    }


    private void initializeCleaner() {
        new CacheCleaner().start();
    }

    
    public void put(String k, T v) {
        map.put(k, new CacheValue<>(v, TTL));
    }
    
    
    public T get(String k) {
        
        if (map.containsKey(k)) {
            hits++;
            return map.get(k).getValue();
        }

        misses++;
        return null;
    }
    
    
    public T remove(String k) {
        
        if (map.containsKey(k)) {
            return map.remove(k).getValue();
        }

        return null;
    }
    
    
    public int getHits() {
        return hits;
    }
    
    
    public int getMisses() {
        return misses;
    }
    
    
    public int getTotalRequests() {
        return (hits + misses);
    }
    
    
    public long getTTL() {
        return TTL;
    }


    public int getSize() {
        return map.size();
    }


    class CacheCleaner extends Thread {
        
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(CLEAN_INTERVAL);
                    cleanCache();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        private void cleanCache() {

            //clean expired entries from cache
            for (Map.Entry<String, CacheValue<T>> entry : map.entrySet()) {
                if (entry.getValue().isExpired()) {
                    map.remove(entry.getKey());
                }
            }
        }
        
    }


}
