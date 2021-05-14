package com.dmodels.app.orders.model;

import com.dmodels.app.security.User;
import lombok.Data;
import com.dmodels.app.orders.model.Printout;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@Table(name = "Order")
public class Order {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;
    private Date orderDate;
    private Date implementDate;
    private Date realImplementDate;
    @OneToOne
    private Printout printout;

    public Order(Long id, User user, Date orderDate, Date implementDate, Date realImplementDate, Printout printout)
    {
        this.id = id;
        this.user = user;
        this.orderDate = orderDate;
        this.implementDate = implementDate;
        this.realImplementDate = realImplementDate;
        this.printout = printout;
    }




}


