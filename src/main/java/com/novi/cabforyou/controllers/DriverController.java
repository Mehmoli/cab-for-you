package com.novi.cabforyou.controllers;

import com.novi.cabforyou.dtos.DriverDto;
import com.novi.cabforyou.services.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cabforyou/drivers")
public class DriverController {

    private DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    //Is OK
    @GetMapping("")
    public ResponseEntity<List<DriverDto>> getAllDrivers() {

        List<DriverDto> dtos = driverService.getAllDrivers();

        return ResponseEntity.ok(dtos);
    }

    //Is OK
    @GetMapping("/{username}")
    public ResponseEntity<DriverDto> getDriver(@PathVariable("username") String username) {
        DriverDto dto = driverService.getDriver(username);
        return ResponseEntity.ok(dto);
    }

    //Is OK
    @PostMapping("")
    public ResponseEntity<DriverDto> addDriver(@RequestBody DriverDto dto) {
        DriverDto dto1 = driverService.addDriver(dto);
        return ResponseEntity.created(null).body(dto1);
    }

    //Is OK
    @PutMapping(value = "/{username}")
    public ResponseEntity<DriverDto> updateDriver(@PathVariable("username") String username, @RequestBody DriverDto dto) {

        driverService.updateDriver(username, dto);

        return ResponseEntity.noContent().build();
    }

    //Is OK
    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Object> deleteDriver(@PathVariable("username") String username) {
        driverService.deleteDriver(username);
        return ResponseEntity.noContent().build();
    }
}