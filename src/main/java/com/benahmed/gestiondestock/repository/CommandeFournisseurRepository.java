package com.benahmed.gestiondestock.repository;

import com.benahmed.gestiondestock.model.CommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeFournisseurRepository extends JpaRepository<Integer, CommandeFournisseur> {
}
