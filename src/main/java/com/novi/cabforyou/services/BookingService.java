package com.novi.cabforyou.services;


import com.novi.cabforyou.dtos.BookingDto;
import com.novi.cabforyou.exceptions.BadRequestException;
import com.novi.cabforyou.exceptions.RecordNotFoundException;
import com.novi.cabforyou.models.Booking;
import com.novi.cabforyou.repositories.BookingRepository;
import org.springframework.stereotype.Service;
import org.apache.commons.math3.util.Precision;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;
    }

    public List<BookingDto> getAllBookings(){
        List<BookingDto> bookingDto = new ArrayList<>();
        List<Booking> bookings = bookingRepository.findAll();
        for (Booking b: bookings){
            bookingDto.add(transferToDto(b));
        }
        return bookingDto;
    }

    public BookingDto getBooking(long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if(booking.isPresent()) {
            return transferToDto(booking.get());
        } else {
            throw new RecordNotFoundException("No booking found!!!");
        }
    }

    public BookingDto addBooking(BookingDto bookingDto) {
        Booking booking =  transferToBooking(bookingDto);
        double tripPrice = calculateTripPrice(
                bookingDto.getNumberOfPeople(),
                bookingDto.getTripKmPriceMiniBus(),
                bookingDto.getTripKmPriceCar(),
                bookingDto.getDistanceInKm()
        );
        bookingDto.setTripPrice(tripPrice);
        booking.setTripPrice(tripPrice);
        bookingRepository.save(booking);
        return bookingDto;
    }

    public void updateBooking (Long id, BookingDto newBooking){
        if(!bookingRepository.existsById(id)) throw new RecordNotFoundException();
        Booking booking = bookingRepository.findById(id).get();

        booking.setTripDate(newBooking.getTripDate());
        booking.setTripTime(newBooking.getTripTime());
        booking.setFromAddress(newBooking.getFromAddress());
        booking.setToAddress(newBooking.getToAddress());
        booking.setNumberOfPeople(newBooking.getNumberOfPeople());
        booking.setDistanceInKm(newBooking.getDistanceInKm());
        booking.setTripKmPriceMiniBus(newBooking.getTripKmPriceMiniBus());
        booking.setTripKmPriceCar(newBooking.getTripKmPriceCar());
        booking.setTripPrice(newBooking.getTripPrice());
        booking.setCarType(newBooking.getCarType());
        booking.setBookingStatus(newBooking.getBookingStatus());
        bookingRepository.save(booking);
    }

    public void deleteBooking (long id){
        bookingRepository.deleteById(id);
    }

    // Is van client naar server
    private Booking transferToBooking(BookingDto dto) {
        Booking booking = new Booking();

        booking.setBookingId(dto.getBookingId());
        booking.setTripDate(dto.getTripDate());
        booking.setTripTime(dto.getTripTime());
        booking.setFromAddress(dto.getFromAddress());
        booking.setToAddress(dto.getToAddress());
        booking.setNumberOfPeople(dto.getNumberOfPeople());
        booking.setDistanceInKm(dto.getDistanceInKm());
        booking.setTripKmPriceMiniBus(dto.getTripKmPriceMiniBus());
        booking.setTripKmPriceCar(dto.getTripKmPriceCar());
        booking.setTripPrice(dto.getTripPrice());
        booking.setCarType(dto.getCarType());
        booking.setBookingStatus(dto.getBookingStatus());
        //booking.setCustomer();

        return booking;
    }

    // Is van Server naar Client
    private BookingDto transferToDto(Booking booking) {
        var dto = new BookingDto();

        dto.bookingId = booking.getBookingId();
        dto.tripDate = booking.getTripDate();
        dto.tripTime = booking.getTripTime();
        dto.fromAddress = booking.getFromAddress();
        dto.toAddress= booking.getToAddress();
        dto.numberOfPeople = booking.getNumberOfPeople();
        dto.distanceInKm = booking.getDistanceInKm();
        dto.tripKmPriceMiniBus = booking.getTripKmPriceMiniBus();
        dto.tripKmPriceCar = booking.getTripKmPriceCar();
        dto.tripPrice = booking.getTripPrice();
        dto.carType = booking.getCarType();
        dto.bookingStatus = booking.getBookingStatus();

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
