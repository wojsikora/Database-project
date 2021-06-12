package com.dmodels.app.orders.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @NotNull
    private String city;

    @NotNull
    private String postalCode;

    @NotNull
    private String street;

    @Positive
    private Long streetNumber;

    @Positive
    private Long flatNumber;
}
