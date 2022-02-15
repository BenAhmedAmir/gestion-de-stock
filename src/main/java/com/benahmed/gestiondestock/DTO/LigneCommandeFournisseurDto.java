package com.benahmed.gestiondestock.DTO;

import com.benahmed.gestiondestock.model.LigneCommandeFournisseur;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class LigneCommandeFournisseurDto {
    private Integer id;
    private ArticleDto article;
    private CommandeFournisseurDto commandeFournisseur;
    private BigDecimal quantite;
    private BigDecimal prixUnitaire;
    public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur){
        if(ligneCommandeFournisseur == null){
            // TODO throw an exception
            return null;
        }
        return LigneCommandeFournisseurDto.builder()
                .id(ligneCommandeFournisseur.getId())
                .article(ArticleDto.fromEntity(ligneCommandeFournisseur.getArticle()))
                .commandeFournisseur(CommandeFournisseurDto.fromEntity(ligneCommandeFournisseur.getCommandeFournisseur()))
                .quantite(ligneCommandeFournisseur.getQuantite())
                .prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())
                .build();
    }

    public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto ligneDto){
        if(ligneDto == null){
            // TODO throw an exception
            return null;
        }
        LigneCommandeFournisseur linCommandFournisseur = new LigneCommandeFournisseur();
        linCommandFournisseur.setId(ligneDto.getId());
        linCommandFournisseur.setArticle(ArticleDto.toEntity(ligneDto.getArticle()));
        linCommandFournisseur.setCommandeFournisseur(CommandeFournisseurDto.toEntity(ligneDto.getCommandeFournisseur()));
        linCommandFournisseur.setQuantite(ligneDto.getQuantite());
        linCommandFournisseur.setPrixUnitaire(ligneDto.getPrixUnitaire());
        return linCommandFournisseur;
    }
}
