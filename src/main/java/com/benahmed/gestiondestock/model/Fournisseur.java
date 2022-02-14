package com.benahmed.gestiondestock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Fournisseur extends AbstractEntity{
    private String name;
    @Column(name = "familyname")
    private String familyName;
    private String photo;
    @Embedded
    private Adresse adresse;
    private String mail;
    @Column(name = "phonenumber")
    private String phoneNumber;
    @OneToMany(mappedBy = "fournisseur")
    private List<CommandeFournisseur> commandeFournisseurs;
    @ManyToOne
    @JoinColumn(name = "idcommandefournisseur")
    private CommandeFournisseur commandeFournisseur;
}
