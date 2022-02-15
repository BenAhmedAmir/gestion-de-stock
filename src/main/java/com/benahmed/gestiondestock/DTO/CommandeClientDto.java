package com.benahmed.gestiondestock.DTO;


import com.benahmed.gestiondestock.model.CommandeClient;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
@Builder
@Data
public class CommandeClientDto {
    private Integer id;

    private String code;
    private Instant dateCommande;
    private ClientDto client;
    private List<LigneCommandeClientDto> ligneCommandeClients;
    public static CommandeClientDto fromEntity(CommandeClient commandeClient){
        if(commandeClient == null){
            // TODO throw an exception
            return null;
        }
        return CommandeClientDto.builder()
                .id(commandeClient.getId())
                .code(commandeClient.getCode())
                .build();

    }
    public static CommandeClient toEntity(CommandeClientDto commandeClientDto){
        if(commandeClientDto==null){
            // TODO throw an exception
            return null;
        }
        CommandeClient commandeClient = new CommandeClient();
        commandeClient.setId(commandeClientDto.getId());
        commandeClient.setCode(commandeClientDto.getCode());
        commandeClient.setClient(ClientDto.toEntity(commandeClientDto.getClient()));
        return commandeClient;
    }
}
