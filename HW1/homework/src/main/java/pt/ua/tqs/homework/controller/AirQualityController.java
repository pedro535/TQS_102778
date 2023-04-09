package pt.ua.tqs.homework.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.ua.tqs.homework.model.AirQuality;
import pt.ua.tqs.homework.service.AirQualityService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/airquality")
public class AirQualityController {

    public static final Logger logger = LoggerFactory.getLogger(AirQualityController.class);

    private AirQualityService airQualityService;

    
    public AirQualityController(AirQualityService airQualityService) {
        this.airQualityService = airQualityService;
    }


    @GetMapping("/current")
    public ResponseEntity<AirQuality> getCurrentDayAirQuality(@RequestParam String city, @RequestParam String countryCode) throws IOException, URISyntaxException {

        logger.info(String.format("New request for today's air quality in %s, %s", city, countryCode));
        
        AirQuality results = airQualityService.getAirQuality(city, countryCode, 1);

        if (results == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(results, HttpStatus.OK);

    }


    @GetMapping("/forecast")
    public ResponseEntity<AirQuality> getAirQualityForecast(@RequestParam String city, @RequestParam String countryCode, @RequestParam int days) throws IOException, URISyntaxException {

        logger.info(String.format("New request for air quality forecast in %s, %s for %d days", city, countryCode, days));

        AirQuality results = airQualityService.getAirQuality(city, countryCode, days);

        if (results == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(results, HttpStatus.OK);

    }
    
}
