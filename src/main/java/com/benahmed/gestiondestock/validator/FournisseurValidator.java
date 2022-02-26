package com.benahmed.gestiondestock.validator;

import com.benahmed.gestiondestock.DTO.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {
    public static List<String> validate(FournisseurDto fournisseurDto) {
        List<String> errors = new ArrayList<>();
        if(fournisseurDto == null){
            errors.add("Veuillez renseigner le prenom de fournisseur");
            errors.add("Veuillez renseigner le nom de fournisseur");
            errors.add("Veuillez renseigner l'Email de fournisseur");
            errors.add("Veuillez renseigner le numero de téléphone de fournisseur");
            return errors;
        }
        if (!StringUtils.hasLength(fournisseurDto.getName())) {
            errors.add("Veuillez renseigner le prenom de fournisseur");
        }
        if (!StringUtils.hasLength(fournisseurDto.getFamilyName())) {
            errors.add("Veuillez renseigner le nom de fournisseur");
        }
        if (!StringUtils.hasLength(fournisseurDto.getMail())) {
            errors.add("Veuillez renseigner l'Email de fournisseur");
        }
        if (!StringUtils.hasLength(fournisseurDto.getPhoneNumber())) {
            errors.add("Veuillez renseigner le numero de téléphone de fournisseur");
        }
        return errors;
    }
}
