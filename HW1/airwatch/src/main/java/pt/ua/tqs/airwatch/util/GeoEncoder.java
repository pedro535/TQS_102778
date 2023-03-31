package pt.ua.tqs.airwatch.util;

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


@Setter
@Getter
public class GeoEncoder {

    private IHttpClient httpClient;
    
    @Value( "${mapquest.geoCodingAPI.url}" )
    private String baseUrl;

    @Value( "${mapquest.apikey}" )
    private String apiKey;


    public GeoEncoder(IHttpClient httpClient) {
        this.httpClient = httpClient;
    }


    public Location getLocationDetails(String location, String countryCode) throws IOException, URISyntaxException {
        
        //build uri
        URIBuilder uriBuilder = new URIBuilder(baseUrl);
        uriBuilder.addParameter("key", apiKey);
        uriBuilder.addParameter("location", location + "," + countryCode);

        //http get using httpClient
        String apiResponse = httpClient.httpGet(uriBuilder.build().toString());

        //get parts from response till reaching the coordinates
        JsonElement element = JsonParser.parseString(apiResponse);
        JsonElement obj = element.getAsJsonObject().get("results");

        if (obj.getAsJsonArray().size() == 0) {
            return null;
        }

        obj = obj.getAsJsonArray().get(0);
        obj = obj.getAsJsonObject().get("locations");

        if (obj.getAsJsonArray().size() == 0) {
            return null;
        }

        obj = obj.getAsJsonArray().get(0);
        obj = obj.getAsJsonObject().get("latLng");

        //create coordinates object
        Double lat = obj.getAsJsonObject().get("lat").getAsDouble();
        Double lon = obj.getAsJsonObject().get("lng").getAsDouble();
        Coordinates coords = new Coordinates(lat, lon);

        return new Location(location, countryCode, coords);
    }
    
}
