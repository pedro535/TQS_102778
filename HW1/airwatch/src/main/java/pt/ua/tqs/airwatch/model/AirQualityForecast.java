package pt.ua.tqs.airwatch.model;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class AirQualityForecast {

    private Location location;
    private Map<String, List<AirQualityInfo>> airQualityInfo;
    
}
