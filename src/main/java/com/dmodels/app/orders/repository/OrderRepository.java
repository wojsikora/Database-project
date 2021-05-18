package com.dmodels.app.orders.repository;

import com.dmodels.app.orders.model.Order;
import com.dmodels.app.orders.model.Printout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderById(long id);
}
