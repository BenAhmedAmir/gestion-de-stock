package com.benahmed.gestiondestock.DTO;

import com.benahmed.gestiondestock.model.Adresse;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AdresseDto {
    private String adresse1;
    private String adresse2;
    private String ville;
    private String codePostal;
    private String pays;
    public static AdresseDto fromEntity(Adresse adresse){
        if(adresse == null){
            // TODO throw an exception
            return null;
        }
        return AdresseDto.builder()
                .adresse1(adresse.getAdresse1())
                .adresse2(adresse.getAdresse2())
                .codePostal(adresse.getCodePostal())
                .ville(adresse.getVille())
                .pays(adresse.getPays())
                .build();

    }
    public static Adresse toEntity(AdresseDto adresseDto){
        if (adresseDto == null){
            // TODO throw an exception
            return null;
        }
        // pour mapper vers l'entity on va instancier la classe pour faire un set pour chaque attribut
        Adresse adresse = new Adresse();
        adresse.setAdresse1(adresseDto.getAdresse1());
        adresse.setAdresse2(adresseDto.getAdresse2());
        adresse.setCodePostal(adresseDto.getCodePostal());
        adresse.setVille(adresseDto.getVille());
        adresse.setPays(adresseDto.getPays());
        return adresse;
    }
}
