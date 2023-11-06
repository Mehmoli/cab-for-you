package com.novi.cabforyou.services;

import com.novi.cabforyou.dtos.BookingRequestDto;
import com.novi.cabforyou.exceptions.RecordNotFoundException;
import com.novi.cabforyou.models.BookingRequest;
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
            bookingRequestDto.add(transferToDto(b));
        }
        return bookingRequestDto;
    }

    public BookingRequestDto getBooking(long id) {
        Optional<BookingRequest> booking = bookingRequestRepository.findById(id);
        if(booking.isPresent()) {
            return transferToDto(booking.get());
        } else {
            throw new RecordNotFoundException("No booking found!!!");
        }
    }

    public BookingRequestDto addBooking(BookingRequestDto bookingRequestDto) {
        BookingRequest bookingRequest = transferToBooking(bookingRequestDto);

        CarType selectedCarType = bookingRequestDto.getCarType();
        double kmPrice= getCorrectKmPrice(selectedCarType);
        bookingRequestDto.setKmPrice(kmPrice);
        bookingRequest.setKmPrice(kmPrice);

        double distanceInKM = bookingRequestDto.getDistanceInKm();

        double tripPrice = calculateTripPrice(selectedCarType, distanceInKM);
        bookingRequestDto.setTripPrice(tripPrice);
        bookingRequest.setTripPrice(tripPrice);
        bookingRequestRepository.save(bookingRequest);
        return bookingRequestDto;
    }


    public void updateBooking (Long id, BookingRequestDto newBooking){
        if(!bookingRequestRepository.existsById(id)) throw new RecordNotFoundException();
        BookingRequest bookingRequest = bookingRequestRepository.findById(id).get();

        bookingRequest.setTripDate(newBooking.getTripDate());
        bookingRequest.setTripTime(newBooking.getTripTime());
        bookingRequest.setFromAddress(newBooking.getFromAddress());
        bookingRequest.setToAddress(newBooking.getToAddress());
        bookingRequest.setNumberOfPeople(newBooking.getNumberOfPeople());
        bookingRequest.setDistanceInKm(newBooking.getDistanceInKm());
        bookingRequest.setKmPrice(newBooking.getKmPrice());
        bookingRequest.setTripPrice(newBooking.getTripPrice());
        bookingRequest.setCarType(newBooking.getCarType());
        bookingRequest.setBookingStatus(newBooking.getBookingStatus());
        bookingRequestRepository.save(bookingRequest);
    }

    public BookingRequest patchBooking(Long id, BookingRequest bookingRequest) {
        if (!bookingRequestRepository.existsById(id)) {
            throw new RecordNotFoundException("BookingRequest not found");}

        BookingRequest patchBookingRequest = bookingRequestRepository.findById(id).orElse(null);

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

        bookingRequestRepository.save(patchBookingRequest);
        return patchBookingRequest;
    }

    public void deleteBooking (long id){
        bookingRequestRepository.deleteById(id);
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
        dto.kmPrice = bookingRequest.getKmPrice();
        dto.tripPrice = bookingRequest.getTripPrice();
        dto.carType = bookingRequest.getCarType();
        dto.bookingStatus = bookingRequest.getBookingStatus();

        return dto;
    }

    public double getCorrectKmPrice(CarType carType){
       double basekmPrice = carType.getPrice();
       double newkmPrice = basekmPrice;

       return newkmPrice;
    }

    public double calculateTripPrice(CarType carType, double distanceInKM) {

        double basePrice = carType.getPrice();

        double tripPrice = Precision.round((basePrice * distanceInKM),2);

        return tripPrice;
    }


    public BookingRequest getOne(Long bookingId) {
     // Todo
        return null;
    }
}
