package com.benahmed.gestiondestock.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
public class Utilisateur extends AbstractEntity{
    private String name;
    @Column(name = "familyname")
    private String familyName;
    private String email;
    private String password;
    @Embedded
    private Adresse adresse;
    private Instant dateDeNaissance;
    private String photo;
    @ManyToOne
    @JoinColumn(name = "identreprsie")
    private Entreprise entreprise;
    @OneToMany(mappedBy = "utilisateur")
    private List<Roles> roles;
}
