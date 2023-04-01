package pt.ua.tqs.airwatch.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import lombok.Getter;
import lombok.Setter;
import pt.ua.tqs.airwatch.model.Coordinates;
import pt.ua.tqs.airwatch.model.Location;
import pt.ua.tqs.airwatch.util.IHttpClient;


@Setter
@Getter
public class GeoEncoder {

    private IHttpClient httpClient;

    @Value( "${openweather.reverseGeocoding.url}" )
    private String baseUrl;

    @Value( "${openweather.apikey}" )
    private String apiKey;


    public GeoEncoder(IHttpClient httpClient) {
        this.httpClient = httpClient;
    }


    public Location getLocationDetails(String city, String countryCode) throws IOException, URISyntaxException {
        
        //build uri
        URIBuilder uriBuilder = new URIBuilder(baseUrl)
            .addParameter("appid", apiKey)
            .addParameter("q", city + "," + countryCode);

        //http get using httpClient
        String apiResponse = httpClient.httpGet(uriBuilder.build().toString());

        //get parts from response till reaching the coordinates
        JsonElement results = JsonParser.parseString(apiResponse);

        if (results.getAsJsonArray().size() == 0) {
            return null;
        }

        JsonElement entry = results.getAsJsonArray().get(0);
        Double lat = entry.getAsJsonObject().get("lat").getAsDouble();
        Double lon = entry.getAsJsonObject().get("lon").getAsDouble();
        Coordinates coords = new Coordinates(lat, lon);

        return new Location(city, countryCode, coords);
    }
    
}
