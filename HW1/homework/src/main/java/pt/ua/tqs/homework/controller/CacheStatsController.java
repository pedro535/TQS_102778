package pt.ua.tqs.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.ua.tqs.homework.model.CacheStats;
import pt.ua.tqs.homework.service.CacheStatsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/cache")
public class CacheStatsController {

    public static final Logger logger = LoggerFactory.getLogger(CacheStatsController.class);

    private CacheStatsService cacheStatsService;


    public CacheStatsController(CacheStatsService cacheStatsService) {
        this.cacheStatsService = cacheStatsService;
    }


    @GetMapping("/stats")
    public ResponseEntity<CacheStats> getCacheStats() {
        logger.info("New request for cache stats");
        
        CacheStats stats = cacheStatsService.getCacheStats();
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

}
