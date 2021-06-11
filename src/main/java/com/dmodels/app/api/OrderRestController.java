package com.dmodels.app.api;

import com.dmodels.app.orders.model.Order;
import com.dmodels.app.orders.model.Printout;
import com.dmodels.app.orders.service.OrderService;
import com.dmodels.app.security.User;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;



    @GetMapping
    public List<OrderResponse> findAll(@PathVariable Long orderId) {
        return orderService.findAll()
                .stream()
                .map(OrderResponse::fromOrder)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(@RequestBody @Valid CreateOrderRequest request) {
        final Order order = orderService.createOrder(request.toOrder());
        return OrderResponse.fromOrder(order);
    }


    @Data
    @Builder
    static class OrderResponse {

        private Long id;
        private User user;
        private Date orderDate;
        private Printout printout;


        static OrderResponse fromOrder(Order order) {
            return OrderResponse.builder()
                    .id(order.getId())
                    .user(order.getUser())
                    .orderDate(order.getOrderDate())
                    .build();

        }
    }

    @Data
    static class CreateOrderRequest {


        @NotNull
        private User user;
        @NotNull
        private Printout printout;


        Order toOrder() {
            Order order = new Order(
                    this.user,
                    new Date(),
                    this.printout


            );

            return order;
        }
    }
}



