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
        return carRepository.save(car);
    }


    public List<Car> getAllCars() {
        return carRepository.findAll();
    }


    public Optional<Car> getCarDetails(Long id) {
        Car carDetails = carRepository.findByCarId(id);

        if (carDetails != null) {
            return Optional.of(carDetails);
        }
        return Optional.empty();
    }
    
}
