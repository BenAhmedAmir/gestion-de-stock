package com.benahmed.gestiondestock.service;

import com.benahmed.gestiondestock.DTO.ArticleDto;
import com.benahmed.gestiondestock.DTO.LigneCommandeClientDto;
import com.benahmed.gestiondestock.DTO.LigneCommandeFournisseurDto;
import com.benahmed.gestiondestock.DTO.LigneVenteDto;

import java.util.List;

// pour definir un contrat de service
public interface ArticleService {
    ArticleDto save(ArticleDto dto);
    ArticleDto findById(Integer id);
    ArticleDto findByCodeArticle(String codeArticle);
    List<ArticleDto> findAll();
    void delete(Integer id);
    List<LigneVenteDto> findHistoriqueVentes(Integer idArticle);
    List<LigneCommandeClientDto> findHistotriqueCommandeClient(Integer idArticle);
    List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle);
    List<ArticleDto> findAllArticleByIdCategory(Integer idCategory);

}
