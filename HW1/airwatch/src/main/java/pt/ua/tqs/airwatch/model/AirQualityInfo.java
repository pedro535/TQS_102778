package pt.ua.tqs.airwatch.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class AirQualityInfo {
    
    private String dateTime;
    private int airQualityIndex;
    private List<AirComponent> components;
    
}
