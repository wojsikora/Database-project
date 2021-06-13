package com.dmodels.app.orders.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRINTERS")
public class Printer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true)
    private String name;

    @Embedded
    Vector3D plateDimensions;

    @ElementCollection(targetClass = MaterialCategory.class)
    @Enumerated
    private Collection<MaterialCategory> materials = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="PRINTER_ID")
    private Collection<Plate> plates = new HashSet<>();

    public Printer(String name, Vector3D plateDimensions, Collection<MaterialCategory> materials){
        this.name = name;
        this.plateDimensions = plateDimensions;
        this.materials.addAll(materials);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Printer)) return false;
        Printer printer = (Printer) o;
        return Objects.equals(id, printer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
