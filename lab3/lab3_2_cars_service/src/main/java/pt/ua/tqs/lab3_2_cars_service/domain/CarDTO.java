package pt.ua.tqs.lab3_2_cars_service.domain;

public class CarDTO {

    private Long carId;
    private String maker;
    private String model;


    public static CarDTO fromCarEntity(Car car) {
        return new CarDTO(car.getCarID(), car.getMaker(), car.getModel());
    }

    public Car toCarEntity() {
        Car car = new Car(maker, model);
        car.setCarID(carId);
        return car;
    }

    public CarDTO() {
    }

    public CarDTO(Long carId, String maker, String model) {
        this.carId = carId;
        this.maker = maker;
        this.model = model;
    }

    public Long getCarId() {
        return carId;
    }

    public String getMaker() {
        return maker;
    }

    public String getModel() {
        return model;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public void setModel(String model) {
        this.model = model;
    }

}
