package com.benahmed.gestiondestock.repository;

import com.benahmed.gestiondestock.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeClienttRepository extends JpaRepository<Integer, CommandeClient> {
}
