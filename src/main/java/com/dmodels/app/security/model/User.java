package com.dmodels.app.security.model;

import com.dmodels.app.orders.model.Customer;
import lombok.*;

import javax.persistence.*;
import java.util.*;


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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
    private Collection<Role> roleSet = new HashSet<>();

    private Date registrationDate;
    private Date lastVisitDate;

    @OneToOne
    private Customer customer;

    public User(String username, String password, String email, Date registrationDate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    public void addRoles(Collection<Role> roles){
        this.roleSet.addAll(roles);
        for(Role role: roles){
            role.addUser(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
