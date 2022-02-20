package com.benahmed.gestiondestock.repository;

import com.benahmed.gestiondestock.model.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VentesRepository extends JpaRepository<Ventes,Integer> {

    Optional<Ventes> findByVenteCode(String code);
}
