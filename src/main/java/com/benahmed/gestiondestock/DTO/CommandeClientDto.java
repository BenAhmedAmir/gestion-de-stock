package com.benahmed.gestiondestock.DTO;


import com.benahmed.gestiondestock.model.CommandeClient;
import com.benahmed.gestiondestock.model.EtatCommande;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class CommandeClientDto {
    private Integer id;

    private String code;
    private Instant dateCommande;
    private ClientDto client;
    private EtatCommande etatCommande;
    private List<LigneCommandeClientDto> ligneCommandeClients;
    public static CommandeClientDto fromEntity(CommandeClient commandeClient){
        if(commandeClient == null){
            // TODO throw an exception
            return null;
        }
        return CommandeClientDto.builder()
                .id(commandeClient.getId())
                .code(commandeClient.getCode())
                .etatCommande(commandeClient.getEtatCommande())
                .ligneCommandeClients(commandeClient.getLigneCommandeClients() != null ?
                        commandeClient.getLigneCommandeClients().stream()
                                .map(LigneCommandeClientDto::fromEntity)
                                .collect(Collectors.toList()): null)
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
        commandeClient.setEtatCommande(commandeClientDto.getEtatCommande());
        commandeClient.setLigneCommandeClients(commandeClientDto.getLigneCommandeClients() != null ?
                commandeClientDto.getLigneCommandeClients().stream()
                        .map(LigneCommandeClientDto::toEntity)
                        .collect(Collectors.toList()) : null);
        return commandeClient;
    }
    public boolean isCommandeLivree(){
        return EtatCommande.LIVREE.equals(this.etatCommande);
    }
}
