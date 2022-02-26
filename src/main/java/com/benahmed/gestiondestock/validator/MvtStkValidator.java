package com.benahmed.gestiondestock.validator;

import com.benahmed.gestiondestock.DTO.MvtStkDto;

import java.util.ArrayList;
import java.util.List;

public class MvtStkValidator {
    public static List<String> validate(MvtStkDto dto) {
        List<String> errors = new ArrayList<>();
        if(dto == null){
            errors.add("Veuillez reseigner la date de mouvement");
            errors.add("Veuillez reseigner la quantité");
            errors.add("Veuillez choisir l'article");
            return errors;
        }
        if(dto.getDateMvt() == null){
            errors.add("Veuillez reseigner la date de mouvement");
        }
        if(dto.getQuantite() == null){
            errors.add("Veuillez reseigner la quantité");
        }
        if(dto.getArticle() == null){
            errors.add("Veuillez choisir l'article");
        }
        return errors;
    }
}
