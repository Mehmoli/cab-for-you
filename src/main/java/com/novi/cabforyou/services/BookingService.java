package com.novi.cabforyou.services;


import com.novi.cabforyou.dtos.BookingDto;
import com.novi.cabforyou.exceptions.RecordNotFoundException;
import com.novi.cabforyou.models.Booking;
import com.novi.cabforyou.repositories.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private BookingRepository bookingRepository;

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
        bookingRepository.save(booking);
        return bookingDto;
    }

    public void updateBooking (Long id, BookingDto newBooking){
        if(!bookingRepository.existsById(id)) throw new RecordNotFoundException();
        Booking booking = bookingRepository.findById(id).get();
        booking.setTripDate(newBooking.getTripDate());
        booking.setTripTime(newBooking.getTripTime());
        booking.setFromStreet(newBooking.getFromStreet());
        booking.setFromHouseNumber(newBooking.getFromHouseNumber());
        booking.setFromPostalCode(newBooking.getFromPostalCode());
        booking.setFromCity(newBooking.getFromCity());
        booking.setToStreet(newBooking.getToStreet());
        booking.setToHouseNumber(newBooking.getToHouseNumber());
        booking.setToPostalCode(newBooking.getToPostalCode());
        booking.setToCity(newBooking.getToCity());
        booking.setNumberOfPeople(newBooking.getNumberOfPeople());
        booking.setDistanceInKm(newBooking.getDistanceInKm());
        booking.setPrice(newBooking.getPrice());
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
        booking.setFromStreet(dto.getFromStreet());
        booking.setFromHouseNumber(dto.getFromHouseNumber());
        booking.setFromPostalCode(dto.getFromPostalCode());
        booking.setFromCity(dto.getFromCity());
        booking.setToStreet(dto.getToStreet());
        booking.setToHouseNumber(dto.getToHouseNumber());
        booking.setToPostalCode(dto.getToPostalCode());
        booking.setToCity(dto.getToCity());
        booking.setNumberOfPeople(dto.getNumberOfPeople());
        booking.setDistanceInKm(dto.getDistanceInKm());
        booking.setPrice(dto.getPrice());
        booking.setCarType(dto.getCarType());
        booking.setBookingStatus(dto.getBookingStatus());
        //booking.setCustomer();

        return booking;
    }

//// Is van Server naar Client
    private BookingDto transferToDto(Booking booking) {
        var dto = new BookingDto();

        dto.bookingId = booking.getBookingId();
        dto.tripDate = booking.getTripDate();
        dto.tripTime = booking.getTripTime();
        dto.fromStreet = booking.getFromStreet();
        dto.fromHouseNumber = booking.getFromHouseNumber();
        dto.fromPostalCode = booking.getFromPostalCode();
        dto.fromCity = booking.getFromCity();
        dto.toStreet = booking.getToStreet();
        dto.toHouseNumber = booking.getToHouseNumber();
        dto.toPostalCode = booking.getToPostalCode();
        dto.toCity = booking.getToCity();
        dto.numberOfPeople = booking.getNumberOfPeople();
        dto.distanceInKm = booking.getDistanceInKm();
        dto.price = booking.getPrice();
        dto.carType = booking.getCarType();
        dto.bookingStatus = booking.getBookingStatus();

        return dto;
    }

}
