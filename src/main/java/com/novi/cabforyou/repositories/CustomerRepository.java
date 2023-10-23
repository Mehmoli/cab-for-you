package com.novi.cabforyou.repositories;

import com.novi.cabforyou.models.Customer;
import com.novi.cabforyou.models.Driver;
import com.novi.cabforyou.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);
    void deleteByUsername(String username);
}
