package pt.ua.tqs.homework.model;

import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class AirQualityDataResponse {
    
    private Map<String, Integer> main;
    private Map<String, Double> components;
    private long dt;
    
}
