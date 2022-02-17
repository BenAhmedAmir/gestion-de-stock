package com.benahmed.gestiondestock.validator;

import com.benahmed.gestiondestock.DTO.EntrepriseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {
    public static List<String> validate(EntrepriseDto dto) {
        List<String> errors = new ArrayList<>();
        if(dto == null){
            errors.add("Veuillez renseigner le nom de l'entreprise");
            errors.add("Veuillez renseigner la description de l'entreprise");
            errors.add("Veuillez renseigner l'adresse de l'entreprise");
            errors.add("Veuillez renseigner le code fiscale de l'entreprise");
            errors.add("Veuillez renseigner l'Email de l'entreprise");
            errors.add("Veuillez renseigner le numero de téléphone de l'entreprise");
            errors.add("Veuillez ajouter les utilisateurs de l'entreprise");
        }
        if (!StringUtils.hasLength(dto.getName())) {
            errors.add("Veuillez renseigner le nom de l'entreprise");
        }
        if (!StringUtils.hasLength(dto.getDescription())) {
            errors.add("Veuillez renseigner la description de l'entreprise");
        }
        if (dto.getAdresse() == null) {
            errors.add("Veuillez renseigner l'adresse de l'entreprise");
        } else {
            if (!StringUtils.hasLength(dto.getAdresse().getAdresse1())) {
                errors.add("le champs 'Adresse 1' est obligatoire");
            }
            if (!StringUtils.hasLength(dto.getAdresse().getCodePostal())) {
                errors.add("le champs 'code postale' est obligatoire");
            }
            if (!StringUtils.hasLength(dto.getAdresse().getPays())) {
                errors.add("le champs 'pays' est obligatoire");
            }
            if (!StringUtils.hasLength(dto.getAdresse().getVille())) {
                errors.add("le champs 'ville' est obligatoire");
            }
        }
        if (!StringUtils.hasLength(dto.getCodeFiscal())) {
            errors.add("Veuillez renseigner le code fiscale de l'entreprise");
        }
        if (!StringUtils.hasLength(dto.getEmail())) {
            errors.add("Veuillez renseigner l'Email de l'entreprise");
        }
        if (!StringUtils.hasLength(dto.getPhoneNumber())) {
            errors.add("Veuillez renseigner le numero de téléphone de l'entreprise");
        }
        if(dto.getUtilisateurs().isEmpty()){
            errors.add("Veuillez ajouter les utilisateurs de l'entreprise");
        }
        return errors;
    }
}
