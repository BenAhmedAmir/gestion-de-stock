package com.benahmed.gestiondestock.service;

import com.benahmed.gestiondestock.DTO.CommandeFournisseurDto;
import com.benahmed.gestiondestock.model.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeFournisseurService {
    CommandeFournisseurDto save(CommandeFournisseurDto dto);
    CommandeFournisseurDto findById(Integer id);
    CommandeFournisseurDto findByCode(String code);
    List<CommandeFournisseurDto> findAll();
    void delete(Integer id);
    CommandeFournisseurDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande);
    CommandeFournisseurDto updateQuantite(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);
    CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur);

}
