package com.novi.cabforyou.services;


import com.novi.cabforyou.repositories.PlannerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlannerService {

    private PlannerRepository plannerRepository;

    public PlannerService(PlannerRepository plannerRepository){
        this.plannerRepository = plannerRepository;
    }
}
