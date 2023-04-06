package pt.ua.tqs.homework.IntegrationTests;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AirQualityRestControllerRestAssuredIT {

    @LocalServerPort
    private int localPortForTestServer;

    /*
     * Get Today's Air Quality tests
    */

    @Test
    void whenValidCityAndCountryCode_thenReturnCurrentAirQuality() throws Exception {
        String city = "Aveiro";
        String countryCode = "PT";

        String endpoint = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("127.0.0.1")
                .port(localPortForTestServer)
                .pathSegment("api", "airquality", "current")
                .build()
                .toUriString();

        RestAssured
        .given()
            .param("city", city)
            .param("countryCode", countryCode)
        .when()
            .get(endpoint)
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("city", Matchers.equalTo(city))
            .body("countryCode", Matchers.equalTo(countryCode))
            .body("coord", Matchers.aMapWithSize(2))
            .body("results", Matchers.aMapWithSize(1));  //because is only for current day === 1 key -> current date

    }


    @Test
    void whenInvalidCityOrCountryCode_thenReturn404() throws Exception {
        String city = "Aveiro";
        String countryCode = "FR";

        String endpoint = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("127.0.0.1")
                .port(localPortForTestServer)
                .pathSegment("api", "airquality", "current")
                .build()
                .toUriString();

        RestAssured
        .given()
            .param("city", city)
            .param("countryCode", countryCode)
        .when()
            .get(endpoint)
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());

    }


    /*
    * Get Air Quality Forecast tests
    */

    @Test
    void whenValidCityAndCountryCode_thenReturntAirQualityForecast() throws Exception {
        String city = "Aveiro";
        String countryCode = "PT";
        int totalDays = 3;

        String endpoint = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("127.0.0.1")
                .port(localPortForTestServer)
                .pathSegment("api", "airquality", "forecast")
                .build()
                .toUriString();

        RestAssured
        .given()
            .param("city", city)
            .param("countryCode", countryCode)
            .param("days", totalDays)
        .when()
            .get(endpoint)
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("city", Matchers.equalTo(city))
            .body("countryCode", Matchers.equalTo(countryCode))
            .body("coord", Matchers.aMapWithSize(2))
            .body("results", Matchers.aMapWithSize(totalDays));
    }


    @Test
    void whenInvalidCityOrCountryCodeForecast_thenReturn404() throws Exception {
        String city = "Aveiro";
        String countryCode = "FR";
        int totalDays = 3;

        String endpoint = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("127.0.0.1")
                .port(localPortForTestServer)
                .pathSegment("api", "airquality", "forecast")
                .build()
                .toUriString();

        RestAssured
        .given()
            .param("city", city)
            .param("countryCode", countryCode)
            .param("days", totalDays)
        .when()
            .get(endpoint)
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }
    
}
