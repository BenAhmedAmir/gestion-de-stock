package com.benahmed.gestiondestock.repository;

import com.benahmed.gestiondestock.model.LigneCommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeFournisseurRepository extends JpaRepository<LigneCommandeFournisseur,Integer> {
    List<LigneCommandeFournisseur> findAllByCommandeFournisseur(Integer id);
    List<LigneCommandeFournisseur> findAllByArticleId(Integer idArticle);

}
