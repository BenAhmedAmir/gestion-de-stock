package com.benahmed.gestiondestock.model;

import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
public class Entreprise extends AbstractEntity {
    private String name;
    private String description;
    @Embedded
    private Adresse adresse;
    private String codeFiscal;
    private String photo;
    private String email;
    private String phoneNumber;
    private String url;
    @OneToMany(mappedBy = "entreprise")
    private List<Utilisateur> utilisateurs;
}
