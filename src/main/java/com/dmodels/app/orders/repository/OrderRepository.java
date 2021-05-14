package com.dmodels.app.orders.repository;

import com.dmodels.app.orders.model.Order;
import com.dmodels.app.orders.model.Printout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Printout, Long> {
    Order findOrderById(long id);
}
