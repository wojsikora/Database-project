package com.dmodels.app.orders.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@Table(name = "Printout")
public class Printout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Material material;
    private Double layerThickness;
    private Double wallThickness;
    private Boolean permission;
    //plik stl

    public Printout(Long id, Material material, Double layerThickness, Double wallThickness, Boolean permission)
    {
        this.id = id;
        this.material = material;
        this.layerThickness = layerThickness;
        this.wallThickness = wallThickness;
        this.permission = permission;
    }
}
