package com.novi.cabforyou.repositories;

import com.novi.cabforyou.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
