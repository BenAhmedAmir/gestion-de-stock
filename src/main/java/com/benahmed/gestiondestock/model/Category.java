package com.benahmed.gestiondestock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
public class Category extends AbstractEntity{
    @Column
    private String categoryCode;
    @Column
    private String designation;
    @Column(name = "identreprise")
    private Integer idEntreprise;
    @OneToMany(mappedBy = "category")
    private List<Article> articles;
}
