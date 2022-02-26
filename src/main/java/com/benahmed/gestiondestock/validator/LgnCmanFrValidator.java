package com.benahmed.gestiondestock.validator;

import com.benahmed.gestiondestock.DTO.LigneCommandeClientDto;

import java.util.ArrayList;
import java.util.List;

public class LgnCmanFrValidator {
    public static List<String> validate(LigneCommandeClientDto dto) {
        List<String> errors = new ArrayList<>();
        if (dto == null) {
            errors.add("Veuillez choisir un artilce");
            errors.add("Veuillez choisir la commande");
            errors.add("Veuillez renseigner la quantité");
            errors.add("Veuillez renseigner le prix unitaire ");
            return errors;
        }
        if (dto.getArticle() == null) {
            errors.add("Veuillez choisir un artilce");
        }
        if (dto.getCommandeClient() == null) {
            errors.add("Veuillez choisir la commande");
        }
        if (dto.getQuantite() == null) {
            errors.add("Veuillez renseigner la quantité");
        }
        if (dto.getPrixUnitaire() == null) {
            errors.add("Veuillez renseigner le prix unitaire ");
        }

        return errors;
    }
}
