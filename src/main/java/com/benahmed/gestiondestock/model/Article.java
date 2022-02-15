package com.benahmed.gestiondestock.model;

import lombok.*;

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
    @ManyToOne()
    @JoinColumn(name = "idcategory")
    private Category category;
}
