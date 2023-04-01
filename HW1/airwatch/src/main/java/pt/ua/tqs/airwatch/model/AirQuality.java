package pt.ua.tqs.airwatch.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class AirQuality {

    private String location;
    private String countryCode;
    private Map<String, Double> coord;

    @JsonProperty("results")
    private List<AirQualityEntry> list;
    
}
