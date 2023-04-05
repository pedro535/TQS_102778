package pt.ua.tqs.homework.UnitTests;

import org.junit.jupiter.api.BeforeEach;
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
    void whenCacheIsConstructed_thenIsEmpty() {
        assertThat(cache.getSize()).isZero();
        assertThat(cache.getHits()).isZero();
        assertThat(cache.getMisses()).isZero();
        assertThat(cache.getTotalRequests()).isZero();
    }


    @Test
    void whenElementsAreAdded_thenAreStored() {
        String keys[] = {"1", "2", "3"};
        String names[] = {"John Smith", "Mary Jane", "Peter Parker"};

        //execute
        for (int i = 0; i < keys.length; i++) {
            cache.put(keys[i], names[i]);
        }

        //assert
        for (int i = 0; i < keys.length; i++) {
            assertThat(cache.get(keys[i])).isNotNull();
            assertThat(cache.get(keys[i])).isEqualTo(names[i]);
        }
    }


    @Test
    void whenGetElementFromEmptyCache_thenReturnNull() {
        String key = "1";

        //execute
        String value = cache.get(key);

        //assert
        assertThat(value).isNull();
    }


    @Test
    void whenValueInCache_thenContainsReturnTrue() {
        String key = "1";
        String value = "John Smith";
        cache.put(key, value);

        //execute
        boolean valueInCache = cache.contains(key);

        //assert
        assertThat(valueInCache).isTrue();
    }


    @Test
    void whenValueNotInCache_thenContainsReturnFalse() {
        String key = "1";

        //execute
        boolean valueInCache = cache.contains(key);

        //assert
        assertThat(valueInCache).isFalse();
    }


    @Test
    void whenElementsAreAdded_thenSizeIncrease() {
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
    void whenGetElementsFromEmptyCache_thenMissesIncrease() {
        String keys[] = {"1", "2", "3"};

        //execute
        for (String key : keys) {
            cache.get(key);
        }

        //assert
        assertThat(cache.getMisses()).isEqualTo(keys.length);
    }


    @Test
    void whenGetElementsFromNonEmptyCache_thenHitsIncrease() {
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
    void whenGetElementsFromCache_thenRequestsIncrease() {
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
    void whenRemoveExistingElementFromCache_thenReturnElement() {
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
    void whenRemoveNonExistingElementFromCache_thenReturnNull() {
        String key = "1";

        //execute
        String removed = cache.remove(key);

        //assert
        assertThat(removed).isNull();
    }


    @Test
    void whenGetElementAfterTTL_thenReturnNull() {
        String key = "1";
        String value = "John Smith";

        //execute
        cache.put(key, value);

        try {
            Thread.sleep(cache.getTTL() + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //assert
        assertThat(cache.get(key)).isNull();
    }
    
}
