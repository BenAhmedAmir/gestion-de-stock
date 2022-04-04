package com.benahmed.gestiondestock.service.impl;

import com.benahmed.gestiondestock.DTO.*;
import com.benahmed.gestiondestock.exception.EntityNotFoundException;
import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.InvalidEntityException;
import com.benahmed.gestiondestock.exception.InvalidOperationException;
import com.benahmed.gestiondestock.model.LigneCommandeClient;
import com.benahmed.gestiondestock.model.LigneCommandeFournisseur;
import com.benahmed.gestiondestock.model.LigneVente;
import com.benahmed.gestiondestock.repository.ArticleRepository;
import com.benahmed.gestiondestock.repository.LigneCommandeClientRepository;
import com.benahmed.gestiondestock.repository.LigneCommandeFournisseurRepository;
import com.benahmed.gestiondestock.repository.LigneVenteRepository;
import com.benahmed.gestiondestock.service.ArticleService;
import com.benahmed.gestiondestock.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private final LigneCommandeClientRepository ligneCommandeClientRepository;
    private final LigneVenteRepository ligneVenteRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository,
                              LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository,
                              LigneCommandeClientRepository ligneCommandeClientRepository,
                              LigneVenteRepository ligneVenteRepository) {
        this.articleRepository = articleRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override
    public ArticleDto save(ArticleDto dto) {
        // validation
        List<String> errors = ArticleValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Article n'est pas valide {}", dto);
            throw new InvalidEntityException("l'Article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }
        return ArticleDto.fromEntity(
                articleRepository.save(ArticleDto.toEntity(dto)));
    }

    @Override
    public ArticleDto findById(Integer id) {
        if (id == null) {
            log.error("id est null");
            return null;
        }
        return articleRepository.findById(id)
                .map(ArticleDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Aucun article est trouvé avec l'id = " + id + "dans la base",
                        ErrorCodes.ARTICLE_NOT_FOUND
                ));
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        if (!StringUtils.hasLength(codeArticle)) {
            log.error("Article code est null");
            return null;
        }
        return articleRepository.findArticleByCodeArticle(codeArticle)
                .map(ArticleDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Aucun article est trouvé avec le code = " + codeArticle + "dans la base",
                        ErrorCodes.ARTICLE_NOT_FOUND
                ));
    }


    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                // method reference ::
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("id est null");
            return;
        }
        List<LigneCommandeClient> ligneCommandeClients = ligneCommandeClientRepository.findAllByArticleId(id);
        if(!ligneCommandeClients.isEmpty()){
            throw new InvalidOperationException("Impossible de supprimer un article deja utilise dans commande clients",
                    ErrorCodes.ARTICLE_ALREADY_IN_USE);
        }
        List<LigneCommandeFournisseur> ligneCommandeFournisseurs = ligneCommandeFournisseurRepository.findAllByArticleId(id);
        if(!ligneCommandeClients.isEmpty()){
            throw new InvalidOperationException("Impossible de supprimer un article deja utilise dans commande fournisseur",
                    ErrorCodes.ARTICLE_ALREADY_IN_USE);
        }
        List<LigneVente> ligneVentes = ligneVenteRepository.findAllByArticleId(id);
        if(!ligneCommandeClients.isEmpty()){
            throw new InvalidOperationException("Impossible de supprimer un article deja utilise dans ligne vente",
                    ErrorCodes.ARTICLE_ALREADY_IN_USE);
        }
        articleRepository.deleteById(id);
    }

    @Override
    public List<LigneVenteDto> findHistoriqueVentes(Integer idArticle) {
        return ligneVenteRepository.findAllByArticleId(idArticle).stream()
                .map(LigneVenteDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeClientDto> findHistotriqueCommandeClient(Integer idArticle) {
        return ligneCommandeClientRepository.findAllByCommandeClient(idArticle).stream()
                .map(LigneCommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle) {
        return ligneCommandeFournisseurRepository.findAllByCommandeFournisseur(idArticle).stream()
                .map(LigneCommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> findAllArticleByIdCategory(Integer idCategory) {
        return articleRepository.findAllByCategoryId(idCategory).stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }
}
