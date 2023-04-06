package pt.ua.tqs.homework.service;

import java.io.IOException;
import java.net.URISyntaxException;
import org.springframework.stereotype.Service;
import pt.ua.tqs.homework.cache.Cache;
import pt.ua.tqs.homework.connection.AirQualityProvider;
import pt.ua.tqs.homework.connection.Geocoding;
import pt.ua.tqs.homework.model.AirQuality;
import pt.ua.tqs.homework.model.Coordinates;


@Service
public class AirQualityService {

    private Cache<AirQuality> cache;

    private AirQualityProvider airQualityProvider;

    private Geocoding geocoding;


    public AirQualityService(Cache<AirQuality> cache, AirQualityProvider airQualityProvider, Geocoding geocoding) {
        this.cache = cache;
        this.airQualityProvider = airQualityProvider;
        this.geocoding = geocoding;
    }


    public AirQuality getAirQuality(String city, String countryCode, int days) throws IOException, URISyntaxException {

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
        
        cache.put(cacheKey, airQuality);
        return airQuality;
    }

}
