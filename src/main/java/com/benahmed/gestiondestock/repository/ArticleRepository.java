package com.benahmed.gestiondestock.repository;

import com.benahmed.gestiondestock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// ref : https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
public interface ArticleRepository extends JpaRepository<Article,Integer> {
    Optional<Article> findArticleByCodeArticle(String codeArticle);
}
