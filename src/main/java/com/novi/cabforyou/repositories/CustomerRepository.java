package com.novi.cabforyou.repositories;

import com.novi.cabforyou.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Long, Customer> {
}
