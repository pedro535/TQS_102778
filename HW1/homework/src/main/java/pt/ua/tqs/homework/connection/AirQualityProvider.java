package pt.ua.tqs.homework.connection;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.http.client.utils.URIBuilder;
import com.google.gson.Gson;
import pt.ua.tqs.homework.model.AirQuality;
import pt.ua.tqs.homework.model.AirQualityResponse;
import pt.ua.tqs.homework.model.Coordinates;
import pt.ua.tqs.homework.utils.AirQualityConverter;


@Component
public class AirQualityProvider {

    private IHttpClient httpClient;

    @Value( "${openweather.airQualityAPI.url}" )
    private String airQualityUrl;

    @Value( "${openweather.apikey}" )
    private String apiKey;



    public AirQualityProvider(IHttpClient httpClient) {
        this.httpClient = httpClient;
    }


    public AirQuality getAirQualityInfo(String city, String countryCode, Coordinates coords, int totalDays) throws URISyntaxException, IOException {

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