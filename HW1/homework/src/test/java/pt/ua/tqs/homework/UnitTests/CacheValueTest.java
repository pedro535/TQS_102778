package pt.ua.tqs.homework.UnitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import pt.ua.tqs.homework.cache.CacheValue;


public class CacheValueTest {

    private CacheValue<String> cacheValue;

    @BeforeEach
    public void setup() {
        cacheValue = new CacheValue<String>("John Smith", 1000L);
    }


    @Test
    @DisplayName("When a cacheValue is created, after the ttl, isExpired() return true")
    public void whenTtlElapsed_thenIsExpiredIsTrue() {

        //execute
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //assert
        assertThat(cacheValue.isExpired()).isTrue();
    }

    
}
