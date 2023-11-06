package com.novi.cabforyou.services;

import com.novi.cabforyou.dtos.PlannerDto;
import com.novi.cabforyou.exceptions.RecordNotFoundException;
import com.novi.cabforyou.exceptions.UsernameNotFoundException;
import com.novi.cabforyou.models.Planner;
import com.novi.cabforyou.models.Trip;
import com.novi.cabforyou.repositories.PlannerRepository;
import com.novi.cabforyou.repositories.TripRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlannerService {

    private final PlannerRepository plannerRepository;
    private final TripRepository tripRepository;

    public PlannerService(PlannerRepository plannerRepository, TripRepository tripRepository) {
        this.plannerRepository = plannerRepository;
        this.tripRepository = tripRepository;
    }

    public List<PlannerDto> getAllPlanners() {
        List<PlannerDto> plannerDto = new ArrayList<>();
        List<Planner> planners = plannerRepository.findAll();
        for (Planner dr: planners){
            plannerDto.add(transferToPlannerDto(dr));
        }
        return plannerDto;
    }

    public PlannerDto getPlanner(String username) {
        PlannerDto dto = new PlannerDto();
        Optional<Planner> planner = plannerRepository.findByUsername(username);
        if (planner.isPresent()) {
            dto = transferToPlannerDto(planner.get());
        } else {
            throw new UsernameNotFoundException(username);
        }
        return dto;
    }

    public PlannerDto addPlanner(PlannerDto plannerDto) {

        Planner planner = transferToPlanner(plannerDto);
        plannerRepository.save(planner);
        return plannerDto;
    }

    public void addPlannerTrip(Long trip, String username) {
        Optional<Planner> plannerOption = plannerRepository.findByUsername(username);
        if (plannerOption.isPresent()) {
           Planner planner = plannerOption.get();
            Trip tr = tripRepository.findById(trip).orElse(null);
            assert tr != null;
            tr.setPlanner(planner);
            tripRepository.save(tr);
            planner.addTrip(tr);
            plannerRepository.save(planner);
        } else {
            throw new RecordNotFoundException("Driver not found");
        }
    }


    public void updatePlanner(String username, PlannerDto newPlanner) {
        Optional<Planner> plannerOptional = plannerRepository.findByUsername(username);

        if (plannerOptional.isPresent()) {
            Planner planner = plannerOptional.get();
            planner.setEmail(newPlanner.getEmail());
            planner.setEmployeeNumber(newPlanner.getEmployeeNumber());
            planner.setUsername(newPlanner.getUsername());
            planner.setPassword(newPlanner.getPassword());

            plannerRepository.save(planner);
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    @Transactional
    public void deletePlanner(String username) {
        plannerRepository.deleteByUsername(username);
    }

    private PlannerDto transferToPlannerDto(Planner planner) {

        var dto = new PlannerDto();

        dto.username = planner.getUsername();
        dto.password = planner.getPassword();
        dto.email = planner.getEmail();
        dto.employeeNumber = planner.getEmployeeNumber();

        return dto;
    }

    private Planner transferToPlanner(PlannerDto plannerDto) {

        Planner planner = new Planner();

        planner.setUsername(plannerDto.getUsername());
        planner.setPassword(plannerDto.getPassword());
        planner.setEmail(plannerDto.getEmail());
        planner.setEmployeeNumber(plannerDto.getEmployeeNumber());

        return planner;
    }

    public void patchOne(String username, Planner planner) {
        //Todo
    }

    public Planner getOne(String username) {
        //Todo
        return null;
    }
}
