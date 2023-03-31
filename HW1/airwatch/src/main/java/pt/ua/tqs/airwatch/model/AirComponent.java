package pt.ua.tqs.airwatch.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AirComponent {

    private String name;
    private String value;
    private String unit = "µg/m³";
    
}
