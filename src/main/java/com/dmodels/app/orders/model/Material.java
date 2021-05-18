package com.dmodels.app.orders.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@Embeddable
@Table(name = "MATERIALS")
public class Material {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
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
