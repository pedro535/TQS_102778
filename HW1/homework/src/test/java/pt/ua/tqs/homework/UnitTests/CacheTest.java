package pt.ua.tqs.homework.UnitTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import pt.ua.tqs.homework.cache.Cache;

public class CacheTest {

    private Cache<String> cache;

    @BeforeEach
    public void setUp() {
        cache = new Cache<>();
    }


    @Test
    @DisplayName("When cache is constructed, it should be empty")
    public void whenCacheIsConstructed_thenIsEmpty() {
        assertThat(cache.getSize()).isZero();
        assertThat(cache.getHits()).isZero();
        assertThat(cache.getMisses()).isZero();
        assertThat(cache.getTotalRequests()).isZero();
    }


    @Test
    @DisplayName("When n elements are added to the cache, they are stored")
    public void whenElementsAreAdded_thenAreStored() {
        String keys[] = {"1", "2", "3"};
        String names[] = {"John Smith", "Mary Jane", "Peter Parker"};

        //execute
        for (int i = 0; i < keys.length; i++) {
            cache.put(keys[i], names[i]);
        }

        //assert
        for (int i = 0; i < keys.length; i++) {
            assertThat(cache.get(keys[i])).isEqualTo(names[i]);
        }
    }


    @Test
    @DisplayName("When get an element from empty cache, the result should be null")
    public void whenGetElementFromEmptyCache_thenReturnNull() {
        String key = "1";

        //execute
        String value = cache.get(key);

        //assert
        assertThat(value).isNull();
    }


    @Test
    @DisplayName("When n elements are added to the cache, the size increase")
    public void whenElementsAreAdded_thenSizeIncrease() {
        String keys[] = {"1", "2", "3"};
        String names[] = {"John Smith", "Mary Jane", "Peter Parker"};

        //execute
        for (int i = 0; i < keys.length; i++) {
            cache.put(keys[i], names[i]);
        }

        //assert
        assertThat(cache.getSize()).isEqualTo(keys.length);
    }


    @Test
    @DisplayName("When get n elements from empty cache, the misses increase")
    public void whenGetElementsFromEmptyCache_thenMissesIncrease() {
        String keys[] = {"1", "2", "3"};

        //execute
        for (String key : keys) {
            cache.get(key);
        }

        //assert
        assertThat(cache.getMisses()).isEqualTo(keys.length);
    }


    @Test
    @DisplayName("When get n elements from non empty cache, the hits increase")
    public void whenGetElementsFromNonEmptyCache_thenHitsIncrease() {
        String keys[] = {"1", "2", "3"};
        String names[] = {"John Smith", "Mary Jane", "Peter Parker"};

        for (int i = 0; i < keys.length; i++) {
            cache.put(keys[i], names[i]);
        }

        //execute
        for (String key : keys) {
            cache.get(key);
        }

        //assert
        assertThat(cache.getHits()).isEqualTo(keys.length);
    }


    @Test
    @DisplayName("When get n elements from the cache, the total requests increase")
    public void whenGetElementsFromCache_thenRequestsIncrease() {
        String keys[] = {"1", "2", "3"};
        String nonExistingKeys[] = {"4", "5"};
        String names[] = {"John Smith", "Mary Jane", "Peter Parker"};

        for (int i = 0; i < keys.length; i++) {
            cache.put(keys[i], names[i]);
        }

        //execute
        for (String key : keys) {
            cache.get(key);
        }

        for (String key : nonExistingKeys) {
            cache.get(key);
        }

        //assert
        assertThat(cache.getTotalRequests()).isEqualTo(keys.length + nonExistingKeys.length);
    }


    @Test
    @DisplayName("When remove existing element from cache, return the element")
    public void whenRemoveExistingElementFromCache_thenReturnElement() {
        String keys[] = {"1", "2", "3"};
        String names[] = {"John Smith", "Mary Jane", "Peter Parker"};

        for (int i = 0; i < keys.length; i++) {
            cache.put(keys[i], names[i]);
        }

        //execute
        List<String> removed = new ArrayList<>();

        for (String k : keys) {
            removed.add(cache.remove(k));
        }

        //assert
        assertThat(removed).containsExactly(names);
    }


    @Test
    @DisplayName("When get value after TTL, should return null")
    public void whenGetElementAfterTTL_thenReturnNull() {
        String key = "1";
        String value = "John Smith";

        //execute
        cache.put(key, value);

        try {
            Thread.sleep(cache.getTTL() + 2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //assert
        assertThat(cache.get(key)).isNull();
    }
    
}
