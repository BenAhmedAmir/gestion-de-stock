package com.benahmed.gestiondestock.service;

import com.benahmed.gestiondestock.DTO.FournisseurDto;

import java.util.List;

public interface FournisseurService {
    FournisseurDto save(FournisseurDto dto);
    FournisseurDto findById(Integer id);
    List<FournisseurDto> findAll();
    void delete(Integer id);
}
