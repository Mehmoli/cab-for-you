package com.novi.cabforyou.repositories;

import com.novi.cabforyou.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
