package com.dmodels.app.blog.repository;

import com.dmodels.app.blog.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

  Article findArticleById(long id);

}
