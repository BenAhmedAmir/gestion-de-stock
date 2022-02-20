package com.benahmed.gestiondestock.DTO;

import com.benahmed.gestiondestock.model.Ventes;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
@Data
@Builder
public class VentesDto {
    private Integer id;
    private String venteCode;
    private String commenatire;
    private Instant dateVente;
    public static VentesDto fromEntity(Ventes ventes){
    if(ventes==null){
        // TODO throw an exception
        return null;
    }
    return VentesDto.builder()
            .id(ventes.getId())
            .venteCode(ventes.getVenteCode())
            .commenatire(ventes.getCommenatire())
            .dateVente(ventes.getDateVente())
            .build();
    }
    public static Ventes toEntity(VentesDto ventesDto){
        if(ventesDto==null){
            // TODO throw an exception
        }
        Ventes ventes = new Ventes();
        ventes.setId(ventesDto.getId());
        ventes.setVenteCode(ventesDto.getVenteCode());
        ventes.setCommenatire(ventesDto.getCommenatire());
        ventes.setDateVente(ventesDto.getDateVente());
        return ventes;
    }
}
