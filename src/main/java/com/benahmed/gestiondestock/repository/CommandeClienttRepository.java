package com.benahmed.gestiondestock.repository;

import com.benahmed.gestiondestock.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandeClienttRepository extends JpaRepository<CommandeClient,Integer> {
    Optional<CommandeClient> findCommandeClientByCode(String code);
}
