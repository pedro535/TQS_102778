package pt.ua.tqs.homework.ServiceLevelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ua.tqs.homework.cache.Cache;
import pt.ua.tqs.homework.model.CacheStats;
import pt.ua.tqs.homework.service.CacheStatsService;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CacheStatsServiceTest {

    @Mock
    private Cache<String> cache;

    @InjectMocks
    private CacheStatsService cacheStatsService;

    private int totalRequests, hits, misses, size;


    @BeforeEach
    public void setup() {
        size = 5;
        hits = 2;
        misses = 3;
        size = 1;
        when(cache.getTotalRequests()).thenReturn(totalRequests);
        when(cache.getHits()).thenReturn(hits);
        when(cache.getMisses()).thenReturn(misses);
        when(cache.getSize()).thenReturn(size);
    }


    @Test
    void whenGetCacheStats_thenReturnCacheStatsObj() {

        //execute
        CacheStats stats = cacheStatsService.getCacheStats();

        //assert
        assertThat(stats).isNotNull();
        assertThat(stats.getRequests()).isEqualTo(totalRequests);
        assertThat(stats.getHits()).isEqualTo(hits);
        assertThat(stats.getMisses()).isEqualTo(misses);
        assertThat(stats.getSize()).isEqualTo(size);

        //verify
        verify(cache, times(1)).getTotalRequests();
        verify(cache, times(1)).getHits();
        verify(cache, times(1)).getMisses();
        verify(cache, times(1)).getSize();
    }
    
}
