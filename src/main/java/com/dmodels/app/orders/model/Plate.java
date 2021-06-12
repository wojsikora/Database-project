package com.dmodels.app.orders.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Plates")
public class Plate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<Printout> printouts = new LinkedList<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Printer printer;

    private Date generationDate;

    public Plate(Printer printer, Collection<Printout> printouts){
        this.printer = printer;
        this.printouts.addAll(printouts);
        this.generationDate = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plate)) return false;
        Plate plate = (Plate) o;
        return Objects.equals(id, plate.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
