package com.benahmed.gestiondestock.service.impl;

import com.benahmed.gestiondestock.DTO.CommandeFournisseurDto;
import com.benahmed.gestiondestock.DTO.LigneCommandeFournisseurDto;
import com.benahmed.gestiondestock.exception.EntityNotFoundException;
import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.InvalidEntityException;
import com.benahmed.gestiondestock.model.Article;
import com.benahmed.gestiondestock.model.CommandeFournisseur;
import com.benahmed.gestiondestock.model.Fournisseur;
import com.benahmed.gestiondestock.model.LigneCommandeFournisseur;
import com.benahmed.gestiondestock.repository.ArticleRepository;
import com.benahmed.gestiondestock.repository.CommandeFournisseurRepository;
import com.benahmed.gestiondestock.repository.FournisseurRepository;
import com.benahmed.gestiondestock.repository.LigneCommandeFournisseurRepository;
import com.benahmed.gestiondestock.service.CommandeFournisseurService;
import com.benahmed.gestiondestock.validator.CmandeFrValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private CommandeFournisseurRepository commandeFournisseurRepository;
    private ArticleRepository articleRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private FournisseurRepository fournisseurRepository;

    @Autowired
    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository,
                                          ArticleRepository articleRepository,
                                          LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository,
                                          FournisseurRepository fournisseurRepository) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
    }


    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
        List<String> errors = CmandeFrValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("la commande nest pas valide {}", dto);
            throw new InvalidEntityException("la commande nest pas valide",
                    ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID);
        }
        // verification business
        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(dto.getFournisseur().getId());
        if (fournisseur.isEmpty()) {
            log.warn("fournisseyr avec l'id = " + dto.getFournisseur().getId() +
                    "nest pas trouvee dans la base");
            throw new InvalidEntityException("fournisseyr avec l'id = " + dto.getFournisseur().getId() +
                    "nest pas trouvee dans la base", ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }
        List<String> articleErrors = new ArrayList<>();
        if (dto.getLigneCommandeFournisseurs() != null) {
            dto.getLigneCommandeFournisseurs().forEach(lignCmndFr -> {
                if (lignCmndFr.getArticle() != null) {
                    Optional<Article> article = articleRepository.findById(lignCmndFr.getArticle().getId());
                    if (article.isEmpty()) {
                        articleErrors.add("l'article avec l'id = " + lignCmndFr.getArticle().getId() + " nexiste pas");
                    } else {
                        articleErrors.add("impossible d'entregistrer une commande avec un article NULL");
                    }
                }
            });
        }
        if (!articleErrors.isEmpty()) {
            log.warn("l'article n'existe pas");
            throw new InvalidEntityException("Article nexiste pas dans la base", ErrorCodes.ARTICLE_NOT_FOUND,
                    articleErrors);
        }

        CommandeFournisseur savedCmdFr = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(dto));
        if (dto.getLigneCommandeFournisseurs() != null) {
            dto.getLigneCommandeFournisseurs().forEach(lignCmdFr -> {
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(lignCmdFr);
                ligneCommandeFournisseur.setCommandeFournisseur(savedCmdFr);
                ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
            });
        }
        return CommandeFournisseurDto.fromEntity(savedCmdFr);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        if (id == null) {
            log.error("l'id est null");
            return null;
        }
        return commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commnade fournisseur trouvé avec l'id = " + id,
                        ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("le codeest null");
            return null;
        }
        return commandeFournisseurRepository.findCommandeFournisseurByCode(code)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commnade fournisseur trouvé avec le code = " + code,
                        ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("l'id est null");
            return;
        }
         commandeFournisseurRepository.deleteById(id);
    }
}

