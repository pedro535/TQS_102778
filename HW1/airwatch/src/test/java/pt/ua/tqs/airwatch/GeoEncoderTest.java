package pt.ua.tqs.airwatch;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pt.ua.tqs.airwatch.model.Location;
import pt.ua.tqs.airwatch.util.GeoEncoder;
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
        String url = "https://www.mapquestapi.com/geocoding/v1/address";
        String response = "{\"info\":{\"statuscode\":0,\"copyright\":{\"text\":\"© 2022 MapQuest, Inc.\",\"imageUrl\":\"http://api.mqcdn.com/res/mqlogo.gif\",\"imageAltText\":\"© 2022 MapQuest, Inc.\"},\"messages\":[]},\"options\":{\"maxResults\":-1,\"ignoreLatLngInput\":false},\"results\":[{\"providedLocation\":{\"location\":\"Aveiro,PT\"},\"locations\":[{\"street\":\"\",\"adminArea6\":\"\",\"adminArea6Type\":\"Neighborhood\",\"adminArea5\":\"Aveiro\",\"adminArea5Type\":\"City\",\"adminArea4\":\"Aveiro\",\"adminArea4Type\":\"County\",\"adminArea3\":\"\",\"adminArea3Type\":\"State\",\"adminArea1\":\"PT\",\"adminArea1Type\":\"Country\",\"postalCode\":\"\",\"geocodeQualityCode\":\"A5XAX\",\"geocodeQuality\":\"CITY\",\"dragPoint\":false,\"sideOfStreet\":\"N\",\"linkId\":\"0\",\"unknownInput\":\"\",\"type\":\"s\",\"latLng\":{\"lat\":40.64123,\"lng\":-8.65391},\"displayLatLng\":{\"lat\":40.64123,\"lng\":-8.65391},\"mapUrl\":\"\"}]}]}";

        //stubbing
        when(httpClient.httpGet(url)).thenReturn(response);
    }


    @Test
    @DisplayName("When getLocationDetails(), then return a location object with Location, CountryCode and Coordinates")
    public void whenGetLocation_thenReturnLocationDetails() throws IOException {
        String url = "https://www.mapquestapi.com/geocoding/v1/address";
        String city = "Aveiro";
        String countryCode = "PT";

        //execute
        Location location = geoEncoder.getLocationDetails(city, countryCode);

        //assert and verify
        assertThat(location.getLocationName()).isEqualTo("Aveiro");
        assertThat(location.getCountryCode()).isEqualTo("PT");
        assertThat(location.getCoord().getLat()).isEqualTo("40.65695");
        assertThat(location.getCoord().getLon()).isEqualTo("-7.91463");

        //verify
        verify(httpClient, times(1)).httpGet(url);
    }
    
    
}
