package pt.ua.tqs.lab3_2_cars_service.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pt.ua.tqs.lab3_2_cars_service.domain.*;
import pt.ua.tqs.lab3_2_cars_service.services.CarManagerService;

@RestController
@RequestMapping("/api")
public class CarController {

    private CarManagerService carManagerService;


    public CarController(CarManagerService carManagerService) {
        this.carManagerService = carManagerService;
    }


    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody CarDTO car) {
        HttpStatus status = HttpStatus.CREATED;
        Car saved = carManagerService.saveCar(car.toCarEntity());
        return new ResponseEntity<>(saved, status);
    }


    @GetMapping("/cars")
    public ResponseEntity<List<Car>>  getAllCars() {
        return new ResponseEntity<>(carManagerService.getAllCars(), HttpStatus.OK);
    }


    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Optional<Car> car = carManagerService.getCarDetails(id);

        if (car.isPresent()) {
            return new ResponseEntity<>(car.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
}
