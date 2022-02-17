package com.benahmed.gestiondestock.validator;


import com.benahmed.gestiondestock.DTO.ClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {
    public static List<String> validate(ClientDto clientDto) {
        List<String> errors = new ArrayList<>();
        if(clientDto == null){
            errors.add("Veuillez renseigner le prenom de client");
            errors.add("Veuillez renseigner le nom de client");
            errors.add("Veuillez renseigner l'Email de client");
            errors.add("Veuillez renseigner le numero de téléphone de client");
            return errors;
        }
        if (!StringUtils.hasLength(clientDto.getName())) {
            errors.add("Veuillez renseigner le prenom de client");
        }
        if (!StringUtils.hasLength(clientDto.getFamilyName())) {
            errors.add("Veuillez renseigner le nom de client");
        }
        if (!StringUtils.hasLength(clientDto.getMail())) {
            errors.add("Veuillez renseigner l'Email de client");
        }
        if (!StringUtils.hasLength(clientDto.getPhoneNumber())) {
            errors.add("Veuillez renseigner le numero de téléphone de client");
        }
        return errors;
    }
}
