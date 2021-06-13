package com.dmodels.app.api;

import com.dmodels.app.orders.model.Address;
import com.dmodels.app.orders.model.IndividualCustomer;
import com.dmodels.app.orders.service.IndividualCustomerService;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.*;
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
                .map(IndividualCustomerController.IndividualCustomerResponse::fromCustomer)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IndividualCustomerResponse createUser(@RequestBody @Valid IndividualCustomerController.CreateIndividualCustomerRequest request){
        final IndividualCustomer customer = individualCustomerService.createCustomer(request.toIndividualCustomer());
        return IndividualCustomerController.IndividualCustomerResponse.fromCustomer(customer);
    }

    @Data
    @Builder
    static class IndividualCustomerResponse {

        private UUID id;
        private String name;
        private String email;
        private String phoneNumber;

        static IndividualCustomerResponse fromCustomer(IndividualCustomer customer) {
            return IndividualCustomerResponse.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .email(customer.getEmail())
                    .phoneNumber(customer.getPhoneNumber()).build();
        }

    }

    @Data
    static class CreateIndividualCustomerRequest {

        @NotNull
        private String name;

        @NotNull
        private String surname;

        @NotNull
        @Pattern(regexp = ".*@.*")
        private String email;

        @NotNull
        @Pattern(regexp = "[0-9]{9}+")
        private String phoneNumber;

        @NotNull
        private String city;

        @NotNull
        private String postalCode;

        @NotNull
        private String street;

        @NotNull
        @Positive
        private Long streetNumber;

        @Positive
        private Long flatNumber;

        IndividualCustomer toIndividualCustomer() {

            return new IndividualCustomer(
                    this.name,
                    this.surname,
                    this.email,
                    this.phoneNumber,
                    new Address(this.city, this.postalCode, this.street, this.streetNumber, this.flatNumber)
            );
        }
    }
}
