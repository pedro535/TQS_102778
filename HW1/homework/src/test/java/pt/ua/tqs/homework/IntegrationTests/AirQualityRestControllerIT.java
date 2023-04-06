package pt.ua.tqs.homework.IntegrationTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import pt.ua.tqs.homework.HomeworkApplication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;


@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = HomeworkApplication.class)
@AutoConfigureMockMvc
class AirQualityRestControllerIT {

    @Autowired
    private MockMvc mvc;


    /*
     * Get Today's Air Quality tests
    */

    @Test
    void whenValidCityAndCountryCode_thenReturnCurrentAirQuality() throws Exception {
        String city = "Aveiro";
        String countryCode = "PT";

        //execute
        mvc.perform(
            get("/api/airquality/current")
            .param("city", city)
            .param("countryCode", countryCode)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.city", is(city)))
            .andExpect(jsonPath("$.countryCode", is(countryCode)));
    }


    @Test
    void whenInvalidCityOrCountryCode_thenReturn404() throws Exception {
        String city = "Aveiro";
        String countryCode = "FR";

        //execute
        mvc.perform(
            get("/api/airquality/current")
            .param("city", city)
            .param("countryCode", countryCode)
        )
            .andExpect(status().isNotFound()
        );
    }


    /*
    * Get Air Quality Forecast tests
    */

    @Test
    void whenValidCityAndCountryCode_thenReturntAirQualityForecast() throws Exception {
        String city = "Aveiro";
        String countryCode = "PT";
        int totalDays = 2;
        
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
    }


    @Test
    void whenInvalidCityOrCountryCodeForecast_thenReturn404() throws Exception {
        String city = "Aveiro";
        String countryCode = "FR";
        int totalDays = 2;

        //execute
        mvc.perform(
            get("/api/airquality/forecast")
            .param("city", city)
            .param("countryCode", countryCode)
            .param("days", Integer.toString(totalDays))
        )
            .andExpect(status().isNotFound()
        );
    }
}
