package com.novi.cabforyou.services;

import com.novi.cabforyou.dtos.DriverDto;
import com.novi.cabforyou.exceptions.UsernameNotFoundException;
import com.novi.cabforyou.models.Driver;
import com.novi.cabforyou.repositories.DriverRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    private DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public List<DriverDto> getAllDrivers() {
        List<DriverDto> driverDto = new ArrayList<>();
        List<Driver> drivers = driverRepository.findAll();
        for (Driver dr: drivers){
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


    public void updateDriver(String username, DriverDto newDriver) {
        Optional<Driver> driverOptional = driverRepository.findByUsername(username);

        if (driverOptional.isPresent()) {
            Driver driver = driverOptional.get();
            driver.setEmail(newDriver.getEmail());
//            driver.setAuthorities(newDriver.getAuthorities());
            driver.setDriverCallSign(newDriver.getDriverCallSign());
            driver.setDriverPhone(newDriver.getDriverPhone());
            driver.setLicenceNumber(newDriver.getLicenceNumber());

            driverRepository.save(driver);
        } else {
            throw new UsernameNotFoundException(username);
        }
    }



    @Transactional
    public void deleteDriver(String username) {
        driverRepository.deleteByUsername(username);
    }

    private DriverDto transferToDriverDto(Driver driver) {

        var dto = new DriverDto();

        dto.username = driver.getUsername();
        dto.password = driver.getPassword();
        dto.email = driver.getEmail();
        dto.licenceNumber = driver.getLicenceNumber();
        dto.driverPhone = driver.getDriverPhone();
        dto.authorities = driver.getAuthorities();
        dto.driverCallSign = dto.getDriverCallSign();

        return dto;
    }
    
    private Driver transferToDriver(DriverDto driverDto) {

        Driver driver = new Driver();

        driver.setUsername(driverDto.getUsername());
        driver.setPassword(driverDto.getPassword());
        driver.setEmail(driverDto.getEmail());
        driver.setLicenceNumber(driverDto.getLicenceNumber());
        driver.setDriverPhone(driverDto.getDriverPhone());
        driver.setAuthorities(driverDto.getAuthorities());
        driver.setDriverCallSign(driverDto.getDriverCallSign());

        return driver;
    }
}
