package pt.ua.tqs.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.ua.tqs.homework.model.CacheStats;
import pt.ua.tqs.homework.service.CacheStatsService;


@RestController
@RequestMapping("/api/cache")
public class CacheStatsController {

    private CacheStatsService cacheStatsService;


    public CacheStatsController(CacheStatsService cacheStatsService) {
        this.cacheStatsService = cacheStatsService;
    }


    @RequestMapping("/stats")
    public ResponseEntity<CacheStats> getCacheStats() {
        CacheStats stats = cacheStatsService.getCacheStats();
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

}
