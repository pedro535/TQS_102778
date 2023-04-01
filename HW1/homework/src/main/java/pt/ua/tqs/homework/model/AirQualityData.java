package pt.ua.tqs.homework.model;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AirQualityData {
    
    private String dateTime;
    private int classification;
    private Map<String, Double> components;


    public AirQualityData(int classification, Map<String, Double> components, String dateTime) {
        this.classification = classification;
        this.components = components;
        this.dateTime = dateTime;
    }
    
}
