package com.novi.cabforyou.repositories;

import com.novi.cabforyou.models.Booking;
import com.novi.cabforyou.models.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByBookingStatus(BookingStatus bookingStatus);
}
