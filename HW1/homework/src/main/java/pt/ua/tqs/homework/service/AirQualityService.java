package pt.ua.tqs.homework.service;

import java.io.IOException;
import java.net.URISyntaxException;
import org.springframework.stereotype.Service;
import pt.ua.tqs.homework.cache.Cache;
import pt.ua.tqs.homework.connection.AirQualityProvider;
import pt.ua.tqs.homework.connection.Geocoding;
import pt.ua.tqs.homework.model.AirQuality;
import pt.ua.tqs.homework.model.Coordinates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class AirQualityService {

    public static final Logger logger = LoggerFactory.getLogger(AirQualityService.class);

    private Cache<AirQuality> cache;

    private AirQualityProvider airQualityProvider;

    private Geocoding geocoding;


    public AirQualityService(Cache<AirQuality> cache, AirQualityProvider airQualityProvider, Geocoding geocoding) {
        this.cache = cache;
        this.airQualityProvider = airQualityProvider;
        this.geocoding = geocoding;
    }


    public AirQuality getAirQuality(String city, String countryCode, int days) throws IOException, URISyntaxException {

        logger.info("Checking if the cache contains the requested data");

        //check cache
        String cacheKey = String.format("%s-%s-%d", city, countryCode, days);
        AirQuality cacheValue = cache.get(cacheKey);
        
        if (cacheValue != null) {
            return cacheValue;
        }

        //get coordinates
        Coordinates coords = geocoding.getCoords(city, countryCode);

        if (coords == null) {
            return null;
        }

        //get air quality
        AirQuality airQuality = airQualityProvider.getAirQualityInfo(city, countryCode, coords, days);
        
        logger.info("Adding data to cache");
        cache.put(cacheKey, airQuality);
        return airQuality;
    }

}
