package com.benahmed.gestiondestock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
public class Article extends AbstractEntity{
    @Column(name = "codearticle")
    private String codeArticle;
    private String designation;
    @Column(name = "prixunitaireht")
    private BigDecimal prixUnitaireHt;
    private BigDecimal tauxTva;
    private BigDecimal prixUnitaireTtc;
    private String photo;
    @Column(name = "identreprise")
    private Integer idEntreprise;
    @ManyToOne()
    @JoinColumn(name = "idcategory")
    private Category category;
    @OneToMany(mappedBy = "article")
    private List<LigneVente> ligneVentes;
    @OneToMany(mappedBy = "article")
    private List<LigneCommandeClient> ligneCommandeClients;
    @OneToMany(mappedBy = "article")
    private List<LigneCommandeFournisseur> ligneCommandeFournisseurs;
    @OneToMany(mappedBy = "article")
    private List<MvtStk> mvtStks;

}
