package com.dmodels.app.orders.service;

import com.dmodels.app.orders.model.Customer;
import com.dmodels.app.orders.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    Optional<Customer> findById(UUID id){
        return customerRepository.findById(id);
    }
}
