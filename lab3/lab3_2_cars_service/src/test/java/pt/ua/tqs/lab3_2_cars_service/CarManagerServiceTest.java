package pt.ua.tqs.lab3_2_cars_service;

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pt.ua.tqs.lab3_2_cars_service.domain.Car;
import pt.ua.tqs.lab3_2_cars_service.repositories.CarRepository;
import pt.ua.tqs.lab3_2_cars_service.services.CarManagerService;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class CarManagerServiceTest {

    @Mock
    private CarRepository mockedRepo;

    @InjectMocks
    private CarManagerService service;


    @Test
    @DisplayName("Test saveCar method")
    public void whenSaveCar_thenWriteInDb() {

        Car car = new Car("Renault", "Clio");

        //load expectations
        when(mockedRepo.save(car)).thenReturn(car);

        //execute
        Car carRes = service.saveCar(car);

        //assert and verify
        assertThat(carRes).isNotNull().isEqualTo(car);
        verify(mockedRepo, times(1)).save(car);
    }


    @Test
    @DisplayName("Test getAllCars method")
    public void given3Cars_whenGetAllCars_thenReturn3Cars() {
        Car car1 = new Car("Ford", "Mustang");
        Car car2 = new Car("Toyota", "Prius");
        Car car3 = new Car("Audi", "A3");

        //load expectations
        when(mockedRepo.findAll()).thenReturn(Arrays.asList(car1, car2, car3));

        //execute
        List<Car> allCars = service.getAllCars();

        //assert and verify
        assertThat(allCars).hasSize(3).extracting(Car::getMaker).containsExactly("Ford", "Toyota", "Audi");
        assertThat(allCars).hasSize(3).extracting(Car::getModel).containsExactly("Mustang", "Prius", "A3");
        verify(mockedRepo, times(1)).findAll();
    }


    @Test
    @DisplayName("Test getCarDetails method")
    public void givenAnExistingCarId_thenReturnCar() {
        Car car = new Car("Ford", "Mustang");

        //load expectations
        when(mockedRepo.findByCarId(1L)).thenReturn(car);

        //execute
        Optional<Car> carFromDb = service.getCarDetails(1L);

        //assert
        assertThat(carFromDb.isPresent());
        assertThat(carFromDb.get().getMaker()).isEqualTo("Ford");
        assertThat(carFromDb.get().getModel()).isEqualTo("Mustang");

        //verify
        verify(mockedRepo, times(1)).findByCarId(anyLong());
    }
    

    @Test
    @DisplayName("Test getCarDetails method")
    public void givenANonExistingCarId_thenReturnEmpty() {

        //load expectations
        when(mockedRepo.findByCarId(1L)).thenReturn(null);

        //execute
        Optional<Car> carFromDb = service.getCarDetails(1L);

        //assert and verify
        assertThat(carFromDb.isEmpty());

        //verify
        verify(mockedRepo, times(1)).findByCarId(anyLong());
    }
}
