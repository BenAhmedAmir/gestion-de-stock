package com.benahmed.gestiondestock.validator;


import com.benahmed.gestiondestock.DTO.VentesDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class VentesValidator {
    public static List<String> validate(VentesDto dto) {
        List<String> errors = new ArrayList<>();
        if(dto == null){
            errors.add("Veuillez reseigner le code de vente");
            errors.add("Veuillez reseigner la date de vente");
            return errors;
        }
        if(!StringUtils.hasLength(dto.getCode())){
            errors.add("Veuillez reseigner le code de vente");
        }
        if(dto.getDateVente() == null){
            errors.add("Veuillez reseigner la date de vente");
        }
        return errors;
    }
}
