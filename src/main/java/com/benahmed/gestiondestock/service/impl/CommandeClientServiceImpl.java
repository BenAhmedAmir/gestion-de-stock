package com.benahmed.gestiondestock.service.impl;

import com.benahmed.gestiondestock.DTO.CommandeClientDto;
import com.benahmed.gestiondestock.DTO.LigneCommandeClientDto;
import com.benahmed.gestiondestock.exception.EntityNotFoundException;
import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.InvalidEntityException;
import com.benahmed.gestiondestock.model.Article;
import com.benahmed.gestiondestock.model.Client;
import com.benahmed.gestiondestock.model.CommandeClient;
import com.benahmed.gestiondestock.model.LigneCommandeClient;
import com.benahmed.gestiondestock.repository.ArticleRepository;
import com.benahmed.gestiondestock.repository.ClientRepository;
import com.benahmed.gestiondestock.repository.CommandeClienttRepository;
import com.benahmed.gestiondestock.repository.LigneCommandeClientRepository;
import com.benahmed.gestiondestock.service.CommandeClientService;
import com.benahmed.gestiondestock.validator.CmandeCltValidator;
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
public class CommandeClientServiceImpl implements CommandeClientService {

    private ArticleRepository articleRepository;
    private CommandeClienttRepository commandeClienttRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;
    private ClientRepository clientRepository;
    @Autowired
    public CommandeClientServiceImpl(
            ArticleRepository articleRepository, CommandeClienttRepository commandeClienttRepository
            , LigneCommandeClientRepository ligneCommandeClientRepository, ClientRepository clientRepository) {
        this.articleRepository = articleRepository;
        this.commandeClienttRepository = commandeClienttRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {
        List<String> errors = CmandeCltValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("la commande n'est pas valide {}", dto);
            throw new InvalidEntityException("la commande client  n'est pas valide ",
                    ErrorCodes.LIGNE_COMMANDE_CLIENT_NOT_VALID);
        }
        Optional<Client> client = clientRepository.findById(dto.getClient().getId());
        if(client.isEmpty()){
            log.warn("client avec l'id = " + dto.getClient().getId() + " n'est pas trouvé dans la base" );
            throw new InvalidEntityException("client avec l'id = " + dto.getClient().getId() +
                    " n'est pas trouvé dans la base"
                    , ErrorCodes.CLIENT_NOT_FOUND);
        }
        List<String> articleErrors = new ArrayList<>();
        if(dto.getLigneCommandeClients()!= null){
            dto.getLigneCommandeClients().forEach(lignCmdClt ->{
                if (lignCmdClt.getArticle() != null){
                    Optional<Article> article = articleRepository.findById(lignCmdClt.getArticle().getId());
                    if(article.isEmpty()){
                        articleErrors.add("l'article avec l'id "+ lignCmdClt.getArticle().getId() + "n'existe pas");
                    }
                }else {
                    articleErrors.add("l'article avec l'id "+ lignCmdClt.getArticle().getId() + "n'existe pas");
                }
            });
        }
        if(!articleErrors.isEmpty()){
            log.warn("l'article n'existe pas");
            throw new InvalidEntityException("Article nexiste pas dans la base", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }
        CommandeClient savedCmdClient = commandeClienttRepository.save(CommandeClientDto.toEntity(dto));
        if(dto.getLigneCommandeClients() != null){
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
        if(id == null){
            log.error("l'id est null");
            return null;
        }

        return commandeClienttRepository.findById(id)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucun commande client nest trouve avec l'id " + id +  ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if(!StringUtils.hasLength(code)){
            log.error("le code est null");
            return null;
        }
        return commandeClienttRepository.findCommandeClientByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucun commande client nest trouve avec le code " + code +  ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClienttRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("l'id est null");
            return;
        }
        commandeClienttRepository.deleteById(id);
    }
}
