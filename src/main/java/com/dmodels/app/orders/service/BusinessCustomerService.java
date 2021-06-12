package com.dmodels.app.orders.service;

import com.dmodels.app.orders.repository.BusinessCustomerRepository;
import com.dmodels.app.orders.model.Customer;
import com.dmodels.app.orders.model.BusinessCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BusinessCustomerService {
    private final BusinessCustomerRepository businessCustomerRepository;

    public List<BusinessCustomer> findAll() {
        return businessCustomerRepository.findAll();
    }

    public Optional<BusinessCustomer> findById(UUID id) {
        return businessCustomerRepository.findById(id);
    }

    public BusinessCustomer createCustomer(BusinessCustomer customer) {
        return businessCustomerRepository.save(customer);
    }
}
