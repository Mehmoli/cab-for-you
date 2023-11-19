package com.novi.cabforyou.services;

import com.novi.cabforyou.dtos.DriverDto;
import com.novi.cabforyou.dtos.TripDto;
import com.novi.cabforyou.exceptions.UsernameNotFoundException;
import com.novi.cabforyou.models.Driver;
import com.novi.cabforyou.models.Trip;
import com.novi.cabforyou.repositories.DriverRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    private final TripService tripService;
    private final PasswordEncoder passwordEncoder;

    public DriverService(DriverRepository driverRepository, @Lazy TripService tripService, PasswordEncoder passwordEncoder) {
        this.driverRepository = driverRepository;
        this.tripService = tripService;
        this.passwordEncoder = passwordEncoder;
    }

    public List<DriverDto> getAllDrivers() {
        List<DriverDto> driverDto = new ArrayList<>();
        List<Driver> drivers = driverRepository.findAll();
        for (Driver dr : drivers) {
            driverDto.add(transferToDriverDto(dr));
        }
        return driverDto;
    }

    public DriverDto getDriver(String username) {
        DriverDto dto = new DriverDto();
        Optional<Driver> driver = driverRepository.findByUsername(username);
        if (driver.isPresent()) {
            dto = transferToDriverDto(driver.get());
        } else {
            throw new UsernameNotFoundException(username);
        }
        return dto;
    }

    public DriverDto addDriver(DriverDto driverDto) {

        Driver driver = transferToDriver(driverDto);
        driverRepository.save(driver);
        return driverDto;
    }

    @Transactional
    public DriverDto updateDriver(String username, DriverDto updatedDriverDto) {
        Optional<Driver> optionalDriver = driverRepository.findByUsername(username);
        if (optionalDriver.isPresent()) {
            Driver existingDriver = optionalDriver.get();

            existingDriver.setPassword(passwordEncoder.encode(updatedDriverDto.getPassword()));
            existingDriver.setEmail(updatedDriverDto.getEmail());
            existingDriver.setLicenceNumber(updatedDriverDto.getLicenceNumber());
            existingDriver.setPhoneNumber(updatedDriverDto.getPhoneNumber());

            driverRepository.save(existingDriver);

            return transferToDriverDto(existingDriver);
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    @Transactional
    public void deleteDriver(String username) {
        driverRepository.deleteByUsername(username);
    }

    @Transactional
    public DriverDto patchDriver(String username, DriverDto updatedDriverDto) {
        Optional<Driver> optionalDriver = driverRepository.findByUsername(username);
        if (optionalDriver.isPresent()) {

            Driver existingDriver = optionalDriver.get();

            if (updatedDriverDto.getEmail() != null) {
                existingDriver.setEmail(updatedDriverDto.getEmail());
            }
            if (updatedDriverDto.getLicenceNumber() != null) {
                existingDriver.setLicenceNumber(updatedDriverDto.getLicenceNumber());
            }
            if (updatedDriverDto.getPhoneNumber() != null) {
                existingDriver.setPhoneNumber(updatedDriverDto.getPhoneNumber());
            }

            driverRepository.save(existingDriver);

            return transferToDriverDto(existingDriver);
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    //DRIVERS TRIPS HANDLING
    public List<Trip> getDriverTrips(String username) {
        Optional<Driver> optionalDriver = driverRepository.findByUsername(username);
        if (optionalDriver.isPresent()) {
            Driver driver = optionalDriver.get();
            return driver.getTrips();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public List<TripDto> getDriverTripsDto(String username) {
        List<Trip> trips = getDriverTrips(username);
        return trips.stream().map(tripService::transferToTripDto).toList();
    }

    public DriverDto transferToDriverDto(Driver driver) {

        var dto = new DriverDto();

        dto.username = driver.getUsername();
        dto.password = driver.getPassword();
        dto.email = driver.getEmail();
        dto.licenceNumber = driver.getLicenceNumber();
        dto.phoneNumber = driver.getPhoneNumber();
        dto.authorities = driver.getAuthorities();

        return dto;
    }

    private Driver transferToDriver(DriverDto driverDto) {
        Driver driver = new Driver();
        driver.setUsername(driverDto.getUsername());
        driver.setPassword(passwordEncoder.encode(driverDto.getPassword()));
        driver.setEmail(driverDto.getEmail());
        driver.setLicenceNumber(driverDto.getLicenceNumber());
        driver.setPhoneNumber(driverDto.getPhoneNumber());
        driver.setAuthorities(driverDto.getAuthorities());

        return driver;
    }
}
