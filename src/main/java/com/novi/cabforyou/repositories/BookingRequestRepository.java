package com.novi.cabforyou.repositories;

import com.novi.cabforyou.models.BookingRequest;
import com.novi.cabforyou.models.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRequestRepository extends JpaRepository<BookingRequest, Long> {
    Optional<BookingRequest> findByBookingStatus(BookingStatus bookingStatus);
}
