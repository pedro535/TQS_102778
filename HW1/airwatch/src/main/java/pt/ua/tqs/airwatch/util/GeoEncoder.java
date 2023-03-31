package pt.ua.tqs.airwatch.util;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;

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


    public Location getLocationDetails(String location, String countryCode) throws IOException, URISyntaxException, ParseException {
        
        //build uri
        URIBuilder uriBuilder = new URIBuilder(baseUrl);
        uriBuilder.addParameter("key", apiKey);
        uriBuilder.addParameter("location", location + "," + countryCode);

        //http get using httpClient
        String apiResponse = httpClient.httpGet(uriBuilder.build().toString());

        //get parts from response till reaching the coordinates
        JSONObject obj = (JSONObject) new JSONParser().parse(apiResponse);
        obj = (JSONObject) ((JSONArray) obj.get("results")).get(0);

        if (((JSONArray) obj.get("locations")).isEmpty()) {
            return null;
        }

        obj = (JSONObject) ((JSONArray) obj.get("locations")).get(0);
        obj = (JSONObject) obj.get("latLng");

        //create coordinates object
        Double lat = (Double) obj.get("lat");
        Double lon = (Double) obj.get("lng");
        Coordinates coords = new Coordinates(lat, lon);
        
        return new Location(location, countryCode, coords);

    }
    
}
