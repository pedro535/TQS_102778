package pt.ua.tqs.lab3_2_cars_service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.ua.tqs.lab3_2_cars_service.domain.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

    public Car findByCarId(Long id);
    
    public List<Car> findAll();

}
