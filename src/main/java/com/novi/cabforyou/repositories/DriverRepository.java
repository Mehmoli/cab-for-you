package com.novi.cabforyou.repositories;

import com.novi.cabforyou.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    void deleteByUsername(String username);

    Optional<Driver> findByUsername(String username);

    boolean existsByUsername(String username);
}
