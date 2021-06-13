package com.dmodels.app.orders.repository;

import com.dmodels.app.orders.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findById(UUID id);
}
