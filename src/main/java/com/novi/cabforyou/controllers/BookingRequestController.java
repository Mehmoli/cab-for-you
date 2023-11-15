package com.novi.cabforyou.controllers;

import com.novi.cabforyou.dtos.BookingRequestDto;
import com.novi.cabforyou.dtos.OnCreateValidation;
import com.novi.cabforyou.dtos.OnUpdateValidation;
import com.novi.cabforyou.models.BookingRequest;
import com.novi.cabforyou.models.BookingStatus;
import com.novi.cabforyou.services.BookingRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingRequestController {

    private final BookingRequestService bookingRequestService;

    public BookingRequestController(BookingRequestService bookingRequestService) {
        this.bookingRequestService = bookingRequestService;
    }

    @GetMapping("")
    public ResponseEntity<List<BookingRequestDto>> getAllBookings() {

        List<BookingRequestDto> dtos = bookingRequestService.getAllBookings();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingRequestDto> getBooking(@PathVariable("id") long id) {
        BookingRequestDto dto = bookingRequestService.getBooking(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("")
    public ResponseEntity<BookingRequestDto> addBooking(@Validated(OnCreateValidation.class) @RequestBody BookingRequestDto dto) {
        BookingRequestDto dto1 = bookingRequestService.addBooking(dto);
        return ResponseEntity.created(null).body(dto1);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BookingRequestDto> updateBooking(@Validated(OnUpdateValidation.class) @PathVariable("id") long id, @RequestBody BookingRequestDto dto) {

        bookingRequestService.updateBooking(id, dto);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookingRequestDto> patchBookingRequest(@PathVariable("id") Long id, @RequestBody BookingRequestDto updatedBookingRequestDto) {
        BookingRequestDto updatedDto = bookingRequestService.patchBookingRequest(id, updatedBookingRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteBooking(@PathVariable("id") long id) {
        bookingRequestService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    // STATUS
    @GetMapping("/status/{status}")
    public ResponseEntity<List<BookingRequestDto>> getBookingRequestsByStatus(@PathVariable("status") BookingStatus status) {
        List<BookingRequest> bookings = bookingRequestService.getBookingRequestsByStatus(status);
        List<BookingRequestDto> bookingRequestDto = bookings.stream().map(BookingRequestDto::transferToBookingRequestDto).toList();
        return ResponseEntity.status(HttpStatus.OK).body(bookingRequestDto);
    }
}
