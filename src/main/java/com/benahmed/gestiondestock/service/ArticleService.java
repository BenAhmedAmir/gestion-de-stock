package com.benahmed.gestiondestock.service;

import com.benahmed.gestiondestock.DTO.ArticleDto;

import java.util.List;

// pour definir un contrat de service
public interface ArticleService {
    ArticleDto save(ArticleDto dto);
    ArticleDto findById(Integer id);
    ArticleDto findByCodeArticle(String codeArticle);
    List<ArticleDto> findAll();
    void delete(Integer id);
}
