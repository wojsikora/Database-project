package com.dmodels.app.orders.model;


import lombok.Data;


import javax.persistence.*;



@Data
@Embeddable
public class Material {


    //@Enumerated(EnumType.STRING)
    private String category;
    private String color;
    private Double price;

    public Material( String category, String color, Double price)
    {

        this.category = category;
        this.color = color;
        this.price = price;
    }

    public Material() {

    }
}
