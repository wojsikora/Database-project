package com.dmodels.app.orders.service;

import com.dmodels.app.orders.model.Order;
import com.dmodels.app.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findOrderById(long id){
        return orderRepository.findOrderById(id);
    }




    public Order createOrder(Order order){
        return orderRepository.save(order);
    }


}
