package pt.ua.tqs.homework.ServiceLevelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ua.tqs.homework.connection.Geocoding;
import pt.ua.tqs.homework.connection.IHttpClient;
import pt.ua.tqs.homework.model.Coordinates;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.net.URISyntaxException;


@ExtendWith(MockitoExtension.class)
class GeocodingTest {

    @Mock
    private IHttpClient httpClient;

    @InjectMocks
    private Geocoding geocoding;


    @BeforeEach
    public void setup() {
        geocoding.setReverseGeocodingUrl("http://api.openweathermap.org/geo/1.0/direct");
        geocoding.setApiKey("04c214fc4b062348b7ab5bb426a0151b");
    }


    @Test
    void whenValidInput_thenReturnCoordsObject() throws IOException, URISyntaxException {
        String city = "Aveiro";
        String countryCode = "PT";

        String response = "[{\"name\":\"Aveiro\",\"local_names\":{\"pt\":\"Aveiro\",\"el\":\"Αβέιρο\",\"hu\":\"Aveiro\",\"ar\":\"آويرو\",\"lt\":\"Aveiras\",\"ru\":\"Авейру\"},\"lat\":40.640496,\"lon\":-8.6537841,\"country\":\"PT\"}]";
        when(httpClient.httpGet(anyString())).thenReturn(response);

        //execute
        Coordinates coords = geocoding.getCoords(city, countryCode);

        //assert
        assertThat(coords).isNotNull();
        assertThat(coords.getLat()).isEqualTo(40.640496);
        assertThat(coords.getLon()).isEqualTo(-8.6537841);

        //verify
        verify(httpClient, times(1)).httpGet(anyString());
    }    


    @Test
    void whenInvalidInput_thenReturnNull() throws IOException, URISyntaxException {
        String city = "Aveiro";
        String countryCode = "PT";

        String response = "[]";
        when(httpClient.httpGet(anyString())).thenReturn(response);

        //execute
        Coordinates coords = geocoding.getCoords(city, countryCode);

        //assert
        assertThat(coords).isNull();

        //verify
        verify(httpClient, times(1)).httpGet(anyString());
    }  

}
