package pt.ua.tqs.airwatch.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {

    private Coordinates coord;
    private String locationName;
    private String countryCode;


    public Location(String locationName, String countryCode, Coordinates coords) {
        this.locationName = locationName;
        this.countryCode = countryCode;
        this.coord = coords;
    }
    
}
