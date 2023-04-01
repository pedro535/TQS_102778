package pt.ua.tqs.homework.service;

import org.springframework.stereotype.Service;
import pt.ua.tqs.homework.cache.Cache;
import pt.ua.tqs.homework.model.AirQuality;
import pt.ua.tqs.homework.model.CacheStats;


@Service
public class CacheStatsService {

    private Cache<AirQuality> cache;


    public CacheStatsService(Cache<AirQuality> cache) {
        this.cache = cache;
    }


    public CacheStats getCacheStats() {
        return new CacheStats(cache.getHits(), cache.getMisses(), cache.getTotalRequests(), cache.getSize());
    }
    
}
