package com.dmodels.app.blog.model;

import com.dmodels.app.security.model.User;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
public class Article {
  @Id
  @GeneratedValue
  private Long id;

  @OneToOne
  private User author;
  private String content;
}
