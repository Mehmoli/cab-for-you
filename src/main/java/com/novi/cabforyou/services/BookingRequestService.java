package com.novi.cabforyou.services;

import com.novi.cabforyou.dtos.BookingRequestDto;
import com.novi.cabforyou.exceptions.RecordNotFoundException;
import com.novi.cabforyou.models.BookingRequest;
import com.novi.cabforyou.models.BookingStatus;
import com.novi.cabforyou.models.CarType;
import com.novi.cabforyou.repositories.BookingRequestRepository;
import com.novi.cabforyou.repositories.TripRepository;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingRequestService {

    private final BookingRequestRepository bookingRequestRepository;
    private final TripRepository tripRepository;

    public BookingRequestService(BookingRequestRepository bookingRequestRepository, TripRepository tripRepository) {
        this.bookingRequestRepository = bookingRequestRepository;
        this.tripRepository = tripRepository;
    }

    public List<BookingRequestDto> getAllBookings(){
        List<BookingRequestDto> bookingRequestDto = new ArrayList<>();
        List<BookingRequest> bookingRequests = bookingRequestRepository.findAll();
        for (BookingRequest b: bookingRequests){
            bookingRequestDto.add(transferToBookingRequestDto(b));
        }
        return bookingRequestDto;
    }

    public BookingRequestDto getBooking(long id) {
        Optional<BookingRequest> booking = bookingRequestRepository.findById(id);
        if(booking.isPresent()) {
            return transferToBookingRequestDto(booking.get());
        } else {
            throw new RecordNotFoundException("No booking found!!!");
        }
    }

    public BookingRequestDto addBooking(BookingRequestDto bookingRequestDto) {
        BookingRequest bookingRequest = transferToBooking(bookingRequestDto);

        CalculatedTripAndKmPriceSetter(bookingRequestDto, bookingRequest);
        bookingRequestRepository.save(bookingRequest);
        return bookingRequestDto;
    }

    public void updateBooking (Long id, BookingRequestDto newBooking){
        if(!bookingRequestRepository.existsById(id)) throw new RecordNotFoundException("BookingRequest not found");
        BookingRequest bookingRequest = bookingRequestRepository.findById(id).get();

        CalculatedTripAndKmPriceSetter(newBooking, bookingRequest);

        bookingRequest.setTripDate(newBooking.getTripDate());
        bookingRequest.setTripTime(newBooking.getTripTime());
        bookingRequest.setFromAddress(newBooking.getFromAddress());
        bookingRequest.setToAddress(newBooking.getToAddress());
        bookingRequest.setNumberOfPeople(newBooking.getNumberOfPeople());
        bookingRequest.setDistanceInKm(newBooking.getDistanceInKm());
        bookingRequest.setCarType(newBooking.getCarType());
        bookingRequest.setBookingStatus(newBooking.getBookingStatus());
        bookingRequestRepository.save(bookingRequest);
    }

    private void CalculatedTripAndKmPriceSetter(BookingRequestDto newBooking, BookingRequest bookingRequest) {
        CarType selectedCarType = newBooking.getCarType();
        double kmPrice= getCorrectKmPrice(selectedCarType);
        newBooking.setKmPrice(kmPrice);
        bookingRequest.setKmPrice(kmPrice);

        double distanceInKM = newBooking.getDistanceInKm();

        double tripPrice = calculateTripPrice(selectedCarType, distanceInKM);
        newBooking.setTripPrice(tripPrice);
        bookingRequest.setTripPrice(tripPrice);
    }


    public BookingRequestDto patchBookingRequest(Long id, BookingRequestDto updatedBookingRequestDto) {
        Optional<BookingRequest> optionalBookingRequest = bookingRequestRepository.findById(id);

        if (optionalBookingRequest.isPresent()) {

            BookingRequest existingBookingRequest = optionalBookingRequest.get();


        if(updatedBookingRequestDto.getTrip() != null){
            existingBookingRequest.setTrip(tripRepository.getById(updatedBookingRequestDto.getTrip().getTripId()));
        }
        if (updatedBookingRequestDto.getCustomer() != null) {
            existingBookingRequest.setCustomer(updatedBookingRequestDto.getCustomer());
        }
        if (updatedBookingRequestDto.getTripDate() != null) {
            existingBookingRequest.setTripDate(updatedBookingRequestDto.getTripDate());
        }
        if (updatedBookingRequestDto.getTripTime() != null) {
            existingBookingRequest.setTripTime(updatedBookingRequestDto.getTripTime());
        }
        if (updatedBookingRequestDto.getFromAddress() != null) {
            existingBookingRequest.setFromAddress(updatedBookingRequestDto.getFromAddress());
        }
        if (updatedBookingRequestDto.getToAddress() != null) {
            existingBookingRequest.setToAddress(updatedBookingRequestDto.getToAddress());
        }
        if (updatedBookingRequestDto.getNumberOfPeople() != 0) {
            existingBookingRequest.setNumberOfPeople(updatedBookingRequestDto.getNumberOfPeople());
        }
        if (updatedBookingRequestDto.getDistanceInKm() != 0) {
            existingBookingRequest.setDistanceInKm(updatedBookingRequestDto.getDistanceInKm());
        }
        if (updatedBookingRequestDto.getKmPrice() != 0) {
            existingBookingRequest.setKmPrice(updatedBookingRequestDto.getKmPrice());
        }
        if (updatedBookingRequestDto.getTripPrice() != 0) {
                existingBookingRequest.setTripPrice(updatedBookingRequestDto.getTripPrice());
        }
        if (updatedBookingRequestDto.getCarType() != null) {
            existingBookingRequest.setCarType(updatedBookingRequestDto.getCarType());
        }
        if (updatedBookingRequestDto.getBookingStatus() != null) {
            existingBookingRequest.setBookingStatus(updatedBookingRequestDto.getBookingStatus());
        }

        bookingRequestRepository.save(existingBookingRequest);

            return transferToBookingRequestDto(existingBookingRequest);
        } else {
            throw new RecordNotFoundException("Booking request not found");
        }
    }

    public void deleteBooking (long id){
        bookingRequestRepository.deleteById(id);
    }

    public List<BookingRequest> getBookingRequestsByStatus(BookingStatus status) {
        return bookingRequestRepository.findByBookingStatus(status);
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
        bookingRequest.setKmPrice(dto.getKmPrice());
        bookingRequest.setTripPrice(dto.getTripPrice());
        bookingRequest.setCarType(dto.getCarType());
        bookingRequest.setBookingStatus(dto.getBookingStatus());

        return bookingRequest;
    }

    private BookingRequestDto transferToBookingRequestDto(BookingRequest bookingRequest) {
        var dto = new BookingRequestDto();

        dto.bookingId = bookingRequest.getBookingId();
        dto.customer = bookingRequest.getCustomer();
        dto.tripDate = bookingRequest.getTripDate();
        dto.tripTime = bookingRequest.getTripTime();
        dto.fromAddress = bookingRequest.getFromAddress();
        dto.toAddress= bookingRequest.getToAddress();
        dto.numberOfPeople = bookingRequest.getNumberOfPeople();
        dto.distanceInKm = bookingRequest.getDistanceInKm();
        dto.kmPrice = bookingRequest.getKmPrice();
        dto.tripPrice = bookingRequest.getTripPrice();
        dto.carType = bookingRequest.getCarType();
        dto.bookingStatus = bookingRequest.getBookingStatus();

        return dto;
    }

    private BookingRequest transferToBookingRequest(BookingRequestDto bookingRequestDto) {

        BookingRequest bookingRequest = new BookingRequest();

        bookingRequest.setTripDate(bookingRequestDto.getTripDate());
        bookingRequest.setTripTime(bookingRequestDto.getTripTime());
        bookingRequest.setNumberOfPeople(bookingRequestDto.getNumberOfPeople());
        bookingRequest.setFromAddress(bookingRequestDto.getFromAddress());
        bookingRequest.setToAddress(bookingRequestDto.getToAddress());
        bookingRequest.setDistanceInKm(bookingRequest.getDistanceInKm());
        bookingRequest.setTripPrice(bookingRequestDto.getTripPrice());
        bookingRequest.setTrip(bookingRequestDto.getTrip());

        return bookingRequest;
    }

    public double getCorrectKmPrice(CarType carType){

        return carType.getPrice();
    }

    public double calculateTripPrice(CarType carType, double distanceInKM) {

        double basePrice = carType.getPrice();

        return Precision.round((basePrice * distanceInKM),2);
    }

}
