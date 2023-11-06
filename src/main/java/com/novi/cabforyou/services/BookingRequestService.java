package com.novi.cabforyou.services;


import com.novi.cabforyou.dtos.BookingRequestDto;
import com.novi.cabforyou.exceptions.BadRequestException;
import com.novi.cabforyou.exceptions.RecordNotFoundException;
import com.novi.cabforyou.models.BookingRequest;
import com.novi.cabforyou.repositories.BookingRepository;
import com.novi.cabforyou.repositories.TripRepository;
import org.springframework.stereotype.Service;
import org.apache.commons.math3.util.Precision;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final TripRepository tripRepository;

    public BookingService(BookingRepository bookingRepository, TripRepository tripRepository) {
        this.bookingRepository = bookingRepository;
        this.tripRepository = tripRepository;
    }

    public List<BookingRequestDto> getAllBookings(){
        List<BookingRequestDto> bookingRequestDto = new ArrayList<>();
        List<BookingRequest> bookingRequests = bookingRepository.findAll();
        for (BookingRequest b: bookingRequests){
            bookingRequestDto.add(transferToDto(b));
        }
        return bookingRequestDto;
    }

    public BookingRequestDto getBooking(long id) {
        Optional<BookingRequest> booking = bookingRepository.findById(id);
        if(booking.isPresent()) {
            return transferToDto(booking.get());
        } else {
            throw new RecordNotFoundException("No booking found!!!");
        }
    }

    public BookingRequestDto addBooking(BookingRequestDto bookingRequestDto) {
        BookingRequest bookingRequest =  transferToBooking(bookingRequestDto);
        double tripPrice = calculateTripPrice(
                bookingRequestDto.getNumberOfPeople(),
                bookingRequestDto.getTripKmPriceMiniBus(),
                bookingRequestDto.getTripKmPriceCar(),
                bookingRequestDto.getDistanceInKm()
        );
        bookingRequestDto.setTripPrice(tripPrice);
        bookingRequest.setTripPrice(tripPrice);
        bookingRepository.save(bookingRequest);
        return bookingRequestDto;
    }

    public void updateBooking (Long id, BookingRequestDto newBooking){
        if(!bookingRepository.existsById(id)) throw new RecordNotFoundException();
        BookingRequest bookingRequest = bookingRepository.findById(id).get();

        bookingRequest.setTripDate(newBooking.getTripDate());
        bookingRequest.setTripTime(newBooking.getTripTime());
        bookingRequest.setFromAddress(newBooking.getFromAddress());
        bookingRequest.setToAddress(newBooking.getToAddress());
        bookingRequest.setNumberOfPeople(newBooking.getNumberOfPeople());
        bookingRequest.setDistanceInKm(newBooking.getDistanceInKm());
        bookingRequest.setTripKmPriceMiniBus(newBooking.getTripKmPriceMiniBus());
        bookingRequest.setTripKmPriceCar(newBooking.getTripKmPriceCar());
        bookingRequest.setTripPrice(newBooking.getTripPrice());
        bookingRequest.setCarType(newBooking.getCarType());
        bookingRequest.setBookingStatus(newBooking.getBookingStatus());
        bookingRepository.save(bookingRequest);
    }

    public BookingRequest patchBooking(Long id, BookingRequest bookingRequest) {
        if (!bookingRepository.existsById(id)) {
            throw new RecordNotFoundException("BookingRequest not found");}

        BookingRequest patchBookingRequest = bookingRepository.findById(id).orElse(null);

        if(bookingRequest.getTrip() != null){
            patchBookingRequest.setTrip(tripRepository.getById(bookingRequest.getTrip().getTripId()));
        }
        if (bookingRequest.getCustomer() != null) {
            patchBookingRequest.setCustomer(bookingRequest.getCustomer());
        }
//        if (bookingRequest.getInvoice() != null) {
//            patchBookingRequest.setInvoice(bookingRequest.getInvoice());
//        }
        if (bookingRequest.getTripDate() != null) {
            patchBookingRequest.setTripDate(bookingRequest.getTripDate());
        }
        if (bookingRequest.getTripTime() != null) {
            patchBookingRequest.setTripTime(bookingRequest.getTripTime());
        }
        if (bookingRequest.getFromAddress() != null) {
            patchBookingRequest.setFromAddress(bookingRequest.getFromAddress());
        }
        if (bookingRequest.getToAddress() != null) {
            patchBookingRequest.setToAddress(bookingRequest.getToAddress());
        }
        if (bookingRequest.getNumberOfPeople() != 0) {
            patchBookingRequest.setNumberOfPeople(bookingRequest.getNumberOfPeople());
        }
        if (bookingRequest.getDistanceInKm() != 0) {
            patchBookingRequest.setDistanceInKm(bookingRequest.getDistanceInKm());
        }
        if (bookingRequest.getCarType() != null) {
            patchBookingRequest.setCarType(bookingRequest.getCarType());
        }
        if (bookingRequest.getBookingStatus() != null) {
            patchBookingRequest.setBookingStatus(bookingRequest.getBookingStatus());
        }

        bookingRepository.save(patchBookingRequest);
        return patchBookingRequest;
    }

    public void deleteBooking (long id){
        bookingRepository.deleteById(id);
    }

    // Is van client naar server
    private BookingRequest transferToBooking(BookingRequestDto dto) {
        BookingRequest bookingRequest = new BookingRequest();

        bookingRequest.setBookingId(dto.getBookingId());
        bookingRequest.setCustomer(dto.getCustomer());
        bookingRequest.setTripDate(dto.getTripDate());
        bookingRequest.setTripTime(dto.getTripTime());
        bookingRequest.setFromAddress(dto.getFromAddress());
        bookingRequest.setToAddress(dto.getToAddress());
        bookingRequest.setNumberOfPeople(dto.getNumberOfPeople());
        bookingRequest.setDistanceInKm(dto.getDistanceInKm());
        bookingRequest.setTripKmPriceMiniBus(dto.getTripKmPriceMiniBus());
        bookingRequest.setTripKmPriceCar(dto.getTripKmPriceCar());
        bookingRequest.setTripPrice(dto.getTripPrice());
        bookingRequest.setCarType(dto.getCarType());
        bookingRequest.setBookingStatus(dto.getBookingStatus());

        return bookingRequest;
    }

    // Is van Server naar Client
    private BookingRequestDto transferToDto(BookingRequest bookingRequest) {
        var dto = new BookingRequestDto();

        dto.bookingId = bookingRequest.getBookingId();
        dto.customer = bookingRequest.getCustomer();
        dto.tripDate = bookingRequest.getTripDate();
        dto.tripTime = bookingRequest.getTripTime();
        dto.fromAddress = bookingRequest.getFromAddress();
        dto.toAddress= bookingRequest.getToAddress();
        dto.numberOfPeople = bookingRequest.getNumberOfPeople();
        dto.distanceInKm = bookingRequest.getDistanceInKm();
        dto.tripKmPriceMiniBus = bookingRequest.getTripKmPriceMiniBus();
        dto.tripKmPriceCar = bookingRequest.getTripKmPriceCar();
        dto.tripPrice = bookingRequest.getTripPrice();
        dto.carType = bookingRequest.getCarType();
        dto.bookingStatus = bookingRequest.getBookingStatus();

        return dto;
    }

    public double calculateTripPrice(int numberOfPeople, double tripKmPriceMiniBus, double tripKmPriceCar, double distanceInKm) {
        if (numberOfPeople > 8) {
            throw new BadRequestException("We have no cars for more than 8 passengers");
        } else if (numberOfPeople > 4) {
            return Precision.round(tripKmPriceMiniBus * distanceInKm, 2);
        } else {
            return Precision.round(tripKmPriceCar * distanceInKm, 2);
        }
    }

}
