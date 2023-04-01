package pt.ua.tqs.airwatch;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pt.ua.tqs.airwatch.model.Location;
import pt.ua.tqs.airwatch.service.GeoEncoder;
import pt.ua.tqs.airwatch.util.IHttpClient;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class GeoEncoderTest {

    @Mock
    private IHttpClient httpClient;

    @InjectMocks
    private GeoEncoder geoEncoder;


    @BeforeEach
    public void setup() throws IOException {
        //setup geoEncoder api
        geoEncoder.setBaseUrl("http://api.openweathermap.org/geo/1.0/direct");
        geoEncoder.setApiKey("04c214fc4b062348b7ab5bb426a0151b");
    }


    @Test
    @DisplayName("When a valid location is given, then return a location object with Location, CountryCode and Coordinates")
    public void whenValidLocation_thenReturnLocationDetails() throws IOException, URISyntaxException {
        String city = "Aveiro";
        String countryCode = "PT";

        //stubbing
        String response = "[{\"name\":\"Aveiro\",\"local_names\":{\"pt\":\"Aveiro\",\"el\":\"Αβέιρο\",\"hu\":\"Aveiro\",\"ar\":\"آويرو\",\"lt\":\"Aveiras\",\"ru\":\"Авейру\"},\"lat\":40.640496,\"lon\":-8.6537841,\"country\":\"PT\"}]";
        when(httpClient.httpGet(anyString())).thenReturn(response);

        //execute
        Location location = geoEncoder.getLocationDetails(city, countryCode);

        //assert and verify
        assertThat(location.getLocationName()).isEqualTo("Aveiro");
        assertThat(location.getCountryCode()).isEqualTo("PT");
        assertThat(location.getCoord().getLat()).isEqualTo(40.640496);
        assertThat(location.getCoord().getLon()).isEqualTo(-8.6537841);

        //verify
        verify(httpClient, times(1)).httpGet(anyString());
    }


    @Test
    @DisplayName("When an invalid location is given, then return null")
    public void whenInvalidLocation_thenReturnNull() throws IOException, URISyntaxException {
        String city = "123456";
        String countryCode = "PT";

        //stubbing
        when(httpClient.httpGet(anyString())).thenReturn("[]");

        //execute
        Location location = geoEncoder.getLocationDetails(city, countryCode);

        //assert and verify
        assertThat(location).isNull();

        //verify
        verify(httpClient, times(1)).httpGet(anyString());
    }
    
    
}
