package com.dmodels.app.orders.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    private String type;

    @Lob
    private byte[] data;

    public File(String name, String type, byte[] data)
    {
        this.name = name;
        this.type = type;
        this.data = data;
    }



}
