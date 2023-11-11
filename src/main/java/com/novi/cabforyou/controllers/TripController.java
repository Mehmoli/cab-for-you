package com.novi.cabforyou.controllers;

import com.novi.cabforyou.dtos.TripDto;
import com.novi.cabforyou.services.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("")
    public ResponseEntity<List<TripDto>> getTrips() {
        var trips = tripService.getAllTrips().stream().map(TripDto::transferToTripDto).toList();
        return ResponseEntity.ok().body(trips);
    }

    @GetMapping("{id}")
    public ResponseEntity<TripDto> getTrip(@PathVariable Long id) {
        TripDto trip = TripDto.transferToTripDto(tripService.getTrip(id));
        return ResponseEntity.ok().body(trip);
    }

}