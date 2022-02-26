package com.benahmed.gestiondestock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Entity
@Table
public class Client extends AbstractEntity{
    private String name;
    @Column(name = "familyname")
    private String familyName;
    private String photo;
    @Embedded
    private Adresse adresse;
    private String mail;
    @Column(name = "phonenumber")
    private String phoneNumber;
    @Column(name = "identreprise")
    private Integer idEntreprise;
    @OneToMany(mappedBy = "client")
    private List<CommandeClient> commandeClients;

    public Adresse getAdresse() {
        return adresse;
    }
}
