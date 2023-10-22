package com.novi.cabforyou.services;

import com.novi.cabforyou.dtos.CarDto;
import com.novi.cabforyou.exceptions.RecordNotFoundException;
import com.novi.cabforyou.models.Car;
import com.novi.cabforyou.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarDto> getAllCars() {
        List<CarDto> carDto = new ArrayList<>();
        List<Car> cars = carRepository.findAll();
        for (Car c: cars){
            carDto.add(transferToCarDto(c));
        }
        return carDto;
    }


    public CarDto getCar(long id) {
        Optional<Car> car = carRepository.findById(id);
        if(!car.isPresent()){
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
        if(!carRepository.existsById(id)) throw new RecordNotFoundException();
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
        dto.licensePlate =car.getLicensePlate();
        dto.carType =car.getCarType();

        return dto;
    }


}
