package com.dmodels.app.api;

import com.dmodels.app.orders.model.Address;
import com.dmodels.app.orders.model.BusinessCustomer;
import com.dmodels.app.orders.model.IndividualCustomer;
import com.dmodels.app.orders.service.BusinessCustomerService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
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
                .map(BusinessCustomerResponse::fromCustomer)
                .collect(Collectors.toList());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BusinessCustomerController.BusinessCustomerResponse createUser(@RequestBody @Valid BusinessCustomerController.CreateBusinessCustomerRequest request){
        final BusinessCustomer customer = businessCustomerService.createCustomer(request.toBusinessCustomer());
        return BusinessCustomerController.BusinessCustomerResponse.fromCustomer(customer);
    }


    @Data
    @Builder
    static class BusinessCustomerResponse {

        private UUID id;
        private String name;
        private String email;
        private String phoneNumber;

        static BusinessCustomerResponse fromCustomer(BusinessCustomer customer) {
            return BusinessCustomerResponse.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .email(customer.getEmail())
                    .phoneNumber(customer.getPhoneNumber()).build();
        }

    }

    @Data
    static class CreateBusinessCustomerRequest {

        @NotNull
        private String name;

        @NotNull
        @Pattern(regexp = "[0-9]{10}+")
        private String tin;

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

        @NotNull
        @Positive
        private Long flatNumber;

        BusinessCustomer toBusinessCustomer() {

            return new BusinessCustomer(
                    this.name,
                    this.tin,
                    this.email,
                    this.phoneNumber,
                    new Address(this.city, this.postalCode, this.street, this.streetNumber, this.flatNumber)
            );
        }
    }

}
