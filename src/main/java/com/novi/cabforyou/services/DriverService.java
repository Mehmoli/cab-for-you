package com.novi.cabforyou.services;


import com.novi.cabforyou.repositories.DriverRepository;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

    private DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository){
        this.driverRepository =driverRepository;
    }
}
