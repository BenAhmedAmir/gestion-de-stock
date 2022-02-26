package com.benahmed.gestiondestock.DTO;

import com.benahmed.gestiondestock.model.Fournisseur;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class FournisseurDto {
    private Integer id;

    private String name;
    private String familyName;
    private String photo;
    private AdresseDto adresse;
    private String mail;
    private String phoneNumber;
    private List<CommandeFournisseurDto> commandeFournisseurs;
    public static FournisseurDto fromEntity(Fournisseur fournisseur){
        if(fournisseur == null){
            // TODO throw an exception
            return null;
        }
        return FournisseurDto.builder()
                .id(fournisseur.getId())
                .name(fournisseur.getName())
                .familyName(fournisseur.getFamilyName())
                .photo(fournisseur.getPhoto())
                .adresse(AdresseDto.fromEntity(fournisseur.getAdresse()))
                .mail(fournisseur.getMail())
                .phoneNumber(fournisseur.getPhoneNumber())
                .commandeFournisseurs(fournisseur.getCommandeFournisseurs() != null ?
                        fournisseur.getCommandeFournisseurs().stream()
                                .map(CommandeFournisseurDto::fromEntity)
                                .collect(Collectors.toList()) : null)
                .build();
    }
    public static Fournisseur toEntity(FournisseurDto fournisseurDto){
        if(fournisseurDto == null){
            // TODO throw an exception
            return null;
        }
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(fournisseurDto.getId());
        fournisseur.setName(fournisseurDto.getName());
        fournisseur.setFamilyName(fournisseurDto.getFamilyName());
        fournisseur.setPhoto(fournisseurDto.getPhoto());
        fournisseur.setAdresse(AdresseDto.toEntity(fournisseurDto.getAdresse()));
        fournisseur.setMail(fournisseurDto.getMail());
        fournisseur.setPhoneNumber(fournisseurDto.getPhoneNumber());
        fournisseur.setCommandeFournisseurs(fournisseurDto.getCommandeFournisseurs() != null ?
                fournisseurDto.getCommandeFournisseurs().stream()
                        .map(CommandeFournisseurDto::toEntity)
                        .collect(Collectors.toList()) : null);
        return fournisseur;
    }
}
