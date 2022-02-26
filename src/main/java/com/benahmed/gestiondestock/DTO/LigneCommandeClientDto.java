package com.benahmed.gestiondestock.DTO;

import com.benahmed.gestiondestock.model.LigneCommandeClient;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Builder
@Data
public class LigneCommandeClientDto {
    private Integer id;
    private ArticleDto article;
    private CommandeClientDto commandeClient;
    private BigDecimal quantite;
    private BigDecimal prixUnitaire;
    public static LigneCommandeClientDto fromEntity(LigneCommandeClient ligneCommandeClient){
        if(ligneCommandeClient ==null){
            return null;
        }
        return LigneCommandeClientDto.builder()
                .id(ligneCommandeClient.getId())
                .article(ArticleDto.fromEntity(ligneCommandeClient.getArticle()))
                .quantite(ligneCommandeClient.getQuantite())
                .prixUnitaire(ligneCommandeClient.getPrixUnitaire())
                .build();
    }
    public static LigneCommandeClient toEntity(LigneCommandeClientDto ligneCommandeClientDto){
        if(ligneCommandeClientDto == null){
            // TODO throw an exception
            return null;
        }
        LigneCommandeClient  ligneCommandeClient = new LigneCommandeClient();
        ligneCommandeClient.setId(ligneCommandeClientDto.getId());
        ligneCommandeClient.setArticle(ArticleDto.toEntity(ligneCommandeClientDto.getArticle()));
        ligneCommandeClient.setCommandeClient(CommandeClientDto.toEntity(ligneCommandeClientDto.getCommandeClient()));
        ligneCommandeClient.setQuantite(ligneCommandeClientDto.getQuantite());
        ligneCommandeClient.setPrixUnitaire(ligneCommandeClient.getPrixUnitaire());
        return ligneCommandeClient;
    }

}
