package pt.ua.tqs.homework.connection;

import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.Setter;
import pt.ua.tqs.homework.model.Coordinates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Setter
@Component
public class Geocoding {

    public static final Logger logger = LoggerFactory.getLogger(Geocoding.class);

    private IHttpClient httpClient;

    @Value( "${openweather.reverseGeocoding.url}" )
    private String reverseGeocodingUrl;

    @Value( "${openweather.apikey}" )
    private String apiKey;



    public Geocoding(IHttpClient httpClient) {
        this.httpClient = httpClient;
    }


    public Coordinates getCoords(String city, String countryCode) throws IOException, URISyntaxException {

        logger.info("Getting coordinates from OpenWeather Geocoding API");
        
        //build uri
        String uri = new URIBuilder(reverseGeocodingUrl)
            .addParameter("appid", apiKey)
            .addParameter("q", city + "," + countryCode)
            .build().toString();

        //http get using httpClient
        String apiResponse = httpClient.httpGet(uri);

        //get parts from response till reaching the coordinates
        JsonElement results = JsonParser.parseString(apiResponse);

        if (results.getAsJsonArray().size() == 0) {
            return null;
        }

        JsonElement entry = results.getAsJsonArray().get(0);
        Double lat = entry.getAsJsonObject().get("lat").getAsDouble();
        Double lon = entry.getAsJsonObject().get("lon").getAsDouble();
        
        return new Coordinates(lat, lon);
    }

}
