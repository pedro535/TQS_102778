package pt.ua.tqs.homework.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.ua.tqs.homework.model.AirQuality;
import pt.ua.tqs.homework.service.AirQualityService;


@RestController
@RequestMapping("/api/airquality")
public class AirQualityController {

    private AirQualityService airQualityService;

    
    public AirQualityController(AirQualityService airQualityService) {
        this.airQualityService = airQualityService;
    }


    @RequestMapping("/current")
    public ResponseEntity<AirQuality> getCurrentDayAirQuality(@RequestParam String city, @RequestParam String countryCode) throws IOException, URISyntaxException {
        
        AirQuality results = airQualityService.getAirQuality(city, countryCode, 1);

        if (results == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(results, HttpStatus.OK);

    }


    @RequestMapping("/forecast")
    public ResponseEntity<AirQuality> getAirQualityForecast(@RequestParam String city, @RequestParam String countryCode, @RequestParam int days) throws IOException, URISyntaxException {

        AirQuality results = airQualityService.getAirQuality(city, countryCode, days);

        if (results == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(results, HttpStatus.OK);

    }
    
}
