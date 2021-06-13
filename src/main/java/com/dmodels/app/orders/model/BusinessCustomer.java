package com.dmodels.app.orders.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "business_customers")
public class BusinessCustomer extends Customer {
    private String name;

    @Column(unique = true)
    private String tin;

    public BusinessCustomer(String name, String tin, String email, String phoneNumber, Address address){
        super(email, phoneNumber, address);
        this.name = name;
        this.tin = tin;
    }
}
