package pt.ua.tqs.airwatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AirwatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirwatchApplication.class, args);
	}

}
