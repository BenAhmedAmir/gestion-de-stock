package com.benahmed.gestiondestock.repository;

import com.benahmed.gestiondestock.model.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeClientRepository extends JpaRepository<Integer, LigneCommandeClient> {
}
