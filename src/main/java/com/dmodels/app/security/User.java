package com.dmodels.app.security;

import com.dmodels.app.orders.model.Order;
import lombok.Data;
import lombok.Generated;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class User {
  @Id
  @Generated
  private Long id;

  private String login;
  private String email;
  private String nickname;
  private String password;

  @OneToMany()
  private List<Order> orders = new ArrayList<>();

}
