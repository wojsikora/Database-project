package com.dmodels.app.api;

import com.dmodels.app.orders.model.BusinessCustomer;
import com.dmodels.app.orders.service.BusinessCustomerService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers/business")
@RequiredArgsConstructor
public class BusinessCustomerController {

    private final BusinessCustomerService businessCustomerService;

    @GetMapping
    public List<BusinessCustomerResponse> findAll() {
        return  businessCustomerService.findAll()
                .stream()
                .map(BusinessCustomerResponse::fromUser)
                .collect(Collectors.toList());
    }

    @Data
    @Builder
    static class BusinessCustomerResponse {

        private UUID id;
        private String name;
        private String email;
        private String phoneNumber;

        static BusinessCustomerResponse fromUser(BusinessCustomer customer) {
            return BusinessCustomerResponse.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .email(customer.getEmail())
                    .phoneNumber(customer.getPhoneNumber()).build();
        }

    }


}
