package com.benahmed.gestiondestock.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
public class Ventes extends AbstractEntity{
    private String code;
    private String commenatire;
    private Instant dateVente;
}
