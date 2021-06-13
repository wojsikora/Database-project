package com.dmodels.app.orders.model;

import com.dmodels.app.orders.model.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "customers")
public abstract class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String email;
    private String phoneNumber;

    @Embedded
    private Address address;

    public Customer(String email, String phoneNumber, Address address) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

}
