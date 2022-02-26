package com.benahmed.gestiondestock.repository;

import com.benahmed.gestiondestock.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntrepriseRepository extends JpaRepository<Entreprise,Integer> {

    Optional<Entreprise> findByCodeFiscal(String code);
}
