package com.novi.cabforyou.repositories;

import com.novi.cabforyou.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
