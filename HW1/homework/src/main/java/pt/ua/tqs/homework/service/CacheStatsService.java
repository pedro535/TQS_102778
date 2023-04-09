package pt.ua.tqs.homework.service;

import org.springframework.stereotype.Service;
import pt.ua.tqs.homework.cache.Cache;
import pt.ua.tqs.homework.model.AirQuality;
import pt.ua.tqs.homework.model.CacheStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class CacheStatsService {

    public static final Logger logger = LoggerFactory.getLogger(CacheStatsService.class);

    private Cache<AirQuality> cache;


    public CacheStatsService(Cache<AirQuality> cache) {
        this.cache = cache;
    }


    public CacheStats getCacheStats() {
        logger.info("Getting cache stats");
        return new CacheStats(cache.getHits(), cache.getMisses(), cache.getTotalRequests(), cache.getSize());
    }
    
}
