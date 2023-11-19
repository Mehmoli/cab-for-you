package com.novi.cabforyou.services;

import com.novi.cabforyou.dtos.BookingRequestDto;
import com.novi.cabforyou.enums.BookingStatus;
import com.novi.cabforyou.enums.CarType;
import com.novi.cabforyou.models.*;
import com.novi.cabforyou.repositories.BookingRequestRepository;
import com.novi.cabforyou.repositories.CustomerRepository;
import com.novi.cabforyou.repositories.PlannerRepository;
import com.novi.cabforyou.repositories.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookingRequestServiceTest {

    @Autowired
    BookingRequestService bookingRequestService;

    @MockBean
    BookingRequestRepository bookingRequestRepository;

    @MockBean
    TripRepository tripRepository;

    @MockBean
    CustomerRepository customerRepository;

    @MockBean
    PlannerRepository plannerRepository;

    BookingRequest bookingRequest;

    Customer customer;

    Trip trip;

    @BeforeEach
    void setUp() {

        customer = new Customer();
        customer.setUsername("CabTestUser");

        trip = new Trip();
        trip.setTripId(1L);

        FromAddress fromAddress = new FromAddress("Koudstraat", "25", "3515 SS", "Utrecht");
        ToAddress toAddress = new ToAddress("Celciusstraat", "38", "1070 SS", "Amsterdam");

        bookingRequest = new BookingRequest();
        bookingRequest.setBookingId(1L);
        bookingRequest.setCustomer(customer);
        bookingRequest.setTrip(trip);
        bookingRequest.setTripDate(LocalDate.parse("2023-12-12"));
        bookingRequest.setTripTime(LocalTime.parse("18:50"));
        bookingRequest.setFromAddress(fromAddress);
        bookingRequest.setToAddress(toAddress);
        bookingRequest.setNumberOfPeople(4);
        bookingRequest.setDistanceInKm(18.50);
        bookingRequest.setKmPrice(2.65);
        bookingRequest.setTripPrice(59.50);
        bookingRequest.setCarType(CarType.SEDAN);
        bookingRequest.setBookingStatus(BookingStatus.REQUEST);
    }

    @Test
    void getAllBookings() {
        when(bookingRequestRepository.findAll()).thenReturn(new ArrayList<>());

        List<BookingRequestDto> result = bookingRequestService.getAllBookings();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void getBooking() {
        long bookingId = 1L;
        when(bookingRequestRepository.findById(bookingId)).thenReturn(Optional.of(bookingRequest));

        BookingRequestDto result = bookingRequestService.getBooking(bookingId);

        assertNotNull(result);
        assertEquals(bookingId, result.bookingId);

    }

}