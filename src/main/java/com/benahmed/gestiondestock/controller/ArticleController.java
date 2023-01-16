package com.benahmed.gestiondestock.controller;

import com.benahmed.gestiondestock.DTO.ArticleDto;
import com.benahmed.gestiondestock.DTO.LigneCommandeClientDto;
import com.benahmed.gestiondestock.DTO.LigneCommandeFournisseurDto;
import com.benahmed.gestiondestock.DTO.LigneVenteDto;
import com.benahmed.gestiondestock.controller.api.ArticleApi;
import com.benahmed.gestiondestock.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController  implements ArticleApi {
    private final ArticleService articleService;
    // creer auto des instances a chaque fois au il trouve cette anno
    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public ArticleDto save(ArticleDto dto) {
        return articleService.save(dto);
    }

    @Override
    public ArticleDto findById(Integer id) {
        return articleService.findById(id);
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        return articleService.findByCodeArticle(codeArticle);
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleService.findAll();
    }

    @Override
    public void delete(Integer id) {
        articleService.delete(id);
    }

    @Override
    public List<LigneVenteDto> findHistoriqueVentes(Integer idArticle) {
        return articleService.findHistoriqueVentes(idArticle);
    }

    @Override
    public List<LigneCommandeClientDto> findHistotriqueCommandeClient(Integer idArticle) {
        return articleService.findHistotriqueCommandeClient(idArticle);
    }

    @Override
    public List<LigneCommandeFournisseurDto> findHistotriqueCommandeFournisseur(Integer idArticle) {
        return articleService.findHistoriqueCommandeFournisseur(idArticle);
    }

    @Override
    public List<ArticleDto> findAllArticleByIdCategory(Integer idCategroy) {
        return articleService.findAllArticleByIdCategory(idCategroy);
    }
}
