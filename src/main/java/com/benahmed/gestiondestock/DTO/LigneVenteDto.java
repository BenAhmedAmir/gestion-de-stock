package com.benahmed.gestiondestock.DTO;

import com.benahmed.gestiondestock.model.LigneVente;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class LigneVenteDto {
    private Integer id;
    private VentesDto vente;
    private BigDecimal qunatite;
    private BigDecimal prixUnitaire;

    public static LigneVenteDto fromEntity(LigneVente ligneVente){
        if(ligneVente == null){
            // TODO throw an exception
            return null;
        }
        return LigneVenteDto.builder()
                .id(ligneVente.getId())
                .vente(VentesDto.fromEntity(ligneVente.getVente()))
                .qunatite(ligneVente.getQunatite())
                .prixUnitaire(ligneVente.getPrixUnitaire())
                .build();
    }

    public static LigneVente toEntity(LigneVenteDto ligneVenteDto){
        if(ligneVenteDto == null){
            // TODO throw an exception
            return null;
        }
        LigneVente ligneVente = new LigneVente();
        ligneVente.setId(ligneVenteDto.getId());
        ligneVente.setVente(VentesDto.toEntity(ligneVenteDto.getVente()));
        ligneVente.setQunatite(ligneVenteDto.getQunatite());
        ligneVente.setPrixUnitaire(ligneVenteDto.getPrixUnitaire());
        return ligneVente;
    }
}
