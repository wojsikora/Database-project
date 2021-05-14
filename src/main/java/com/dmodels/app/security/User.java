package com.dmodels.app.security;

import lombok.Data;
import lombok.Generated;

import javax.persistence.Entity;
import javax.persistence.Id;

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

}
