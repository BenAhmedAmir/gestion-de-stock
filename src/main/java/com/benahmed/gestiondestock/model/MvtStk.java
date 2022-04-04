package com.benahmed.gestiondestock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "mvtstock")
public class MvtStk extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;
    private Instant dateMvt;
    private BigDecimal quantite;
    @Column(name = "identreprise")
    private Integer idEntreprise;
    @Column(name = "typeMvt")
    private TypeMvt typeMvt;
    @Column(name = "sourceMvt")
    private SourceMvtStk sourceMvtStk;
}
