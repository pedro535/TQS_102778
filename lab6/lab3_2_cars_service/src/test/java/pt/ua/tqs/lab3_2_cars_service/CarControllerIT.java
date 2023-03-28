package pt.ua.tqs.lab3_2_cars_service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import pt.ua.tqs.lab3_2_cars_service.domain.Car;
import pt.ua.tqs.lab3_2_cars_service.repositories.CarRepository;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

//switch AutoConfigureTestDatabase with TestPropertySource to use a real database
//@AutoConfigureTestDatabase
@TestPropertySource(locations = "application-integrationtest.properties")
public class CarControllerIT {

    @LocalServerPort
    int randomPort;

    //REST client
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;


    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }


    @Test
    @DisplayName("Test POST request to create a car")
    public void whenPostCar_thenCreateCar() throws Exception {
        Car car = new Car("Ford", "Mustang");
        restTemplate.postForEntity("/api/cars", car, Car.class);

        List<Car> found = repository.findAll();
        assertThat(found).extracting(Car::getMaker).containsOnly(car.getMaker());
        assertThat(found).extracting(Car::getModel).containsOnly(car.getModel());
    }


    @Test
    @DisplayName("Test getAllCars method")
    public void givenManyCars_whenGetCars_thenReturnJsonArray() throws Exception {

        Car car1 = new Car("Ford", "Mustang");
        Car car2 = new Car("Nissan", "Skyline");
        Car car3 = new Car("Toyota", "Supra");
        repository.save(car1);
        repository.save(car2);
        repository.save(car3);

        //execute
        ResponseEntity<List<Car>> response = restTemplate.exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {});

        //assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(3).extracting(Car::getMaker).containsExactly(car1.getMaker(), car2.getMaker(), car3.getMaker());
        assertThat(response.getBody()).hasSize(3).extracting(Car::getModel).containsExactly(car1.getModel(), car2.getModel(), car3.getModel());
    }


    @Test
    @DisplayName("Test getCarById method when CarId exists")
    public void givenAnExistingCar_whenGetById_thenReturnCar() throws Exception {
        Car car = new Car("Ford", "Mustang");
        car.setCarId(1L);
        repository.save(car);

        ResponseEntity<Car> response = restTemplate.getForEntity("/api/cars/" + car.getCarId(), Car.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getMaker()).isEqualTo(car.getMaker());
        assertThat(response.getBody().getModel()).isEqualTo(car.getModel());
    }


    @Test
    @DisplayName("Test getCarById method when CarId does not exist")
    public void givenANonExistingCar_whenGetById_thenReturnNotFound() throws Exception {

        ResponseEntity<Car> response = restTemplate.getForEntity("/api/cars/1", Car.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    
}
