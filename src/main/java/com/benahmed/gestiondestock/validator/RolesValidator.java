package com.benahmed.gestiondestock.validator;

import com.benahmed.gestiondestock.DTO.RolesDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RolesValidator {
    public static List<String> validate(RolesDto dto) {
        List<String> errors = new ArrayList<>();
        if(dto == null){
            errors.add("Veuillez reseigner le nom de role");
            errors.add("Veuillez choisir l'utilisateur");
            return errors;
        }
        if(!StringUtils.hasLength(dto.getRoleName())) {
            errors.add("Veuillez reseigner le nom de role");
        }
        if(dto.getUtilisateur() == null){
            errors.add("Veuillez choisir l'utilisateur");
        }

        return errors;
    }

}
