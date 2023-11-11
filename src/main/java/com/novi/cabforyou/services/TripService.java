package com.novi.cabforyou.services;

import com.novi.cabforyou.dtos.TripDto;
import com.novi.cabforyou.exceptions.RecordNotFoundException;
import com.novi.cabforyou.models.*;
import com.novi.cabforyou.repositories.TripRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    private final TripRepository tripRepository;


    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Trip getTrip(Long id) {
        Optional<Trip> tripOptional = tripRepository.findById(id);
        if (tripOptional.isPresent()) {
            return tripOptional.get();
        } else {
            throw new RecordNotFoundException("Trip Id is not found");
        }
    }

    @Transactional
    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }

    public Trip transferToTrip(TripDto tripDto) {

        Trip trip = new Trip();
        trip.setDriver(tripDto.getDriver());
        trip.setBookingRequests(tripDto.getBookingRequests());

        return trip;
    }

    public TripDto transferToTripDto(Trip trip) {

        TripDto tripDto = new TripDto();
        tripDto.tripId = trip.getTripId();
        tripDto.driver = trip.getDriver();
        tripDto.bookingRequests = trip.getBookingRequests();

        return tripDto;

    }
}
