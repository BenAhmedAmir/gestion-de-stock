package com.benahmed.gestiondestock.service;

import com.benahmed.gestiondestock.DTO.EntrepriseDto;

import java.util.List;

public interface EntrepriseService {
    EntrepriseDto save(EntrepriseDto dto);
    EntrepriseDto findById(Integer id);
    EntrepriseDto findByCodeFiscal(String code);
    List<EntrepriseDto> findAll();
    void delete(Integer id);
}
