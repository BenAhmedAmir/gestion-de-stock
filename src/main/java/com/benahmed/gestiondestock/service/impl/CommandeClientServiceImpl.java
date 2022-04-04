package com.benahmed.gestiondestock.service.impl;

import com.benahmed.gestiondestock.DTO.*;
import com.benahmed.gestiondestock.exception.EntityNotFoundException;
import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.InvalidEntityException;
import com.benahmed.gestiondestock.exception.InvalidOperationException;
import com.benahmed.gestiondestock.model.*;
import com.benahmed.gestiondestock.repository.ArticleRepository;
import com.benahmed.gestiondestock.repository.ClientRepository;
import com.benahmed.gestiondestock.repository.CommandeClienttRepository;
import com.benahmed.gestiondestock.repository.LigneCommandeClientRepository;
import com.benahmed.gestiondestock.service.CommandeClientService;
import com.benahmed.gestiondestock.service.MvtStockService;
import com.benahmed.gestiondestock.validator.ArticleValidator;
import com.benahmed.gestiondestock.validator.CmandeCltValidator;
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
public class CommandeClientServiceImpl implements CommandeClientService {

    private final ArticleRepository articleRepository;
    private final CommandeClienttRepository commandeClientRepository;
    private final LigneCommandeClientRepository ligneCommandeClientRepository;
    private final ClientRepository clientRepository;
    private final MvtStockService mvtStockService;

    @Autowired
    public CommandeClientServiceImpl(
            ArticleRepository articleRepository, CommandeClienttRepository commandeClienttRepository
            , LigneCommandeClientRepository ligneCommandeClientRepository, ClientRepository clientRepository, MvtStockService mvtStockService) {
        this.articleRepository = articleRepository;
        this.commandeClientRepository = commandeClienttRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.clientRepository = clientRepository;
        this.mvtStockService = mvtStockService;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {
        List<String> errors = CmandeCltValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("la commande n'est pas valide {}", dto);
            throw new InvalidEntityException("la commande client  n'est pas valide ",
                    ErrorCodes.LIGNE_COMMANDE_CLIENT_NOT_VALID);
        }

        if (dto.getId() != null && dto.isCommandeLivree()) {
            throw new InvalidOperationException("Impossible de modifier la commande lorsque elle est livree",
                    ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }

        Optional<Client> client = clientRepository.findById(dto.getClient().getId());
        if (client.isEmpty()) {
            log.warn("client avec l'id = " + dto.getClient().getId() + " n'est pas trouvé dans la base");
            throw new InvalidEntityException("client avec l'id = " + dto.getClient().getId() +
                    " n'est pas trouvé dans la base"
                    , ErrorCodes.CLIENT_NOT_FOUND);
        }
        List<String> articleErrors = new ArrayList<>();
        if (dto.getLigneCommandeClients() != null) {
            dto.getLigneCommandeClients().forEach(lignCmdClt -> {
                if (lignCmdClt.getArticle() != null) {
                    Optional<Article> article = articleRepository.findById(lignCmdClt.getArticle().getId());
                    if (article.isEmpty()) {
                        articleErrors.add("l'article avec l'id " + lignCmdClt.getArticle().getId() + "n'existe pas");
                    }
                } else {
                    articleErrors.add("impossible d'entregistrer une commande avec un article NULL");
                }
            });
        }
        if (!articleErrors.isEmpty()) {
            log.warn("l'article n'existe pas");
            throw new InvalidEntityException("Article nexiste pas dans la base",
                    ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }
        CommandeClient savedCmdClient = commandeClientRepository.save(CommandeClientDto.toEntity(dto));
        if (dto.getLigneCommandeClients() != null) {
            dto.getLigneCommandeClients().forEach(lignCmdClt -> {
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(lignCmdClt);
                ligneCommandeClient.setCommandeClient(savedCmdClient);
                ligneCommandeClientRepository.save(ligneCommandeClient);
            });
        }
        return CommandeClientDto.fromEntity(savedCmdClient);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        if (id == null) {
            log.error("l'id est null");
            return null;
        }

        return commandeClientRepository.findById(id)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun commande client nest trouve avec l'id "
                        + id + ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("le code est null");
            return null;
        }
        return commandeClientRepository.findCommandeClientByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun commande client nest trouve avec le code "
                        + code + ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("l'id est null");
            return;
        }
        List<LigneCommandeClient> ligneCommandeClients = ligneCommandeClientRepository.findAllByCommandeClient(id);
        if(!ligneCommandeClients.isEmpty()){
            throw new InvalidOperationException("impossible de supprimer une commande client deja utilise",
                    ErrorCodes.COMMANDE_CLIENT_ALREADY_IN_USE);
        }
        commandeClientRepository.deleteById(id);
    }

    @Override
    public CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        checkIdCommande(idCommande);
        if (!StringUtils.hasLength(String.valueOf(etatCommande))) {
            log.error("letat commande est vide");
            throw new InvalidOperationException("impossible de modifier letat de la commande avec un etat commande" +
                    "vide",
                    ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }
        CommandeClientDto commandeClientDto = checkEtatCommande(idCommande);
        commandeClientDto.setEtatCommande(etatCommande);
        CommandeClient commandeClient = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto));
        if(commandeClientDto.isCommandeLivree()){
            updateMvtStk(idCommande);
        }
        return CommandeClientDto.fromEntity(commandeClient);
    }

    @Override
    public CommandeClientDto updateQuantite(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
        checkIdCommande(idCommande);
        if (quantite == null || quantite.compareTo(BigDecimal.ZERO) == 0 ) {
            log.error("quantite null ou egal a zero");
            throw new InvalidOperationException("impossible de modifier la quantite avec quantite null ou zero",
                    ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }
        checkIdLigneCommande(idLigneCommande);
        CommandeClientDto commandeClientDto = checkEtatCommande(idCommande);


        Optional<LigneCommandeClient> ligneCommandeClientOptional = findLigneCommande(idLigneCommande);
        LigneCommandeClient ligneCommandeClient = ligneCommandeClientOptional.get();
        ligneCommandeClient.setQuantite(quantite);
        ligneCommandeClientRepository.save(ligneCommandeClient);
        return commandeClientDto;
    }

    @Override
    public CommandeClientDto updateClient(Integer idCommande, Integer idClient) {
        checkIdCommande(idCommande);
        if (idClient == null) {
            log.error("id client null");
            throw new InvalidOperationException("impossible de modifier le client avec un Id null",
                    ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }
        CommandeClientDto commandeClientDto = checkEtatCommande(idCommande);
        Optional<Client> optionClient = clientRepository.findById(idClient);
        if(optionClient.isEmpty()){
            throw new EntityNotFoundException("Aucun client a ete trouve avec l'id "+ idClient ,
                    ErrorCodes.CLIENT_NOT_FOUND);
        }
        commandeClientDto.setClient(ClientDto.fromEntity(optionClient.get()));
        return CommandeClientDto.fromEntity(commandeClientRepository.save(
                CommandeClientDto.toEntity(commandeClientDto)));
    }

    @Override
    public CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande,Integer idArticle) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        checkIdArticle(idArticle);

        CommandeClientDto commandeClientDto = checkEtatCommande(idCommande);

        Optional<LigneCommandeClient> ligneCommandeClient = findLigneCommande(idLigneCommande);
        Optional<Article> article = articleRepository.findById(idArticle);
        if(article.isEmpty()){
            throw new InvalidEntityException("Aucun artile a ete trouve dans la base avec l'id "
                    + idLigneCommande + ErrorCodes.ARTICLE_NOT_FOUND);
        }

        List <String> errors = ArticleValidator.validate(ArticleDto.fromEntity(article.get()));

        if (!errors.isEmpty()){
            throw new InvalidEntityException("Article invalide", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }
        LigneCommandeClient ligneCommandeClientToSave = ligneCommandeClient.get();
        ligneCommandeClientToSave.setArticle(article.get());
        ligneCommandeClientRepository.save(ligneCommandeClientToSave);

        return commandeClientDto;
    }

    @Override
    public CommandeClientDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        CommandeClientDto commandeClientDto = checkEtatCommande(idCommande);
        // c juste pour verif ligne commande et pour informer le client en cas il est absent;
        findLigneCommande(idLigneCommande);
        ligneCommandeClientRepository.deleteById(idCommande);
        return commandeClientDto;
    }

    @Override
    public List<LigneCommandeClientDto> findAllLigneCommandeClientByCommandeClient(Integer idCommande) {
        return ligneCommandeClientRepository.findAllByCommandeClient(idCommande).stream()
                .map(LigneCommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    private void checkIdCommande(Integer idCommande){
        if (idCommande == null) {
            log.error("l'id commande est null");
            throw new InvalidOperationException("impossible de modifier leta avec un Id commande null",
                    ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
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
                    ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }
    }
    private CommandeClientDto checkEtatCommande(Integer idCommande){
        CommandeClientDto commandeClientDto = findById(idCommande);
        if(commandeClientDto.isCommandeLivree()){
            throw  new InvalidOperationException("impossible de modifier letat de la commande lorsque elle est livre",
                    ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }
        return commandeClientDto;
    }
    private Optional<LigneCommandeClient> findLigneCommande(Integer idLigneCommande){
        Optional<LigneCommandeClient> ligneCommandeClientOptional = ligneCommandeClientRepository.findById(idLigneCommande);

        if(ligneCommandeClientOptional.isEmpty()){
            throw new InvalidEntityException("Aucun ligne commande a ete trouve dans la base avec l'id "
                    + idLigneCommande + ErrorCodes.LIGNE_COMMANDE_CLIENT_NOT_FOUND);
        }
        return ligneCommandeClientOptional;
    }
    private void updateMvtStk(Integer idCommande){
        List<LigneCommandeClient> ligneCommandeClients = ligneCommandeClientRepository.findAllByCommandeClient(idCommande);
        ligneCommandeClients.forEach(ligne -> {
            MvtStkDto mvtStkDto = MvtStkDto.builder()
                    .article(ArticleDto.fromEntity(ligne.getArticle()))
                    .dateMvt(Instant.now())
                    .typeMvt(TypeMvt.SORTIE)
                    .sourceMvtStk(SourceMvtStk.COMMANDE_CLIENT)
                    .quantite(ligne.getQuantite())
                    .idEntreprise(ligne.getIdEntreprise())
                    .build();
            mvtStockService.sortieStock(mvtStkDto);
        });
    }
}

