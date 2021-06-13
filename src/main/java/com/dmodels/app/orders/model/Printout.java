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
    private String filling;
    private Boolean permission;
    private Vector3D dimensions;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "printouts")
    private Collection<Plate> plates = new HashSet<>();

    @OneToOne
    private File file;

    public Printout(Material material, String filling, Boolean permission, Vector3D dimensions)
    {
        //this.id = id;
        this.material = material;
        this.filling = filling;
        this.permission = permission;
        this.dimensions = dimensions;
        this.file = null;
    }
}
