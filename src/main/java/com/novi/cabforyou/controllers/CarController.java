package com.novi.cabforyou.controllers;

import com.novi.cabforyou.dtos.CarDto;
import com.novi.cabforyou.services.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("")
    public ResponseEntity<List<CarDto>> getAllCars() {

        List<CarDto> dtos = carService.getAllCars();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCar(@PathVariable("id") long id) {

        CarDto dto = carService.getCar(id);

        return ResponseEntity.ok(dto);
    }

    @PostMapping("")
    public ResponseEntity<CarDto> addCar(@RequestBody CarDto dto) {
        CarDto dto1 = carService.addCar(dto);
        return ResponseEntity.created(null).body(dto1);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CarDto> updateCar(@PathVariable("id") long id, @RequestBody CarDto dto) {

        carService.updateCar(id, dto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable("id") long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CarDto> partiallyUpdateCar(@PathVariable("id") long id, @RequestBody Map<String, Object> carUpdates) {
        CarDto updatedCar = carService.partiallyUpdateCar(id, carUpdates);
        return ResponseEntity.ok(updatedCar);
    }

}
