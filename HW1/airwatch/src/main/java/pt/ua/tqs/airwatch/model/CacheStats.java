package pt.ua.tqs.airwatch.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CacheStats {

    private int hits;
    private int misses;
    private int requests;
    private int size;

    public CacheStats(int hits, int misses, int requests, int size) {
        this.hits = hits;
        this.misses = misses;
        this.requests = requests;
        this.size = size;
    }
}
