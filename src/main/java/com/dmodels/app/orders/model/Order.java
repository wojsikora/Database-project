package com.dmodels.app.orders.model;


import com.dmodels.app.security.model.User;
import lombok.Data;
import com.dmodels.app.orders.model.Printout;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private Customer customer;
    private Date orderDate;
    private Date implementDate;
    private Date realImplementDate;

    @OneToMany
    private Set<Printout> toPrinted = new LinkedHashSet<>();
    @OneToMany
    private Set<Printout> alreadyPrinted = new LinkedHashSet<>();

    public Order(Customer customer, Date orderDate, Printout printout)
    {
        this.customer = customer;
        this.orderDate = orderDate;
        this.implementDate = null;
        this.toPrinted.add(printout);
    }
}


