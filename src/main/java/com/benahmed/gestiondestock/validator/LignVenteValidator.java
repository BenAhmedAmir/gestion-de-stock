package com.benahmed.gestiondestock.validator;

import com.benahmed.gestiondestock.DTO.LigneVenteDto;

import java.util.ArrayList;
import java.util.List;

public class LignVenteValidator {
    public static List<String> validate(LigneVenteDto dto) {
        List<String> errors = new ArrayList<>();
        if(dto == null){
            errors.add("Veuillez renseigner la vente");
            errors.add("Veuillez renseigner la quantité");
            errors.add("Veuillez renseigner le prix untitaire");
            return errors;
        }
        if(dto.getVente() == null){
            errors.add("Veuillez renseigner la vente");
        }
        if(dto.getQunatite() == null){
            errors.add("Veuillez renseigner la quantité");
        }
        if(dto.getPrixUnitaire() == null){
            errors.add("Veuillez renseigner le prix untitaire");
        }

        return errors;
    }
}
