package com.novi.cabforyou.controllers;

import com.novi.cabforyou.dtos.DriverDto;
import com.novi.cabforyou.dtos.TripDto;
import com.novi.cabforyou.services.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("")
    public ResponseEntity<List<DriverDto>> getAllDrivers() {

        List<DriverDto> dtos = driverService.getAllDrivers();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{username}")
    public ResponseEntity<DriverDto> getDriver(@PathVariable("username") String username) {
        DriverDto dto = driverService.getDriver(username);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("")
    public ResponseEntity<DriverDto> addDriver(@RequestBody DriverDto dto) {
        DriverDto dto1 = driverService.addDriver(dto);
        return ResponseEntity.created(null).body(dto1);
    }

    @PutMapping("/{username}")
    public ResponseEntity<DriverDto> updateDriver(@PathVariable("username") String username, @RequestBody DriverDto updatedDriverDto) {
        DriverDto updatedDto = driverService.updateDriver(username, updatedDriverDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Object> deleteDriver(@PathVariable("username") String username) {
        driverService.deleteDriver(username);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{username}")
    public ResponseEntity<DriverDto> patchDriver(@PathVariable("username") String username, @RequestBody DriverDto updatedDriverDto) {
        DriverDto updatedDto = driverService.patchDriver(username, updatedDriverDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    @GetMapping("/{username}/trips")
    public ResponseEntity<List<TripDto>> getDriverTripsDto(@PathVariable("username") String username) {
        List<TripDto> tripsDto = driverService.getDriverTripsDto(username);
        return ResponseEntity.status(HttpStatus.OK).body(tripsDto);
    }
}
