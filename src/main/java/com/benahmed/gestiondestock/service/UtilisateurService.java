package com.benahmed.gestiondestock.service;

import com.benahmed.gestiondestock.DTO.UtilisateurDto;
import com.benahmed.gestiondestock.model.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    UtilisateurDto save(UtilisateurDto dto);
    UtilisateurDto findById(Integer id);
    List<UtilisateurDto> findAll();
    void delete(Integer id);
    UtilisateurDto findUtilisateurByEmail(String email);
}
