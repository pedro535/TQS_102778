package pt.ua.tqs.homework.model;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class AirQualityResponse {

    private Map<String, Double> coord;
    private List<AirQualityDataResponse> list;
    
}
