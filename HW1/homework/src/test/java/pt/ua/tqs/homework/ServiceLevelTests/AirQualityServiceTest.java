package pt.ua.tqs.homework.ServiceLevelTests;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ua.tqs.homework.cache.Cache;
import pt.ua.tqs.homework.connection.AirQualityProvider;
import pt.ua.tqs.homework.connection.Geocoding;
import pt.ua.tqs.homework.model.AirQuality;
import pt.ua.tqs.homework.model.Coordinates;
import pt.ua.tqs.homework.service.AirQualityService;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AirQualityServiceTest {
    
    @Mock
    private Cache<AirQuality> cache;

    @Mock
    private Geocoding geocoding;

    @Mock
    private AirQualityProvider airQualityProvider;

    @InjectMocks
    private AirQualityService airQualityService;

    private String city;
    private String countryCode;
    private int totalDays;
    private Map<String, Double> coords;
    private Coordinates coordsObj;


    @BeforeEach
    public void setup() throws IOException, URISyntaxException {
        city = "Aveiro";
        countryCode = "PT";
        totalDays = 1;

        coords = new HashMap<>();
        coords.put("lat", 40.640496);
        coords.put("lon", -8.6537841);

        coordsObj = new Coordinates(40.640496, -8.6537841);
    }


    @Test
    void whenAirQualityInCache_thenReturnAirQualityFromCache() throws IOException, URISyntaxException {

        //stubbing
        when(cache.get( city + "-" + countryCode + "-" + totalDays )).thenReturn(new AirQuality(city, countryCode, coords));

        //execute
        AirQuality airQuality = airQualityService.getAirQuality(city, countryCode, totalDays);

        //assert
        assertThat(airQuality).isNotNull();
        assertThat(airQuality.getCity()).isEqualTo(city);
        assertThat(airQuality.getCountryCode()).isEqualTo(countryCode);
        assertThat(airQuality.getCoord()).isEqualTo(coords);

        //verify if the cache.get was the only method to be invoked
        verify(cache, times(1)).get(city + "-" + countryCode + "-" + totalDays);
        verify(geocoding, times(0)).getCoords(city, countryCode);
        verify(airQualityProvider, times(0)).getAirQualityInfo(city, countryCode, new Coordinates(40.640496, -8.6537841), totalDays);
        verify(cache, times(0)).put(city + "-" + countryCode + "-" + totalDays, airQuality);
    }


    @Test
    void whenAirQualityNotInCacheAndValidCoords_thenReturnAirQuality() throws IOException, URISyntaxException {

        //stubbing
        when(cache.get( city + "-" + countryCode + "-" + totalDays )).thenReturn(null);
        when(geocoding.getCoords(city, countryCode)).thenReturn(coordsObj);
        when(airQualityProvider.getAirQualityInfo(city, countryCode, coordsObj, totalDays)).thenReturn(new AirQuality(city, countryCode, coords));

        //execute
        AirQuality airQuality = airQualityService.getAirQuality(city, countryCode, totalDays);

        //assert
        assertThat(airQuality).isNotNull();
        assertThat(airQuality.getCity()).isEqualTo(city);
        assertThat(airQuality.getCountryCode()).isEqualTo(countryCode);
        assertThat(airQuality.getCoord()).isEqualTo(coords);

        //verify
        verify(cache, times(1)).get(city + "-" + countryCode + "-" + totalDays);
        verify(geocoding, times(1)).getCoords(city, countryCode);
        verify(airQualityProvider, times(1)).getAirQualityInfo(city, countryCode, coordsObj, totalDays);
        verify(cache, times(1)).put(city + "-" + countryCode + "-" + totalDays, airQuality);
    }


    @Test
    void whenAirQualityNotInCacheAndInvalidCoords_thenReturnNull() throws IOException, URISyntaxException {

        //stubbing
        when(cache.get( city + "-" + countryCode + "-" + totalDays )).thenReturn(null);
        when(geocoding.getCoords(city, countryCode)).thenReturn(null);

        //execute
        AirQuality airQuality = airQualityService.getAirQuality(city, countryCode, totalDays);

        //assert
        assertThat(airQuality).isNull();

        //verify
        verify(cache, times(1)).get(city + "-" + countryCode + "-" + totalDays);
        verify(geocoding, times(1)).getCoords(city, countryCode);
        verify(airQualityProvider, times(0)).getAirQualityInfo(city, countryCode, coordsObj, totalDays);
        verify(cache, times(0)).put(city + "-" + countryCode + "-" + totalDays, airQuality);
    }

}
