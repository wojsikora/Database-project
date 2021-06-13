package com.dmodels.app.orders.repository;

import com.dmodels.app.orders.model.Order;
import com.dmodels.app.orders.model.Printout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    Optional<Order> findById(UUID id);

    Collection<Order> findByImplementDateIsNullOrderByOrderDate();

    Collection<Order> findByImplementDateIsNull();

    Collection<Order> findByCustomer_Id(UUID customerId);

    Collection<Order> findByToPrintedContainsOrAlreadyPrintedContains(Printout p1, Printout p2);
}