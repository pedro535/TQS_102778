package pt.ua.tqs.homework.UnitTests;

import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pt.ua.tqs.homework.model.AirQuality;
import pt.ua.tqs.homework.model.AirQualityData;
import pt.ua.tqs.homework.model.AirQualityDataResponse;
import pt.ua.tqs.homework.model.AirQualityResponse;
import pt.ua.tqs.homework.utils.AirQualityConverter;


class AirQualityConverterTest {


    @Test
    void whenValidAirQualityResponse_thenReturnAirQualityObject() {
        String city = "Aveiro";
        String countryCode = "PT";

        AirQualityResponse openWeatherObj = new AirQualityResponse();
        openWeatherObj.setCoord(Map.of("lat", 38.722252, "lon", -9.139337));
        openWeatherObj.setList(List.of(
            new AirQualityDataResponse(Map.of("aqi", 2), Map.of("pm10", 1.0), 1680386400L))
        );

        //execute
        AirQuality airQuality = AirQualityConverter.convertToAirQuality(openWeatherObj, city, countryCode);

        //assert
        assertThat(airQuality.getCity()).isEqualTo(city);
        assertThat(airQuality.getCountryCode()).isEqualTo(countryCode);
        assertThat(airQuality.getCoord()).isEqualTo(openWeatherObj.getCoord());
        assertThat(airQuality.getResults()).hasSize(1);
        assertThat(airQuality.getResults()).containsKey("2023-04-01");

        AirQualityData airQualityData = airQuality.getResults().get("2023-04-01").get(0);
        assertThat(airQualityData.getClassification()).isEqualTo(2);
        assertThat(airQualityData.getComponents()).containsKey("pm10");
        assertThat(airQualityData.getComponents().get("pm10")).isOne();
    }


    @Test
    void whenAirQualityResponseHasNoResults_thenReturnEmptyResults() {
        String city = "Aveiro";
        String countryCode = "PT";

        AirQualityResponse openWeatherObj = new AirQualityResponse();
        openWeatherObj.setCoord(Map.of("lat", 38.722252, "lon", -9.139337));
        openWeatherObj.setList(List.of());

        //execute
        AirQuality airQuality = AirQualityConverter.convertToAirQuality(openWeatherObj, city, countryCode);

        //assert
        assertThat(airQuality.getCity()).isEqualTo(city);
        assertThat(airQuality.getCountryCode()).isEqualTo(countryCode);
        assertThat(airQuality.getCoord()).isEqualTo(openWeatherObj.getCoord());
        assertThat(airQuality.getResults()).isEmpty();
    }
    
}
