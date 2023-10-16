package com.novi.cabforyou.repositories;

import com.novi.cabforyou.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Long, Booking> {
}
