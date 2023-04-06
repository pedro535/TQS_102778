package pt.ua.tqs.homework.ServiceLevelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ua.tqs.homework.connection.AirQualityProvider;
import pt.ua.tqs.homework.connection.IHttpClient;
import pt.ua.tqs.homework.model.AirQuality;
import pt.ua.tqs.homework.model.Coordinates;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;


@ExtendWith(MockitoExtension.class)
class AirQualityProviderTest {

    @Mock
    private IHttpClient httpClient;

    @InjectMocks
    private AirQualityProvider airQualityProvider;


    @BeforeEach
    public void setup() {
        airQualityProvider.setAirQualityUrl("http://api.openweathermap.org/data/2.5/air_pollution/forecast");
        airQualityProvider.setApiKey("04c214fc4b062348b7ab5bb426a0151b");
    }


    @Test
    void whenGetCurrentAirQuality_thenReturnTodaysAirQuality() throws IOException, URISyntaxException {
        String city = "Aveiro";
        String countryCode = "PT";
        Coordinates coords = new Coordinates(40.6405, -8.6538);

        String response = "{\"coord\":{\"lon\":-8.6538,\"lat\":40.6405},\"list\":[{\"main\":{\"aqi\":3},\"components\":{\"co\":220.3,\"no\":0.4,\"no2\":1.97,\"o3\":100.14,\"so2\":0.86,\"pm2_5\":2.72,\"pm10\":3.68,\"nh3\":2.06},\"dt\":1680614379}, {\"main\":{\"aqi\":3},\"components\":{\"co\":220.3,\"no\":0.4,\"no2\":1.97,\"o3\":100.14,\"so2\":0.86,\"pm2_5\":2.72,\"pm10\":3.68,\"nh3\":2.06},\"dt\":1680617408}, {\"main\":{\"aqi\":3},\"components\":{\"co\":220.3,\"no\":0.4,\"no2\":1.97,\"o3\":100.14,\"so2\":0.86,\"pm2_5\":2.72,\"pm10\":3.68,\"nh3\":2.06},\"dt\":1680700208}, {\"main\":{\"aqi\":3},\"components\":{\"co\":220.3,\"no\":0.4,\"no2\":1.97,\"o3\":100.14,\"so2\":0.86,\"pm2_5\":2.72,\"pm10\":3.68,\"nh3\":2.06},\"dt\":1680786608}]}";
        when(httpClient.httpGet(anyString())).thenReturn(response);

        //execute
        AirQuality airQuality = airQualityProvider.getAirQualityInfo(city, countryCode, coords, 1);

        //assert
        assertThat(airQuality.getCity()).isEqualTo(city);
        assertThat(airQuality.getCountryCode()).isEqualTo(countryCode);
        assertThat(airQuality.getCoord()).containsEntry("lat", coords.getLat());
        assertThat(airQuality.getCoord()).containsEntry("lon", coords.getLon());
        assertThat(airQuality.getResults()).hasSize(1);  //because is the current days == 1 key date
        assertThat(airQuality.getResults()).containsOnlyKeys("2023-04-04");

        //verify
        verify(httpClient, times(1)).httpGet(anyString());
    }    


    @Test
    void whenGetForecasFor2Days_thenReturn2DaysAirQuality() throws IOException, URISyntaxException {
        String city = "Aveiro";
        String countryCode = "PT";
        Coordinates coords = new Coordinates(40.6405, -8.6538);

        String response = "{\"coord\":{\"lon\":-8.6538,\"lat\":40.6405},\"list\":[{\"main\":{\"aqi\":3},\"components\":{\"co\":220.3,\"no\":0.4,\"no2\":1.97,\"o3\":100.14,\"so2\":0.86,\"pm2_5\":2.72,\"pm10\":3.68,\"nh3\":2.06},\"dt\":1680614379}, {\"main\":{\"aqi\":3},\"components\":{\"co\":220.3,\"no\":0.4,\"no2\":1.97,\"o3\":100.14,\"so2\":0.86,\"pm2_5\":2.72,\"pm10\":3.68,\"nh3\":2.06},\"dt\":1680617408}, {\"main\":{\"aqi\":3},\"components\":{\"co\":220.3,\"no\":0.4,\"no2\":1.97,\"o3\":100.14,\"so2\":0.86,\"pm2_5\":2.72,\"pm10\":3.68,\"nh3\":2.06},\"dt\":1680700208}, {\"main\":{\"aqi\":3},\"components\":{\"co\":220.3,\"no\":0.4,\"no2\":1.97,\"o3\":100.14,\"so2\":0.86,\"pm2_5\":2.72,\"pm10\":3.68,\"nh3\":2.06},\"dt\":1680786608}]}";
        when(httpClient.httpGet(anyString())).thenReturn(response);

        //execute
        AirQuality airQuality = airQualityProvider.getAirQualityInfo(city, countryCode, coords, 2);

        //assert
        assertThat(airQuality.getCity()).isEqualTo(city);
        assertThat(airQuality.getCountryCode()).isEqualTo(countryCode);
        assertThat(airQuality.getCoord()).containsEntry("lat", coords.getLat());
        assertThat(airQuality.getCoord()).containsEntry("lon", coords.getLon());
        assertThat(airQuality.getResults()).hasSize(2);
        assertThat(airQuality.getResults()).containsOnlyKeys("2023-04-04", "2023-04-05");

        //verify
        verify(httpClient, times(1)).httpGet(anyString());
    }


    @Test
    void whenGetForecastForMoreThan4Days_thenReturnNull() throws IOException, URISyntaxException {
        String city = "Aveiro";
        String countryCode = "PT";
        Coordinates coords = new Coordinates(40.6405, -8.6538);

        //execute
        AirQuality airQuality = airQualityProvider.getAirQualityInfo(city, countryCode, coords, 5);

        //assert
        assertThat(airQuality).isNull();

        //verify
        verify(httpClient, times(0)).httpGet(anyString());
    }  




    
}
