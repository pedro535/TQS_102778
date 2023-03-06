package pt.ua.tqs.lab3_2_cars_service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import pt.ua.tqs.lab3_2_cars_service.repositories.CarRepository;
import pt.ua.tqs.lab3_2_cars_service.domain.Car;


@Service
public class CarManagerService {

    private CarRepository carRepository;


    public CarManagerService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    public Car saveCar(Car car) {
        return null;
    }


    public List<Car> getAllCars() {
        return null;
    }


    public Optional<Car> getCarDetails(Long id) {
        return null;
    }
    
}
