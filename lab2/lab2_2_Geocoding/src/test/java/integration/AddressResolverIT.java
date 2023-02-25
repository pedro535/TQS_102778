package integration;

import connection.TqsBasicHttpClient;
import geocoding.Address;
import geocoding.AddressResolver;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddressResolverIT {

    private AddressResolver resolver;


    @BeforeEach
    public void init(){
        resolver = new AddressResolver(new TqsBasicHttpClient());
    }

    @Test
    public void whenGoodCoordidates_returnAddress() throws IOException, URISyntaxException, ParseException {

        //Latitude and longitude of DETI
        double latitude = 40.633116;
        double longitude = -8.658784;

        //Call the method to be tested
        Optional<Address> result = resolver.findAddressForLocation(latitude, longitude);

        //Verify the result and the usage of the mock
        assertEquals(result.get(), new Address( "Avenida João Jacinto de Magalhães", "Aveiro", "", "3810-149", null));
    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddrress() throws IOException, URISyntaxException, ParseException {

        //Invalid Latitude and longitude
        double latitude = -300;
        double longitude = -810;

        //Call the method to be tested
        Optional<Address> result = resolver.findAddressForLocation(latitude, longitude);

        //Verify the result ans the usage of the mock
        assertEquals(result, Optional.empty());
        
    }

}
