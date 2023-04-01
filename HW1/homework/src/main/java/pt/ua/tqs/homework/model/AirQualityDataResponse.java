package pt.ua.tqs.homework.model;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AirQualityDataResponse {
    
    private Map<String, Integer> main;
    private Map<String, Double> components;
    private long dt;

    public AirQualityDataResponse(Map<String, Integer> main, Map<String, Double> components, long dt) {
        this.main = main;
        this.components = components;
        this.dt = dt;
    }
    
}
