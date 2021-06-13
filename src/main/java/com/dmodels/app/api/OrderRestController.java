package com.dmodels.app.api;

import com.dmodels.app.orders.model.Customer;
import com.dmodels.app.orders.model.Order;
import com.dmodels.app.orders.model.Printout;
import com.dmodels.app.orders.service.OrderService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;



    @GetMapping
    public List<OrderResponse> findAll() {
        return orderService.findAll()
                .stream()
                .map(OrderResponse::fromOrder)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(@RequestBody @Valid CreateOrderRequest request) {
        List<UUID> ids = request.toIdsList();
        final Order order = orderService.createOrder(ids.get(0), ids.get(1));
        return OrderResponse.fromOrder(order);
    }


    @Data
    @Builder
    static class OrderResponse {

        private UUID id;
        private Customer customer;
        private Date orderDate;
        private Printout printout;


        static OrderResponse fromOrder(Order order) {
            return OrderResponse.builder()
                    .id(order.getId())
                    .customer(order.getCustomer())
                    .orderDate(order.getOrderDate())
                    .printout(order.getToPrinted().iterator().next())
                    .build();

        }
    }

    @Data
    static class CreateOrderRequest {


//        @NotNull
//        private Customer customer;
//        @NotNull
//        private Printout printout;

        @NotNull
        private UUID customerId;

        @NotNull
        private UUID printoutId;

        List<UUID> toIdsList(){
            List<UUID> ids =  new LinkedList<UUID>();
            ids.add(customerId);
            ids.add(printoutId);
            return ids;
        }

//        Order toOrder() {
//
//            return new Order(
//                    this.customer,
//                    new Date(),
//                    this.printout
//
//
//            );
//        }
    }
}