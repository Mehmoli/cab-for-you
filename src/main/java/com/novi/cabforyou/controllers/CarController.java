package com.novi.cabforyou.controllers;

import com.novi.cabforyou.dtos.CarDto;
import com.novi.cabforyou.services.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    // Is OK
    @GetMapping("")
    public ResponseEntity<List<CarDto>> getAllCars() {

        List<CarDto> dtos = carService.getAllCars();

        return ResponseEntity.ok(dtos);
    }

    // Is OK
    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCar(@PathVariable("id") long id) {

        CarDto dto = carService.getCar(id);

        return ResponseEntity.ok(dto);
    }

    //Is OK
    @PostMapping("")
    public ResponseEntity<CarDto> addCar(@RequestBody CarDto dto) {
        CarDto dto1 = carService.addCar(dto);
        return ResponseEntity.created(null).body(dto1);
    }

    //Is OK
    @PutMapping(value = "/{id}")
    public ResponseEntity<CarDto> updateCar(@PathVariable("id") long id, @RequestBody CarDto dto) {

        carService.updateCar(id, dto);

        return ResponseEntity.noContent().build();
    }

    //Is OK
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

}
