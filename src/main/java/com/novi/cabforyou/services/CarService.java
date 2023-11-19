package com.novi.cabforyou.services;

import com.novi.cabforyou.dtos.CarDto;
import com.novi.cabforyou.exceptions.RecordNotFoundException;
import com.novi.cabforyou.models.Car;
import com.novi.cabforyou.enums.CarType;
import com.novi.cabforyou.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarDto> getAllCars() {
        List<CarDto> carDto = new ArrayList<>();
        List<Car> cars = carRepository.findAll();
        for (Car c : cars) {
            carDto.add(transferToCarDto(c));
        }
        return carDto;
    }


    public CarDto getCar(long id) {
        Optional<Car> car = carRepository.findById(id);
        if (!car.isPresent()) {
            throw new RecordNotFoundException();
        } else {
            return transferToCarDto(car.get());
        }
    }

    public CarDto addCar(CarDto carDto) {
        Car car = transferToCar(carDto);
        carRepository.save(car);
        return carDto;

    }

    public void updateCar(long id, CarDto newCar) {
        if (!carRepository.existsById(id)) throw new RecordNotFoundException();
        Car car = carRepository.findById(id).get();
        car.setMake(newCar.getMake());
        car.setModel(newCar.getModel());
        car.setAvailableSeats(newCar.getAvailableSeats());
        car.setLicensePlate(newCar.getLicensePlate());
        car.setCarType(newCar.getCarType());

        carRepository.save(car);
    }

    public void deleteCar(long id) {
        carRepository.deleteById(id);
    }

    private Car transferToCar(CarDto carDto) {
        Car car = new Car();
        car.setCarId(carDto.getCarId());
        car.setMake(carDto.getMake());
        car.setModel(carDto.getModel());
        car.setAvailableSeats(carDto.getAvailableSeats());
        car.setLicensePlate(carDto.getLicensePlate());
        car.setCarType(carDto.getCarType());

        return car;
    }

    private CarDto transferToCarDto(Car car) {
        var dto = new CarDto();

        dto.carId = car.getCarId();
        dto.make = car.getMake();
        dto.model = car.getModel();
        dto.availableSeats = car.getAvailableSeats();
        dto.licensePlate = car.getLicensePlate();
        dto.carType = car.getCarType();

        return dto;
    }

    public CarDto partiallyUpdateCar(long id, Map<String, Object> carUpdates) {
        if (!carRepository.existsById(id)) {
            throw new RecordNotFoundException();
        }

        Car car = carRepository.findById(id).get();

        for (Map.Entry<String, Object> entry : carUpdates.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if ("carType".equals(key)) {
                if (value instanceof String) {
                    car.setCarType(CarType.valueOf((String) value));
                } else {
                    throw new IllegalArgumentException("car type must be a string.");
                }
            } else {
                switch (key) {
                    case "make":
                        car.setMake((String) value);
                        break;
                    case "model":
                        car.setModel((String) value);
                        break;
                    case "availableSeats":
                        car.setAvailableSeats((int) value);
                        break;
                    case "licensePlate":
                        car.setLicensePlate((String) value);
                        break;
                    case "carType":
                        car.setCarType(CarType.valueOf((String) value));
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown field: " + key);
                }
            }
        }
        carRepository.save(car);

        return transferToCarDto(car);
    }

}
