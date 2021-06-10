package com.dmodels.app.api;

import com.dmodels.app.orders.model.IndividualCustomer;
import com.dmodels.app.orders.service.IndividualCustomerService;
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
@RequestMapping("/customers/individual")
@RequiredArgsConstructor
public class IndividualCustomerController {
    private final IndividualCustomerService individualCustomerService;

    @GetMapping
    public List<IndividualCustomerResponse> findAll() {
        return individualCustomerService.findAll()
                .stream()
                .map(IndividualCustomerController.IndividualCustomerResponse::fromUser)
                .collect(Collectors.toList());
    }

    @Data
    @Builder
    static class IndividualCustomerResponse {

        private UUID id;
        private String name;
        private String email;
        private String phoneNumber;

        static IndividualCustomerResponse fromUser(IndividualCustomer customer) {
            return IndividualCustomerResponse.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .email(customer.getEmail())
                    .phoneNumber(customer.getPhoneNumber()).build();
        }

    }


}
