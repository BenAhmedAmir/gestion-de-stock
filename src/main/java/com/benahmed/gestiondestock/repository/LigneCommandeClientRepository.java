package com.benahmed.gestiondestock.repository;

import com.benahmed.gestiondestock.model.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient,Integer> {
    List<LigneCommandeClient> findAllByCommandeClient(Integer id);
    List<LigneCommandeClient> findAllByArticleId(Integer idArticle);
}
