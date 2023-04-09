package pt.ua.tqs.homework.connection;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.http.client.utils.URIBuilder;
import com.google.gson.Gson;

import lombok.Setter;
import pt.ua.tqs.homework.model.AirQuality;
import pt.ua.tqs.homework.model.AirQualityResponse;
import pt.ua.tqs.homework.model.Coordinates;
import pt.ua.tqs.homework.utils.AirQualityConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Setter
@Component
public class AirQualityProvider {

    public static final Logger logger = LoggerFactory.getLogger(AirQualityProvider.class);

    private IHttpClient httpClient;

    @Value( "${openweather.airQualityAPI.url}" )
    private String airQualityUrl;

    @Value( "${openweather.apikey}" )
    private String apiKey;



    public AirQualityProvider(IHttpClient httpClient) {
        this.httpClient = httpClient;
    }


    public AirQuality getAirQualityInfo(String city, String countryCode, Coordinates coords, int totalDays) throws URISyntaxException, IOException {

        logger.info(String.format("Getting air quality info for %s, %s", city, countryCode));

        if (totalDays > 4) {
            return null;
        }
        
        //build uri
        String uri = new URIBuilder(airQualityUrl)
            .addParameter("lat", Double.toString(coords.getLat()))
            .addParameter("lon", Double.toString(coords.getLon()))
            .addParameter("appid", apiKey)
            .build().toString();

        //get response
        logger.info(String.format("Sending request to %s", uri));
        String response = httpClient.httpGet(uri);

        //json to object
        Gson gson = new Gson();
        AirQualityResponse results = gson.fromJson(response, AirQualityResponse.class);
                
        //convert OpenWeather obj to AirQualityResponse
        AirQuality airQuality = AirQualityConverter.convertToAirQuality(results, city, countryCode);
        filterByTotalDays(airQuality, totalDays);

        return airQuality;
    }


    public void filterByTotalDays(AirQuality airQuality, int totalDays) {

        List<String> allDays = new ArrayList<>(airQuality.getResults().keySet());

        for (String key : allDays.subList(totalDays, allDays.size())) {
            airQuality.getResults().remove(key);
        }
    }


}
