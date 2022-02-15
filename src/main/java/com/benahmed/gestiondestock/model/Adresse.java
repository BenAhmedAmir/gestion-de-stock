package com.benahmed.gestiondestock.model;

import lombok.*;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
@Embeddable
public class Adresse {
    private String adresse1;
    private String adresse2;
    private String ville;
    private String codePostal;
    private String pays;


}
