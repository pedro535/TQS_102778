package pt.ua.tqs.lab3_2_cars_service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import pt.ua.tqs.lab3_2_cars_service.controllers.CarController;
import pt.ua.tqs.lab3_2_cars_service.domain.Car;
import pt.ua.tqs.lab3_2_cars_service.services.CarManagerService;


@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mvc;   //entry point to use web framework

    @MockBean
    private CarManagerService service;


    @Test
    @DisplayName("Test POST request to create a car")
    public void whenPostCar_thenCreateCar() throws Exception {

        Car car = new Car("Ford", "Mustang");

        //load expectations
        when(service.saveCar(any())).thenReturn(car);

        //HTTP POST and make assertions
        mvc.perform(
            post("/api/cars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonUtils.toJson(car))
        )
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.maker", is("Ford")))
            .andExpect(jsonPath("$.model", is("Mustang")));

        //verify if the save() method was invoked
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
        mvc.perform(
            get("/api/cars").contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].maker", is(car1.getMaker())))
            .andExpect(jsonPath("$[0].model", is(car1.getModel())))
            .andExpect(jsonPath("$[1].maker", is(car2.getMaker())))
            .andExpect(jsonPath("$[1].model", is(car2.getModel())))
            .andExpect(jsonPath("$[2].maker", is(car3.getMaker())))
            .andExpect(jsonPath("$[2].model", is(car3.getModel())));

        //verify if the getAllCars() from service was invoked
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
        mvc.perform(
            get("/api/cars/" + car.getCarId()).contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.maker", is("Ford")))
        .andExpect(jsonPath("$.model", is("Mustang")));

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
        mvc.perform(
            get("/api/cars/2").contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isNotFound());

        //verify if the getCarDetails() was invoked
        verify(service, times(1)).getCarDetails(2L);
    }

}
