package com.dmodels.app.security.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.UUID;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true)
    private String username;
    private String password;

    @Column(unique = true)
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@ToString.Exclude
    private Collection<Role> roleSet = new HashSet<>();

    private Date registrationDate;
    private Date lastVisitDate;

    public User(String username, String password, String email, Date registrationDate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    public void addRoles(Collection<Role> roles){
        roles.forEach(role -> this.roleSet.add(role));
    }
}
