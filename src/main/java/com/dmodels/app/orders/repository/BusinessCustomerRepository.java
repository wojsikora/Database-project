package com.dmodels.app.orders.repository;

import com.dmodels.app.orders.model.BusinessCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BusinessCustomerRepository extends JpaRepository<BusinessCustomer, UUID> {
}
