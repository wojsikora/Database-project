package com.dmodels.app.orders.service;

import com.dmodels.app.orders.repository.IndividualCustomerRepository;
import com.dmodels.app.orders.model.Customer;
import com.dmodels.app.orders.model.IndividualCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;



@Service
@RequiredArgsConstructor
public class IndividualCustomerService {

    private final IndividualCustomerRepository individualCustomerRepository;

    public List<IndividualCustomer> findAll() {
        return individualCustomerRepository.findAll();
    }

    public Optional<IndividualCustomer> findById(UUID id) {
        return individualCustomerRepository.findById(id);
    }

    public IndividualCustomer createCustomer(IndividualCustomer customer) {
        return individualCustomerRepository.save(customer);
    }
}
