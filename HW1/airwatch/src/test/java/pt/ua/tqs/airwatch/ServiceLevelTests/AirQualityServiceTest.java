package pt.ua.tqs.airwatch.ServiceLevelTests;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pt.ua.tqs.airwatch.model.AirQuality;
import pt.ua.tqs.airwatch.model.Coordinates;
import pt.ua.tqs.airwatch.service.AirQualityService;
import pt.ua.tqs.airwatch.util.IHttpClient;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class AirQualityServiceTest {
    

    @Mock
    private IHttpClient httpClient;

    @InjectMocks
    private AirQualityService airQualityService;



    @BeforeEach
    public void setup() {

        //set api config props, because service uses @Value
        airQualityService.setAirQualityUrl("http://api.openweathermap.org/data/2.5/air_pollution/forecast");
        airQualityService.setReverseGeocodingUrl("http://api.openweathermap.org/geo/1.0/direct");
        airQualityService.setApiKey("");
    }


    @Test
    @DisplayName("When getCoords() with a valid location, return a Coordinates object")
    public void whenValidLocation_thenReturnCoords() throws IOException, URISyntaxException {

        String city = "Aveiro";
        String countryCode = "PT";

        //stubbing
        String response = "[{\"name\":\"Aveiro\",\"local_names\":{\"lt\":\"Aveiras\",\"ru\":\"Авейру\",\"pt\":\"Aveiro\",\"el\":\"Αβέιρο\",\"hu\":\"Aveiro\",\"ar\":\"آويرو\"},\"lat\":40.640496,\"lon\":-8.6537841,\"country\":\"PT\"}]";
        when(httpClient.httpGet(anyString())).thenReturn(response);

        //execute
        Coordinates coords = airQualityService.getCoords(city, countryCode);

        //assert and verify
        assertThat(coords.getLat()).isEqualTo(40.640496);
        assertThat(coords.getLon()).isEqualTo(-8.6537841);

        //verify
        verify(httpClient, times(1)).httpGet(anyString());

    }


    @Test
    @DisplayName("When getCoords() with a invalid location, return null")
    public void whenInvalidLocation_thenReturnNull() throws IOException, URISyntaxException {

        String city = "aaaaa";
        String countryCode = "aaaaa";

        //stubbing
        String response = "[]";
        when(httpClient.httpGet(anyString())).thenReturn(response);

        //execute
        Coordinates coords = airQualityService.getCoords(city, countryCode);

        //assert and verify
        assertThat(coords).isNull();

        //verify
        verify(httpClient, times(1)).httpGet(anyString());
    }


    // @Test
    // @DisplayName("When getAirQualityInfo() with valid coords, return AirQuality object")
    // public void whenValidCoords_thenReturnAirQuality() throws IOException, URISyntaxException {
    //     String city = "Aveiro";
    //     String countryCode = "PT";
    //     Coordinates coords = new Coordinates(40.640496, -8.6537841);

    //     //stubbing
    //     String response = "{\"coord\":{\"lon\":-8.6538,\"lat\":40.6405},\"list\":[{\"main\":{\"aqi\":2},\"components\":{\"co\":195.27,\"no\":0.08,\"no2\":0.79,\"o3\":71.53,\"so2\":1.03,\"pm2_5\":2.26,\"pm10\":4.32,\"nh3\":0},\"dt\":1680367039}]}";
    //     when(httpClient.httpGet(anyString())).thenReturn(response);

    //     //execute
    //     AirQuality results = airQualityService.getAirQualityInfo(city, countryCode, response, 0)

    //     //assert and verify
    //     assertThat(coords).isNull();

    //     //verify
    //     verify(httpClient, times(1)).httpGet(anyString());

    // }

}
