package com.benahmed.gestiondestock.DTO;

import com.benahmed.gestiondestock.model.Entreprise;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class EntrepriseDto {
    private Integer id;

    private String name;
    private String description;
    private AdresseDto adresse;
    private String codeFiscal;
    private String photo;
    private String email;
    private String phoneNumber;
    private String url;
    private List<UtilisateurDto> utilisateurs;
    public static EntrepriseDto fromEntity(Entreprise entreprise){
        if(entreprise == null){
            // TODO throw an exception
            return null;
        }
        return EntrepriseDto.builder()
                .id(entreprise.getId())
                .name(entreprise.getName())
                .description(entreprise.getDescription())
                .adresse(AdresseDto.fromEntity(entreprise.getAdresse()))
                .codeFiscal(entreprise.getCodeFiscal())
                .photo(entreprise.getPhoto())
                .email(entreprise.getEmail())
                .phoneNumber(entreprise.getPhoneNumber())
                .url(entreprise.getUrl())
                .utilisateurs(entreprise.getUtilisateurs() != null ?
                        entreprise.getUtilisateurs().stream()
                                .map(UtilisateurDto::fromEntity).
                                collect(Collectors.toList()) : null)
                .build();
    }
    public static Entreprise toEntity(EntrepriseDto entrepriseDto){
        if(entrepriseDto == null){
            // TODO throw an exception
            return null;
        }
        Entreprise entreprise = new Entreprise();
        entreprise.setId(entrepriseDto.getId());
        entreprise.setName(entrepriseDto.getName());
        entreprise.setDescription(entrepriseDto.getDescription());
        entreprise.setAdresse(AdresseDto.toEntity(entrepriseDto.getAdresse()));
        entreprise.setCodeFiscal(entrepriseDto.getCodeFiscal());
        entreprise.setPhoto(entrepriseDto.getPhoto());
        entreprise.setEmail(entrepriseDto.getEmail());
        entreprise.setPhoneNumber(entrepriseDto.getPhoneNumber());
        entreprise.setUrl(entrepriseDto.getUrl());
        entreprise.setUtilisateurs(entrepriseDto.getUtilisateurs() != null ?
                entrepriseDto.getUtilisateurs().stream()
                        .map(UtilisateurDto::toEntity)
                        .collect(Collectors.toList()): null);
        return entreprise;
    }
}
