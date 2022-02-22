package com.benahmed.gestiondestock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
public class Ventes extends AbstractEntity{
    private String venteCode;
    private String commenatire;
    private Instant dateVente;
    @Column(name = "identreprise")
    private Integer idEntreprise;
    @OneToMany(mappedBy = "vente")
    private List<LigneVente> ligneVentes;
}
