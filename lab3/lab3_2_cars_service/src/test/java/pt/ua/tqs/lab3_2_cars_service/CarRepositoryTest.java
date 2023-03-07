package pt.ua.tqs.lab3_2_cars_service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import pt.ua.tqs.lab3_2_cars_service.domain.Car;
import pt.ua.tqs.lab3_2_cars_service.repositories.CarRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository repository;


    @Test
    @DisplayName("Test findByCarId repository method with a valid carId")
    public void givenAnExitingCar_thenReturnCar() {

        Car car1 = new Car("Ford", "Mustang");
        Long carId = (Long) entityManager.persistAndGetId(car1);

        //execute
        Car carFromDb = repository.findByCarId(carId);

        //assert
        assertThat(carFromDb).isNotNull();
        assertThat(carFromDb.getMaker()).isEqualTo("Ford");
        assertThat(carFromDb.getModel()).isEqualTo("Mustang");
    }


    @Test
    @DisplayName("Test findByCarId repository method with an invalid carId")
    public void givenAnInvaliCarId_thenReturnNull() {

        //execute
        Car carFromDb = repository.findByCarId(1L);

        //assert
        assertThat(carFromDb).isNull();
    }


    @Test
    @DisplayName("Test findAll repository method")
    public void given3Cars_thenReturnListOfCars() {

        Car car1 = new Car("Ford", "Mustang");
        Car car2 = new Car("Nissan", "Skyline");
        Car car3 = new Car("Toyota", "Supra");

        entityManager.persistAndFlush(car1);
        entityManager.persistAndFlush(car2);
        entityManager.persistAndFlush(car3);

        //execute
        List<Car> allCarsFromDb = repository.findAll();

        //assert
        assertThat(allCarsFromDb).hasSize(3).extracting(Car::getMaker).containsExactly(car1.getMaker(), car2.getMaker(), car3.getMaker());
        assertThat(allCarsFromDb).hasSize(3).extracting(Car::getModel).containsExactly(car1.getModel(), car2.getModel(), car3.getModel());
    }

    
}
