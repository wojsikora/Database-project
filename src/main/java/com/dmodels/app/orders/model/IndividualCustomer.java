package com.dmodels.app.orders.model;

import lombok.*;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndividualCustomer extends Customer {
    private String name;
    private String surname;

    public IndividualCustomer(String name, String surname, String email, String phoneNumber, Address address){
        super(email, phoneNumber, address);
        this.name = name;
        this.surname = surname;
    }
}
