package com.novi.cabforyou.repositories;

import com.novi.cabforyou.models.BookingRequest;
import com.novi.cabforyou.models.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRequestRepository extends JpaRepository<BookingRequest, Long> {
    List<BookingRequest> findByBookingStatus(BookingStatus bookingStatus);
}
