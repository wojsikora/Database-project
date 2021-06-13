package com.dmodels.app.orders.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@Table(name = "PRINTOUTS")
public class Printout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Embedded
    private Material material;
    //private Double layerThickness;
    //private Double wallThickness;
    private String filling;
    private String resolution;
    private Boolean permission;
    private Vector3D dimensions;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "printouts")
    private Collection<Plate> plates = new HashSet<>();

    @OneToOne
    private File file;

    public Printout(Material material, String filling, String resolution, Boolean permission, Vector3D dimensions)
    {
        //this.id = id;
        this.material = material;
        this.filling = filling;
        this.resolution = resolution;
        this.permission = permission;
        this.dimensions = dimensions;
        this.file = null;
    }
}
