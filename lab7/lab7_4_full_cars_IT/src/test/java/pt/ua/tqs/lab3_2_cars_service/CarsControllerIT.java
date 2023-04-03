package pt.ua.tqs.lab3_2_cars_service;

import io.restassured.RestAssured;
import pt.ua.tqs.lab3_2_cars_service.domain.Car;
import pt.ua.tqs.lab3_2_cars_service.repositories.CarRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.util.UriComponentsBuilder;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=create")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarsControllerIT {
    
    @LocalServerPort
    int localPortForTestServer;

    @Autowired
    private CarRepository repository;

    @Container
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres")
        .withUsername("username")
        .withPassword("password")
        .withDatabaseName("db")
        .withInitScript("db/migration/V001__INIT.sql");


    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    Car car1, car2;


    @BeforeEach
    public void setUpTestCars() throws Exception{
        repository.deleteAll(); //testcontainer content does not reset on @BeforeEach!!!
        car1 = repository.save(new Car("kia", "stinger"));
        car2 = repository.save(new Car("tesla", "model x"));
    }


    @Test
    @Order(1)
    void whenGetCarById_thenApiReturnsOneCar() {
        String endpoint = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("127.0.0.1")
            .port(localPortForTestServer)
            .pathSegment("api", "cars", String.valueOf(car1.getCarId()))
            .build()
            .toUriString();
            
            
        RestAssured
        .given()
        .when()
            .get(endpoint)
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("maker", Matchers.equalTo(car1.getMaker()))
            .body("model", Matchers.equalTo(car1.getModel()));
        }
        
        
    @Test
    @Order(2)
    void givenManyCars_whenGetCars_thenReturnJsonArray() throws Exception {
        
        String endpoint = UriComponentsBuilder.newInstance()
        .scheme("http")
        .host("127.0.0.1")
        .port(localPortForTestServer)
        .pathSegment("api", "cars")
        .build()
        .toUriString();
        
        RestAssured
        .given()
        .when()
            .get(endpoint)
        .then()
            .statusCode(200)
            .body("$", Matchers.hasSize(2))
            .body("[0].maker", Matchers.equalTo(car1.getMaker()))
            .body("[0].model", Matchers.equalTo(car1.getModel()))
            .body("[1].maker", Matchers.equalTo(car2.getMaker()))
            .body("[1].model", Matchers.equalTo(car2.getModel()));
    }
        
        
    @Test
    @Order(3)
    void whenGetNonExistingCar_thenNotFound() throws Exception {
        String carId = "100";

        String endpoint = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("127.0.0.1")
            .port(localPortForTestServer)
            .pathSegment("api", "cars", carId)
            .build()
            .toUriString();

        RestAssured
        .given()
        .when()
            .get(endpoint)
        .then()
            .statusCode(404);
    }


    @Test
    @Order(4)
    void whenPostCar_thenCreateCar() throws Exception {
        Car car3 = new Car("ford", "mustang");
        repository.save(car3);

        String endpoint = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("127.0.0.1")
            .port(localPortForTestServer)
            .pathSegment("api", "cars")
            .build()
            .toUriString();

        RestAssured
        .given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(JsonUtils.toJson(car3))
        .when()
            .post(endpoint)
        .then()
            .statusCode(201);
    }

}
