package com.novi.cabforyou.services;

import com.novi.cabforyou.dtos.BookingRequestDto;
import com.novi.cabforyou.dtos.DriverDto;
import com.novi.cabforyou.dtos.TripDto;
import com.novi.cabforyou.exceptions.RecordNotFoundException;
import com.novi.cabforyou.models.*;
import com.novi.cabforyou.repositories.BookingRequestRepository;
import com.novi.cabforyou.repositories.DriverRepository;
import com.novi.cabforyou.repositories.TripRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    private final TripRepository tripRepository;

    private final BookingRequestRepository bookingRequestRepository;
    private final BookingRequestService bookingRequestService;

    private final DriverRepository driverRepository;

    private final DriverService driverService;

    public TripService(TripRepository tripRepository,
                       BookingRequestRepository bookingRequestRepository,
                       BookingRequestService bookingRequestService,
                       DriverRepository driverRepository, DriverService driverService) {
        this.tripRepository = tripRepository;
        this.bookingRequestRepository = bookingRequestRepository;
        this.bookingRequestService = bookingRequestService;
        this.driverRepository = driverRepository;
        this.driverService = driverService;
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

    public TripDto addTrip(TripDto tripDto) {
        Trip trip = transferToTrip();
        if (tripDto.bookingRequest != null) {
            Optional<BookingRequest> bookingRequestOpt = bookingRequestRepository.findById(tripDto.bookingRequest.getBookingId());
            if (bookingRequestOpt.isPresent()) {
                BookingRequest bookingRequest = bookingRequestOpt.get();
                trip.setBookingRequest(bookingRequest);
            }
        }

        if (tripDto.driver != null) {
            Optional<Driver> driverOpt = driverRepository.findByUsername(tripDto.driver.getUsername());
            if (driverOpt.isPresent()) {
                Driver driver = driverOpt.get();
                trip.setDriver(driver);
            }
        }

        tripRepository.save(trip);

        tripDto = transferToTripDto(trip);
        return tripDto;
    }

    @Transactional
    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }

    public Trip transferToTrip() {
        Trip trip = new Trip();
        return trip;
    }

    public TripDto transferToTripDto(Trip trip) {
        DriverDto driver;
        BookingRequestDto bookingRequest;

        if (trip.getDriver() != null && trip.getBookingRequest() != null){
            driver = driverService.transferToDriverDto(trip.getDriver());
            bookingRequest = bookingRequestService.transferToBookingRequestDto(trip.getBookingRequest());

            TripDto tripDto = new TripDto(trip.getTripId(), driver, bookingRequest);
            return tripDto;
        }

        return null;
    }
}
