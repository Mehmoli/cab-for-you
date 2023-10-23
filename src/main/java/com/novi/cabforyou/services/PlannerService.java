package com.novi.cabforyou.services;

import com.novi.cabforyou.dtos.PlannerDto;
import com.novi.cabforyou.exceptions.UsernameNotFoundException;
import com.novi.cabforyou.models.Planner;
import com.novi.cabforyou.repositories.PlannerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlannerService {

    private PlannerRepository plannerRepository;

    public PlannerService(PlannerRepository plannerRepository){
        this.plannerRepository = plannerRepository;
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


    public void updatePlanner(String username, PlannerDto newPlanner) {
        Optional<Planner> plannerOptional = plannerRepository.findByUsername(username);

        if (plannerOptional.isPresent()) {
            Planner planner = plannerOptional.get();
            planner.setEmail(newPlanner.getEmail());
            planner.setPlannerName(newPlanner.getPlannerName());
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
        dto.plannerName = planner.getPlannerName();

        return dto;
    }

    private Planner transferToPlanner(PlannerDto plannerDto) {

        Planner planner = new Planner();

        planner.setUsername(plannerDto.getUsername());
        planner.setPassword(plannerDto.getPassword());
        planner.setEmail(plannerDto.getEmail());
        planner.setPlannerName(plannerDto.getPlannerName());

        return planner;
    }
}
