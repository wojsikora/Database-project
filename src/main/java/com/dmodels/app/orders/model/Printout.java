package com.dmodels.app.orders.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@Table(name = "PRINTOUTS")
public class Printout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private Material material;
    //private Double layerThickness;
    //private Double wallThickness;
    private String filling;
    private String resolution;
    private Boolean permission;


    public Printout(Material material, String filling, String resolution, Boolean permission)
    {
        //this.id = id;
        this.material = material;
        this.filling = filling;
        this.resolution = resolution;
        this.permission = permission;
    }
}
