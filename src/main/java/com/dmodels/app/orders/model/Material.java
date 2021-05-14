package com.dmodels.app.orders.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
@Data
@NoArgsConstructor
public class Material {
    @Id
    @GeneratedValue
    private Long id;


    private String category;
    private String color;
    private Double price;

    public Material(Long id, String category, String color, Double price)
    {
        this.id = id;
        this.category = category;
        this.color = color;
        this.price = price;
    }

}
