package com.novi.cabforyou.repositories;

import com.novi.cabforyou.models.BookingRequest;
import com.novi.cabforyou.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<BookingRequest, Long> {
    Optional<BookingRequest> findByBookingStatus(BookingStatus bookingStatus);
}
