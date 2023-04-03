package pt.ua.tqs.homework.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CacheStats {

    private int requests;
    private int hits;
    private int misses;
    private int size;

    public CacheStats(int hits, int misses, int requests, int size) {
        this.requests = requests;
        this.hits = hits;
        this.misses = misses;
        this.size = size;
    }
}
