package com.benahmed.gestiondestock.validator;

import com.benahmed.gestiondestock.DTO.ClientDto;
import com.benahmed.gestiondestock.DTO.CommandeClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CmandeCltValidator {
        public static List<String> validate(CommandeClientDto dto) {
            List<String> errors = new ArrayList<>();
            if(dto == null){
                errors.add("Veuillez renseigner le code de la commande client");
                errors.add("Veuillez renseigner la date de commande");
                errors.add("Veuillez selectioner le client");
                errors.add("Veuillez choisir la ligne commande");
                return errors;
            }
            if(!StringUtils.hasLength(dto.getCode())){
                errors.add("Veuillez renseigner le code de la commande client");
            }
            if(dto.getDateCommande() == null){
                errors.add("Veuillez renseigner la date de commande");
            }
            if(dto.getClient() == null){
                errors.add("Veuillez selectioner le client");
            }
            if(dto.getLigneCommandeClients() == null){
                errors.add("Veuillez choisir la ligne commande");
            }
            return errors;
        }
}
