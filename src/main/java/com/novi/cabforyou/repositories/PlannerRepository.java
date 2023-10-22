package com.novi.cabforyou.repositories;

import com.novi.cabforyou.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlannerRepository extends JpaRepository<User, String> {
}
