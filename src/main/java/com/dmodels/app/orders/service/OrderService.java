package com.dmodels.app.orders.service;

import com.dmodels.app.orders.model.Order;
import com.dmodels.app.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Optional<Order> findOrderById(UUID id){
        return orderRepository.findById(id);
    }

    public Order createOrUpdateOrder(Order order){
        return orderRepository.save(order);
    }

    public Order getOldestOrder(){
        return orderRepository.findByImplementDateIsNullOrderByOrderDate();
    }
}