package pt.ua.tqs.homework.ServiceLevelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ua.tqs.homework.connection.AirQualityProvider;
import pt.ua.tqs.homework.connection.IHttpClient;
import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class AirQualityProviderTest {

    @Mock
    private IHttpClient httpClient;

    @InjectMocks
    private AirQualityProvider airQualityProvider;





    
}
