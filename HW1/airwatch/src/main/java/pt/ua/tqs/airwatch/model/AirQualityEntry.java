package pt.ua.tqs.airwatch.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class AirQualityEntry {
    
    @JsonProperty("airClassification")
    private Map<String, Integer> main;

    private Map<String, Double> components;

    @JsonProperty("timestamp")
    private long dt;
    
}
