package com.dmodels.app.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
  @Id
  @GeneratedValue
  private Long id;

  private String content;

  private String title;

  public Article(Article fromArticle) {
    this.id = fromArticle.id;
    this.content = fromArticle.content;
    this.title = fromArticle.title;
  }
}
