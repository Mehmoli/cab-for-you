package com.novi.cabforyou.controllers;


import com.novi.cabforyou.dtos.BookingDto;
import com.novi.cabforyou.services.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cabforyou/bookings")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }


    @GetMapping("")
    public ResponseEntity<List<BookingDto>> getAllBookings() {

        List<BookingDto> dtos = bookingService.getAllBookings();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}") //Now OK
    public ResponseEntity<BookingDto> getBooking(@PathVariable("id") long id) {
        BookingDto dto = bookingService.getBooking(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("")
    public ResponseEntity<BookingDto> addBooking(@RequestBody BookingDto dto) {
        BookingDto dto1 = bookingService.addBooking(dto);
        return ResponseEntity.created(null).body(dto1);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BookingDto> updateBooking(@PathVariable("id") long id, @RequestBody BookingDto dto) {

        bookingService.updateBooking(id, dto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteBooking(@PathVariable("id") long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }


}
