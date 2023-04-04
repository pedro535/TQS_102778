package pt.ua.tqs.homework.BoundaryLevelTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pt.ua.tqs.homework.controller.AirQualityController;
import pt.ua.tqs.homework.controller.CacheStatsController;
import pt.ua.tqs.homework.model.AirQuality;
import pt.ua.tqs.homework.model.CacheStats;
import pt.ua.tqs.homework.service.AirQualityService;
import pt.ua.tqs.homework.service.CacheStatsService;

import java.util.HashMap;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;


@WebMvcTest(CacheStatsController.class)
public class CacheStatsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CacheStatsService cacheStatsService;

    
    @Test
    void whenGetCacheStats_thenReturnCacheStatsInfo() throws Exception {
        int requests = 0;
        int hits = 0;
        int misses = 0;
        int size = 0;

        //stubbing
        CacheStats stats = new CacheStats(0, 0, 0, 0);
        when(cacheStatsService.getCacheStats()).thenReturn(stats);

        //execute
        mvc.perform(
            get("/api/cache/stats")
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.requests", is(requests)))
            .andExpect(jsonPath("$.hits", is(hits)))
            .andExpect(jsonPath("$.misses", is(misses)))
            .andExpect(jsonPath("$.size", is(size)));

        //verify
        verify(cacheStatsService, times(1)).getCacheStats();
    }
    
}
