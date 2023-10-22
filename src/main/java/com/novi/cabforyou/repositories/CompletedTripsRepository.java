package com.novi.cabforyou.repositories;

import com.novi.cabforyou.models.CompletedTrips;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompletedTripsRepository extends JpaRepository<CompletedTrips, Long> {
}
