package com.benahmed.gestiondestock.DTO;

import com.benahmed.gestiondestock.model.Ventes;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
@Data
public class VentesDto {
    private Integer id;
    private String code;
    private String commenatire;
    private Instant dateVente;
    public static VentesDto fromEntity(Ventes ventes){

    }
    public static Ventes toEntity(VentesDto ventesDto){

    }
}
