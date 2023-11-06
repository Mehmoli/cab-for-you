package com.novi.cabforyou.repositories;

import com.novi.cabforyou.models.Cab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CabRepository extends JpaRepository<Cab, Long> {
}
