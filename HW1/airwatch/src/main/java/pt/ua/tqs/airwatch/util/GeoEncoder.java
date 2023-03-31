package pt.ua.tqs.airwatch.util;

import org.springframework.beans.factory.annotation.Value;

import pt.ua.tqs.airwatch.model.Location;

public class GeoEncoder {

    private IHttpClient httpClient;
    
    @Value( "${mapquest.geoCodingAPI.url}" )
    private String url;

    @Value( "${mapquest.apikey}" )
    private String apiKey;


    public GeoEncoder(IHttpClient httpClient) {
        this.httpClient = httpClient;
    }


    public Location getLocationDetails(String location, String countryCode) {
        //api request to get coordinates
        return null;
    }
    
}
