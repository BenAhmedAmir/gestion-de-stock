package com.benahmed.gestiondestock.DTO;

import com.benahmed.gestiondestock.model.Utilisateur;
import com.benahmed.gestiondestock.model.Ventes;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;
import java.util.List;

@Builder
@Data
public class UtilisateurDto {
    private Integer id;
    private String name;
    private String familyName;
    private String email;
    private String password;
    private AdresseDto adresse;
    private Instant dateDeNaissance;
    private String photo;
    private EntrepriseDto entreprise;
    private List<RolesDto> roles;
    public static UtilisateurDto fromEntity(Utilisateur utilisateur){
        if(utilisateur == null){
            // TODO throw an exception
            return null;
        }
        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .name(utilisateur.getName())
                .familyName(utilisateur.getFamilyName())
                .email(utilisateur.getEmail())
                .password(utilisateur.getPassword())
                .adresse(AdresseDto.fromEntity(utilisateur.getAdresse()))
                .dateDeNaissance(utilisateur.getDateDeNaissance())
                .photo(utilisateur.getPhoto())
                .entreprise(EntrepriseDto.fromEntity(utilisateur.getEntreprise()))
                .build();

    }
    public static Utilisateur toEntity(UtilisateurDto utilisateurDto){
        if(utilisateurDto == null){
            // TODO throw an exception
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setName(utilisateurDto.getName());
        utilisateur.setFamilyName(utilisateurDto.getFamilyName());
        utilisateur.setEmail(utilisateur.getEmail());
        utilisateur.setPassword(utilisateurDto.getPassword());
        utilisateur.setAdresse(AdresseDto.toEntity(utilisateurDto.getAdresse()));
        utilisateur.setDateDeNaissance(utilisateurDto.getDateDeNaissance());
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        utilisateur.setEntreprise(EntrepriseDto.toEntity(utilisateurDto.getEntreprise()));
        return utilisateur;
    }

}
