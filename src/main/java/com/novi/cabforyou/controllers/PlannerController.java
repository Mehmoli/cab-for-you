package com.novi.cabforyou.controllers;

import com.novi.cabforyou.dtos.PlannerDto;
import com.novi.cabforyou.services.PlannerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/planners")
public class PlannerController {
    private PlannerService plannerService;

    public PlannerController(PlannerService plannerService) {
        this.plannerService = plannerService;
    }

    @GetMapping("")
    public ResponseEntity<List<PlannerDto>> getAllPlanners() {
        List<PlannerDto> dtos = plannerService.getAllPlanners();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{username}")
    public ResponseEntity<PlannerDto> getPlanner(@PathVariable("username") String username) {
        PlannerDto dto = plannerService.getPlanner(username);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("")
    public ResponseEntity<PlannerDto> addPlanner(@RequestBody PlannerDto dto) {
        PlannerDto dto1 = plannerService.addPlanner(dto);
        return ResponseEntity.created(null).body(dto1);
    }

    @PutMapping("/{username}")
    public ResponseEntity<PlannerDto> updatePlanner(@PathVariable("username") String username, @RequestBody PlannerDto updatedPlannerDto) {
        PlannerDto updatedDto = plannerService.updatePlanner(username, updatedPlannerDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Object> deletePlanner(@PathVariable("username") String username) {
        plannerService.deletePlanner(username);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{username}")
    public ResponseEntity<PlannerDto> patchPlanner(@PathVariable("username") String username, @RequestBody PlannerDto updatedPlannerDto) {
        PlannerDto updatedDto = plannerService.patchPlanner(username, updatedPlannerDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

}
