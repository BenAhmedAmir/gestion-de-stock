package com.benahmed.gestiondestock.validator;

import com.benahmed.gestiondestock.DTO.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {
    public static List<String> validate(UtilisateurDto utilisateurDto) {
        List<String> errors = new ArrayList<>();
        if(utilisateurDto == null){
            errors.add("Veuillez renseigner le prenom d'tulisateur");
            errors.add("Veuillez renseigner le nom d'tulisateur");
            errors.add("Veuillez renseigner le mot de passe d'tulisateur");
            errors.add("Veuillez renseigner l'adresse d'tulisateur");
            return errors;
        }
        if (!StringUtils.hasLength(utilisateurDto.getName())) {
            errors.add("Veuillez renseigner le prenom d'tulisateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getFamilyName())) {
            errors.add("Veuillez renseigner le nom d'tulisateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getPassword())) {
            errors.add("Veuillez renseigner le mot de passe d'tulisateur");
        }
        if(utilisateurDto.getDateDeNaissance() == null){
            errors.add("Veuillez renseigner la date de naissance d'tulisateur");
        }
        if (utilisateurDto.getAdresse() == null) {
            errors.add("Veuillez renseigner l'adresse d'tulisateur");
        } else {
            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getAdresse1())) {
                errors.add("le champs 'Adresse 1' est obligatoire");
            }
            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getCodePostal())) {
                errors.add("le champs 'code postale' est obligatoire");
            }
            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getPays())) {
                errors.add("le champs 'pays' est obligatoire");
            }
            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getVille())) {
                errors.add("le champs 'ville' est obligatoire");
            }
        }
        return errors;
    }
}
