package com.dmodels.app.orders.service;

import com.dmodels.app.orders.model.Customer;
import com.dmodels.app.orders.model.Order;
import com.dmodels.app.orders.model.Printout;
import com.dmodels.app.orders.repository.CustomerRepository;
import com.dmodels.app.orders.repository.OrderRepository;
import com.dmodels.app.orders.repository.PrintoutRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.nio.channels.Pipe;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final PrintoutService printoutService;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Optional<Order> findOrderById(UUID id){
        return orderRepository.findById(id);
    }

    public Order createOrUpdateOrder(Order order){
        return orderRepository.save(order);
    }
    
    public Order createOrder(UUID customerId, UUID printoutId){
        Optional<Customer> customer = customerService.findById(customerId);
        Optional<Printout> printout = printoutService.findPrintoutById(printoutId);

        Order order =  new Order(customer.get(), new Date(), printout.get());

        orderRepository.save(order);
        return order;
    }

    public Collection<Order> getOldestOrder(){
        return orderRepository.findByImplementDateIsNullOrderByOrderDate();
    }

    public Collection<Order> findNotRealized(){
        return orderRepository.findByImplementDateIsNull();
    }

    public Collection<Order> findByCustomerId(UUID customerId){
        return orderRepository.findByCustomer_Id(customerId);
    }

    public Collection<Order> findByPrintout(Printout printout){
        return orderRepository.findByToPrintedContainsOrAlreadyPrintedContains(printout, printout);
    }

    public Collection<Order> findByPrintoutMaterialPriceLessThan(Double price){
        Collection<Printout> printouts = printoutService.findByMaterialPriceLessThan(price);
        Collection<Order> results = new LinkedList<>();
        for(Printout printout : printouts){
            results.addAll(this.findByPrintout(printout));
        }
        return results;
    }

    public Collection<Order> findByPrintoutMaterialCategory(String materialCategory){
        Collection<Printout> printouts = printoutService.findByMaterialCategory(materialCategory);
        Collection<Order> results = new LinkedList<>();
        for(Printout printout : printouts){
            results.addAll(this.findByPrintout(printout));
        }
        return results;
    }

    public Collection<Order> findByPrintoutMaterialColor(String materialColor){
        Collection<Printout> printouts = printoutService.findByMaterialColor(materialColor);
        Collection<Order> results = new LinkedList<>();
        for(Printout printout : printouts){
            results.addAll(this.findByPrintout(printout));
        }
        return results;
    }
}