package pt.ua.tqs.homework.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.ua.tqs.homework.model.AirQuality;
import pt.ua.tqs.homework.model.AirQualityData;
import pt.ua.tqs.homework.model.AirQualityDataResponse;
import pt.ua.tqs.homework.model.AirQualityResponse;


public class AirQualityConverter {

    private AirQualityConverter() {
        throw new IllegalStateException("Utility class");
    }


    /*
     * Convert OpenWeather object to AirQuality object
     */

    public static AirQuality convertToAirQuality(AirQualityResponse openWeatherObj, String city, String countryCode) {

        AirQuality airQuality = new AirQuality(city, countryCode, openWeatherObj.getCoord());
        
        if (openWeatherObj.getList().isEmpty()) {
            return airQuality;
        }
        
        //extract air quality values by day
        Map<String, List<AirQualityData>> values = new HashMap<>();
        
        for (AirQualityDataResponse v : openWeatherObj.getList()) {
            String vDateTime = toDate(v.getDt());

            //convert OpenWeather air data to AirQualityData format
            AirQualityData newData = new AirQualityData(v.getMain().get("aqi"), v.getComponents(), vDateTime);
            
            //extract yyyy-MM-dd from timestamp
            String date = vDateTime.split(" ")[0];
            
            if (!values.containsKey(date)) {
                values.put(date, new ArrayList<>());
            }

            values.get(date).add(newData);
        }
        
        airQuality.setResults(values);
        return airQuality;
    }
    


    /*
     * Convert UNIX timestamp to DateTime
     */

    public static String toDate(long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
    
}
