package com.novi.cabforyou.repositories;

import com.novi.cabforyou.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
