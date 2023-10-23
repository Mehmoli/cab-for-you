package com.novi.cabforyou.repositories;

import com.novi.cabforyou.models.Planner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlannerRepository extends JpaRepository<Planner, Long> {
    Optional<Planner> findByUsername(String username);
    void deleteByUsername(String username);
}
