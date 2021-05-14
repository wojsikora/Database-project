package com.dmodels.app.api;

import com.dmodels.app.blog.model.Article;
import com.dmodels.app.blog.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/articles", produces = "application/hal+json")
public class ArticleRestController {

  final ArticleRepository articleRepository; // articleService

  public ArticleRestController(@Autowired final ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  @GetMapping
  public HttpEntity<CollectionModel<Article>> all() {
    return ResponseEntity.ok(CollectionModel.of(articleRepository.findAll()));
  }

  @GetMapping("/{id}")
  public HttpEntity<EntityModel<Article>> get(@PathVariable final long id) {
    Link link = linkTo(methodOn(ArticleRestController.class).get(id)).withSelfRel();
    return ResponseEntity.ok(EntityModel.of(articleRepository.findArticleById(id)));
  }

  @PostMapping
  public HttpEntity<EntityModel<Article>> post(@RequestBody final Article articleFromRequest) {

    final Article article = new Article(articleFromRequest);
    articleRepository.save(article);
    final URI uri =
      MvcUriComponentsBuilder.fromController(getClass())
        .path("/{id}")
        .buildAndExpand(article.getId())
        .toUri();
    return ResponseEntity.created(uri).body(EntityModel.of(article));
  }

  @PutMapping("/{id}")
  public HttpEntity<EntityModel<Article>> put(@PathVariable("id") final long id, @RequestBody Article articleFromRequest) {
    final Article article = new Article(articleFromRequest);
    article.setId(id);
    articleRepository.save(article);
    final URI uri =
      MvcUriComponentsBuilder.fromController(getClass())
        .path("/{id}")
        .buildAndExpand(article.getId())
        .toUri();
    return ResponseEntity.created(uri).body(EntityModel.of(article));
  }

  @DeleteMapping("/{id}")
  public HttpEntity<?> delete(@PathVariable("id") final long id) {
    articleRepository.deleteById(id);
    return ResponseEntity.ok().build();
  }
}
