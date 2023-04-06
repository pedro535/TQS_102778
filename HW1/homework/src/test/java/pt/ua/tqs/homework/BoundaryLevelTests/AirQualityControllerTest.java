package pt.ua.tqs.homework.BoundaryLevelTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pt.ua.tqs.homework.controller.AirQualityController;
import pt.ua.tqs.homework.model.AirQuality;
import pt.ua.tqs.homework.service.AirQualityService;
import java.util.HashMap;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;


@WebMvcTest(AirQualityController.class)
class AirQualityControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AirQualityService airQualityService;


    /*
     * Get Today's Air Quality tests
     */

    @Test
    void whenCurrentAirQualityAvailable_thenReturnCurrentAirQuality() throws Exception {
        String city = "Aveiro";
        String countryCode = "PT";
        int totalDays = 1;

        //stubbing
        AirQuality airQuality = new AirQuality(city, countryCode, new HashMap<>());
        when(airQualityService.getAirQuality(city, countryCode, totalDays)).thenReturn(airQuality);

        //execute
        mvc.perform(
            get("/api/airquality/current")
            .param("city", city)
            .param("countryCode", countryCode)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.city", is(city)))
            .andExpect(jsonPath("$.countryCode", is(countryCode)));

        //verify
        verify(airQualityService, times(1)).getAirQuality(city, countryCode, totalDays);
    }


    @Test
    void whenCurrentAirQualityUnavailable_thenReturn404() throws Exception {
        String city = "Aveiro";
        String countryCode = "PT";
        int totalDays = 1;

        //stubbing
        when(airQualityService.getAirQuality(city, countryCode, totalDays)).thenReturn(null);

        //execute
        mvc.perform(
            get("/api/airquality/current")
            .param("city", city)
            .param("countryCode", countryCode)
        )
            .andExpect(status().isNotFound()
        );

        //verify
        verify(airQualityService, times(1)).getAirQuality(city, countryCode, totalDays);
    }


    /*
     * Get Air Quality Forecast tests
     */

    @Test
    void whenAirQualityForecastAvailable_thenReturntAirQualityForecast() throws Exception {
        String city = "Aveiro";
        String countryCode = "PT";
        int totalDays = 2;

        //stubbing
        AirQuality airQuality = new AirQuality(city, countryCode, new HashMap<>());
        when(airQualityService.getAirQuality(city, countryCode, totalDays)).thenReturn(airQuality);

        //execute
        mvc.perform(
            get("/api/airquality/forecast")
            .param("city", city)
            .param("countryCode", countryCode)
            .param("days", Integer.toString(totalDays))
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.city", is(city)))
            .andExpect(jsonPath("$.countryCode", is(countryCode))
        );

        //verify
        verify(airQualityService, times(1)).getAirQuality(city, countryCode, totalDays);
    }


    @Test
    void whentAirQualityForecastUnavailable_thenReturn404() throws Exception {
        String city = "Aveiro";
        String countryCode = "PT";
        int totalDays = 2;

        //stubbing
        when(airQualityService.getAirQuality(city, countryCode, totalDays)).thenReturn(null);

        //execute
        mvc.perform(
            get("/api/airquality/forecast")
            .param("city", city)
            .param("countryCode", countryCode)
            .param("days", Integer.toString(totalDays))
        )
            .andExpect(status().isNotFound()
        );

        //verify
        verify(airQualityService, times(1)).getAirQuality(city, countryCode, totalDays);
    }

    
}
