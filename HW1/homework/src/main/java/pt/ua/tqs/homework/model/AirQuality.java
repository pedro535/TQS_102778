package pt.ua.tqs.homework.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AirQuality {

    private String city;
    private String countryCode;
    private Map<String, Double> coord;
    private Map<String, List<AirQualityData>> results;

    
    public AirQuality(String city, String countryCode, Map<String, Double> coord) {
        this.city = city;
        this.countryCode = countryCode;
        this.coord = coord;
        this.results = new LinkedHashMap<>();
    }
    
}
