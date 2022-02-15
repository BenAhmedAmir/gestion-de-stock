package com.benahmed.gestiondestock.model;

import lombok.*;

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

}
