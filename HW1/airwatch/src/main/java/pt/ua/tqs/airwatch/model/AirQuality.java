package pt.ua.tqs.airwatch.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AirQuality {

    private Location location;
    private AirQualityInfo airQualityInfo;
    
}
