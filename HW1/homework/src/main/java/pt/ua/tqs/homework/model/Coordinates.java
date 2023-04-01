package pt.ua.tqs.homework.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Coordinates {

    private Double lat;
    private Double lon;

    public Coordinates(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }
    
}
