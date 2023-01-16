package com.benahmed.gestiondestock.service;

import com.benahmed.gestiondestock.DTO.CommandeClientDto;
import com.benahmed.gestiondestock.DTO.LigneCommandeClientDto;
import com.benahmed.gestiondestock.model.EtatCommande;
import java.math.BigDecimal;
import java.util.List;

public interface CommandeClientService{
    CommandeClientDto save(CommandeClientDto dto);
    CommandeClientDto findById(Integer id);
    CommandeClientDto findByCode(String code);
    List<CommandeClientDto> findAll();
    void delete(Integer id);
    CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande);
    CommandeClientDto updateQuantite(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);
    CommandeClientDto updateClient(Integer idCommande, Integer idClient);
    CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande ,Integer idArticle);
    // delete article -> delete ligne commande
    CommandeClientDto deleteArticle(Integer idCommande, Integer idLigneCommande);
    List<LigneCommandeClientDto> findAllLigneCommandeClientByCommandeClient(Integer idCommande);
}
