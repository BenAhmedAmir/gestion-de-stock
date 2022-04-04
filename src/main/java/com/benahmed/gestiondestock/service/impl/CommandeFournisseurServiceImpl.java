package com.benahmed.gestiondestock.service.impl;

import com.benahmed.gestiondestock.DTO.*;
import com.benahmed.gestiondestock.exception.EntityNotFoundException;
import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.InvalidEntityException;
import com.benahmed.gestiondestock.exception.InvalidOperationException;
import com.benahmed.gestiondestock.model.*;
import com.benahmed.gestiondestock.repository.ArticleRepository;
import com.benahmed.gestiondestock.repository.CommandeFournisseurRepository;
import com.benahmed.gestiondestock.repository.FournisseurRepository;
import com.benahmed.gestiondestock.repository.LigneCommandeFournisseurRepository;
import com.benahmed.gestiondestock.service.CommandeFournisseurService;
import com.benahmed.gestiondestock.service.MvtStockService;
import com.benahmed.gestiondestock.validator.CmandeFrValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private final CommandeFournisseurRepository commandeFournisseurRepository;
    private final ArticleRepository articleRepository;
    private final LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private final FournisseurRepository fournisseurRepository;
    private final MvtStockService mvtStockService;

    @Autowired
    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository,
                                          ArticleRepository articleRepository,
                                          LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository,
                                          FournisseurRepository fournisseurRepository, MvtStockService mvtStockService) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.mvtStockService = mvtStockService;
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
        List<LigneCommandeFournisseur> ligneCommandeFournisseurs = ligneCommandeFournisseurRepository.findAllByCommandeFournisseur(id);
        if(!ligneCommandeFournisseurs.isEmpty()){
            throw new InvalidOperationException("impossible de supprimer une commande fournisseur deja utilise",
                    ErrorCodes.COMMANDE_FOURNISSEUR_ALREADY_IN_USE);
        }
         commandeFournisseurRepository.deleteById(id);
    }

    @Override
    public CommandeFournisseurDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        checkIdCommande(idCommande);
        if(!StringUtils.hasLength(String.valueOf(etatCommande))){
            log.error("l'etat commande est vide");
            throw new InvalidOperationException("Impossible de modifier l'etat de commande avec etat vide",
                    ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIABLE);
        }
        CommandeFournisseurDto commandeFournisseurDto = checkEtatCommande(idCommande);
        commandeFournisseurDto.setEtatCommande(etatCommande);
        CommandeFournisseur savedCommande = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(
                commandeFournisseurDto));
        if(commandeFournisseurDto.isCommandLivree()){
            updateMvtStk(idCommande);
        }
        return CommandeFournisseurDto.fromEntity(savedCommande);
    }

    @Override
    public CommandeFournisseurDto updateQuantite(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
        checkIdCommande(idCommande);
        if(quantite== null || quantite.compareTo(BigDecimal.ZERO) == 0){
            log.error("quantite null ou egal zero");
            throw new InvalidOperationException("Impossible de modifier la quantite avec quantite null ou zero",
                    ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIABLE);
        }
        checkIdLigneCommande(idLigneCommande);
        CommandeFournisseurDto commandeFournisseurDto = checkEtatCommande(idCommande);
        Optional<LigneCommandeFournisseur> optionaLigneCommande  = findLigneCommande(idLigneCommande);
        LigneCommandeFournisseur ligneCommandeFournisseur = optionaLigneCommande.get();
        ligneCommandeFournisseur.setQuantite(quantite);
        ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
        return commandeFournisseurDto;
    }

    @Override
    public CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur) {
        checkIdCommande(idCommande);
        if(idFournisseur == null){
            log.error("id fournisseur est null");
            throw new InvalidOperationException("Impossible de modifier le fournssieur avec un id null",
                    ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIABLE);
        }
        CommandeFournisseurDto commandeFournisseurDto = checkEtatCommande(idCommande);
        Optional<Fournisseur> optionalFournisseur = fournisseurRepository.findById(idFournisseur);
        if(optionalFournisseur.isEmpty()){
            throw new EntityNotFoundException("Aucun fournisseur a ete trouve dans la base",
                    ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }
        commandeFournisseurDto.setFournisseur(FournisseurDto.fromEntity(optionalFournisseur.get()));
        return CommandeFournisseurDto.fromEntity(commandeFournisseurRepository.save(
                CommandeFournisseurDto.toEntity(commandeFournisseurDto)
        ));
    }

    @Override
    public CommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        CommandeFournisseurDto commandeFournisseurDto = checkEtatCommande(idCommande);
        findLigneCommande(idLigneCommande);
        ligneCommandeFournisseurRepository.deleteById(idCommande);
        return commandeFournisseurDto;
    }

    @Override
    public List<LigneCommandeFournisseurDto> findAllLigneCommandeFournisseurByCommandeFournisseur(Integer idCommande) {
        return ligneCommandeFournisseurRepository.findAllByCommandeFournisseur(idCommande).stream()
                .map(LigneCommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    private void checkIdCommande(Integer idCommande){
        if (idCommande == null) {
            log.error("l'id commande est null");
            throw new InvalidOperationException("impossible de modifier l'etat avec un Id commande null",
                    ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIABLE);
        }
    }
    private void checkIdLigneCommande(Integer idLigneCommande){
        if (idLigneCommande == null ) {
            log.error("id ligne commande est null");
            throw new InvalidOperationException("impossible de modifier letat de la commande avec id ligne commande",
                    ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }
    }
    private void checkIdArticle(Integer idArticle){
        if (idArticle == null ) {
            log.error("id de nouveau article   est null");
            throw new InvalidOperationException("impossible de modifier letat de la commande avec un nouveau id ",
                    ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIABLE);
        }
    }
    private CommandeFournisseurDto checkEtatCommande(Integer idCommande){
        CommandeFournisseurDto commandeFournisseurDto = findById(idCommande);
        if(commandeFournisseurDto.isCommandLivree()){
            throw  new InvalidOperationException("impossible de modifier letat de la commande lorsque elle est livre",
                    ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIABLE);
        }
        return commandeFournisseurDto;
    }
    private Optional<LigneCommandeFournisseur> findLigneCommande(Integer idLigneCommande){
        Optional<LigneCommandeFournisseur> optionalLigneCommandeFournisseur =
                ligneCommandeFournisseurRepository.findById(idLigneCommande);

        if(optionalLigneCommandeFournisseur.isEmpty()){
            throw new InvalidEntityException("Aucun ligne commande a ete trouve dans la base avec l'id "
                    + idLigneCommande + ErrorCodes.LIGNE_COMMANDE_FOURNISSEUR_NOT_FOUND);
        }
        return optionalLigneCommandeFournisseur;
    }
    private void updateMvtStk(Integer idCommande){
        List<LigneCommandeFournisseur> ligneCommandeFournisseurs = ligneCommandeFournisseurRepository.findAllByCommandeFournisseur(idCommande);
        ligneCommandeFournisseurs.forEach(ligne -> {
            MvtStkDto mvtStkDto = MvtStkDto.builder()
                    .article(ArticleDto.fromEntity(ligne.getArticle()))
                    .dateMvt(Instant.now())
                    .typeMvt(TypeMvt.ENTREE)
                    .sourceMvtStk(SourceMvtStk.COMMANDE_FOURNISSEUR)
                    .quantite(ligne.getQuantite())
                    .idEntreprise(ligne.getIdEntreprise())
                    .build();
            mvtStockService.entreeStock(mvtStkDto);
        });
    }
}

