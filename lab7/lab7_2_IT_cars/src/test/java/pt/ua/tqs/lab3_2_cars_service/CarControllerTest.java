package pt.ua.tqs.lab3_2_cars_service;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import pt.ua.tqs.lab3_2_cars_service.controllers.CarController;
import pt.ua.tqs.lab3_2_cars_service.domain.Car;
import pt.ua.tqs.lab3_2_cars_service.services.CarManagerService;


@WebMvcTest(CarController.class)
public class CarControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;


    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.mockMvc(mvc);
    }


    @Test
    @DisplayName("Test POST request to create a car")
    public void whenPostCar_thenCreateCar() throws Exception {

        Car car = new Car("Ford", "Mustang");

        //load expectations
        when(service.saveCar(any())).thenReturn(car);

        //HTTP POST and make assertions
        RestAssuredMockMvc
            .given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(JsonUtils.toJson(car))
            .when()
                .post("/api/cars")
            .then()
                .statusCode(201)
                .body("maker", Matchers.equalTo(car.getMaker()))
                .body("model", Matchers.equalTo(car.getModel()));

        //verify if the save method was invoked
        verify(service, times(1)).saveCar(any());
    }


    @Test
    @DisplayName("Test getAllCars method")
    public void givenManyCars_whenGetCars_thenReturnJsonArray() throws Exception {

        Car car1 = new Car("Ford", "Mustang");
        Car car2 = new Car("Nissan", "Skyline");
        Car car3 = new Car("Toyota", "Supra");

        //load expectations
        when(service.getAllCars()).thenReturn(Arrays.asList(car1, car2, car3));

        //HTTP GET and make assertions
        RestAssuredMockMvc
            .given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
                .get("/api/cars")
            .then()
                .statusCode(200)
                .body("$", hasSize(3))
                .body("[0].maker", Matchers.equalTo(car1.getMaker()))
                .body("[0].model", Matchers.equalTo(car1.getModel()))
                .body("[1].maker", Matchers.equalTo(car2.getMaker()))
                .body("[1].model", Matchers.equalTo(car2.getModel()))
                .body("[2].maker", Matchers.equalTo(car3.getMaker()))
                .body("[2].model", Matchers.equalTo(car3.getModel()));

        //verify if the getAllCars method from service was invoked
        verify(service, times(1)).getAllCars();
    }


    @Test
    @DisplayName("Test getCarById method when CarId exists")
    public void givenAnExistingCar_whenGetById_thenReturnCar() throws Exception {

        Car car = new Car("Ford", "Mustang");
        car.setCarId(1L);

        //load expectation
        when(service.getCarDetails(anyLong())).thenReturn(Optional.of(car));

        //HTTP GET and make assertions
        RestAssuredMockMvc
            .given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
                .get("/api/cars/" + car.getCarId())
            .then()
                .statusCode(200)
                .body("maker", Matchers.equalTo(car.getMaker()))
                .body("model", Matchers.equalTo(car.getModel()));

        //verify if the getCarDetails() was invoked
        verify(service, times(1)).getCarDetails(car.getCarId());
    }


    @Test
    @DisplayName("Test getCarById method when CarId does not exist")
    public void givenANonExistingCar_whenGetById_thenReturnNotFound() throws Exception {

        Car car = new Car("Ford", "Mustang");
        car.setCarId(1L);

        //load expectation
        when(service.getCarDetails(anyLong())).thenReturn(Optional.empty());

        //HTTP GET and make assertions
        RestAssuredMockMvc
            .given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
                .get("/api/cars/" + car.getCarId())
            .then()
                .statusCode(404);

        //verify if the getCarDetails() was invoked
        verify(service, times(1)).getCarDetails(1L);
    }

}
