package com.dmodels.app.orders.model;

import com.dmodels.app.security.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import com.dmodels.app.orders.model.Printout;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;
    private Date orderDate;
    private Date implementDate;
    private Date realImplementDate;
    @OneToOne
    private Printout printout;
    @OneToMany
    private Set<Printout> toPrinted = new LinkedHashSet<>();
    @OneToMany
    private Set<Printout> alreadyPrinted = new LinkedHashSet<>();

    public Order(User user, Date orderDate, Printout printout)
    {
        this.id = id;
        this.user = user;
        this.orderDate = orderDate;
        this.implementDate = null;
        //this.realImplementDate = null;
        this.printout = printout;
    }




}


