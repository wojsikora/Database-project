package com.dmodels.app.blog.model;

import com.dmodels.app.security.model.User;
import lombok.Data;
import lombok.Generated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
public class Comment {

  @Id
  @Generated
  private Long id;

  @OneToOne
  private User author;
  private String content;

  @OneToOne
  private Article article;
}
