package com.novi.cabforyou.controllers;

import com.novi.cabforyou.dtos.OnUpdateValidation;
import com.novi.cabforyou.dtos.TripDto;
import com.novi.cabforyou.services.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/trips")
public class TripController {
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("")
    public ResponseEntity<List<TripDto>> getTrips() {
        var trips = tripService.getAllTrips().stream().map(tripService::transferToTripDto).toList();
        if (trips.stream().anyMatch(Objects::isNull)){
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok().body(trips);
    }

    @GetMapping("{id}")
    public ResponseEntity<TripDto> getTrip(@PathVariable Long id) {
        TripDto trip = tripService.transferToTripDto(tripService.getTrip(id));
        if (trip == null){
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok().body(trip);
    }

    @PostMapping("")
    public ResponseEntity<TripDto> addTrip(@Validated(OnUpdateValidation.class) @RequestBody TripDto dto) {
        TripDto dto1 = tripService.addTrip(dto);
        return ResponseEntity.created(null).body(dto1);
    }
}