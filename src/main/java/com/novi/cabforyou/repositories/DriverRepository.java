package com.novi.cabforyou.repositories;

import com.novi.cabforyou.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByUsername(String username);
    void deleteByUsername(String username);
}
