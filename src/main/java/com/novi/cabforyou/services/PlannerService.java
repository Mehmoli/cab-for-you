package com.novi.cabforyou.services;

import com.novi.cabforyou.dtos.PlannerDto;
import com.novi.cabforyou.exceptions.UsernameNotFoundException;
import com.novi.cabforyou.models.Planner;
import com.novi.cabforyou.repositories.PlannerRepository;
import com.novi.cabforyou.repositories.TripRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlannerService {

    private final PlannerRepository plannerRepository;
    private final TripRepository tripRepository;

    private final PasswordEncoder passwordEncoder;

    public PlannerService(PlannerRepository plannerRepository, TripRepository tripRepository, PasswordEncoder passwordEncoder) {
        this.plannerRepository = plannerRepository;
        this.tripRepository = tripRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<PlannerDto> getAllPlanners() {
        List<PlannerDto> plannerDto = new ArrayList<>();
        List<Planner> planners = plannerRepository.findAll();
        for (Planner dr : planners) {
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

    @Transactional
    public PlannerDto updatePlanner(String username, PlannerDto updatedPlannerDto) {
        Optional<Planner> optionalPlanner = plannerRepository.findByUsername(username);

        if (optionalPlanner.isPresent()) {
            Planner existingPlanner = optionalPlanner.get();

            existingPlanner.setPassword(updatedPlannerDto.getPassword());
            existingPlanner.setEmail(updatedPlannerDto.getEmail());
            existingPlanner.setEmployeeNumber(updatedPlannerDto.getEmployeeNumber());
            existingPlanner.setPhoneNumber(updatedPlannerDto.getPhoneNumber());

            return transferToPlannerDto(existingPlanner);
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    @Transactional
    public void deletePlanner(String username) {
        plannerRepository.deleteByUsername(username);
    }

    @Transactional
    public PlannerDto patchPlanner(String username, PlannerDto updatedPlannerDto) {
        Optional<Planner> optionalPlanner = plannerRepository.findByUsername(username);
        if (optionalPlanner.isPresent()) {
            Planner existingPlanner = optionalPlanner.get();

            if (updatedPlannerDto.getEmail() != null) {
                existingPlanner.setEmail(updatedPlannerDto.getEmail());
            }
            if (updatedPlannerDto.getEmployeeNumber() != null) {
                existingPlanner.setEmployeeNumber(updatedPlannerDto.getEmployeeNumber());
            }
            if (updatedPlannerDto.getPhoneNumber() != null) {
                existingPlanner.setPhoneNumber(updatedPlannerDto.getPhoneNumber());
            }

            plannerRepository.save(existingPlanner);

            return transferToPlannerDto(existingPlanner);
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public PlannerDto transferToPlannerDto(Planner planner) {

        var dto = new PlannerDto();

        dto.username = planner.getUsername();
        dto.password = planner.getPassword();
        dto.email = planner.getEmail();
        dto.employeeNumber = planner.getEmployeeNumber();
        dto.phoneNumber = planner.getPhoneNumber();
        dto.authorities = planner.getAuthorities();

        return dto;
    }

    private Planner transferToPlanner(PlannerDto plannerDto) {

        Planner planner = new Planner();

        planner.setUsername(plannerDto.getUsername());
        planner.setPassword(passwordEncoder.encode(plannerDto.getPassword()));
        planner.setEmail(plannerDto.getEmail());
        planner.setEmployeeNumber(plannerDto.getEmployeeNumber());
        planner.setPhoneNumber(plannerDto.getPhoneNumber());
        planner.setAuthorities(plannerDto.getAuthorities());

        return planner;
    }

}
