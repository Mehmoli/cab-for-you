package com.novi.cabforyou.services;


import com.novi.cabforyou.repositories.CompletedTripsRepository;
import org.springframework.stereotype.Service;

@Service
public class CompletedTripsService {
    private CompletedTripsRepository completedTripsRepository;

    public CompletedTripsService(CompletedTripsRepository completedTripsRepository){
        this.completedTripsRepository = completedTripsRepository;
    }


}
