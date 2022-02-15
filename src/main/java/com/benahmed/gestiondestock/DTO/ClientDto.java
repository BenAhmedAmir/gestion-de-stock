package com.benahmed.gestiondestock.DTO;
import com.benahmed.gestiondestock.model.Client;
import lombok.Builder;
import lombok.Data;


import java.util.List;
@Data
@Builder
public class ClientDto {
    private Integer id;
    private String name;

    private String familyName;
    private String photo;
    private AdresseDto adresse;
    private String mail;
    private String phoneNumber;
    private List<CommandeClientDto> commandeClients;

    public ClientDto fromEntity(Client client){
        if(client == null){
            return null;
        }
        return ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .familyName(client.getFamilyName())
                .photo(client.getPhoto())
                .adresse(AdresseDto.fromEntity(client.getAdresse()))
                .mail(client.getMail())
                .phoneNumber(client.getPhoneNumber())
               // .commandeClients(CommandeClientDto.)
                .build();
    }
    public static Client toEntity(ClientDto clientDto){
        if(clientDto == null){
            // TODO throw an exception
            return null;
        }
        Client client = new Client();
        client.setId(clientDto.getId());
        client.setName(clientDto.getName());
        client.setFamilyName(clientDto.getFamilyName());
        client.setPhoto(clientDto.getPhoto());
        client.setAdresse(AdresseDto.toEntity(clientDto.getAdresse()));
        client.setMail(clientDto.getMail());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        return client;
    }
}
