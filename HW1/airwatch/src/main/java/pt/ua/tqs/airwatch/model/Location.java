package pt.ua.tqs.airwatch.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Location {

    private Coordinates coord;
    private String locationName;
    private String countryCode;
    
}
