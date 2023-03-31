package pt.ua.tqs.airwatch.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CacheStatistics {

    private int hits;
    private int misses;
    private int requests;
    private int size;
    
}
