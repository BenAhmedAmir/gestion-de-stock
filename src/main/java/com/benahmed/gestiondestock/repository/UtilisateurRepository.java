package com.benahmed.gestiondestock.repository;

import com.benahmed.gestiondestock.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {
}
