package com.dmodels.app.orders.repository;

import com.dmodels.app.orders.model.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer, UUID>  {
}
