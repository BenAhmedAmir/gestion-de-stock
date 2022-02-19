package com.benahmed.gestiondestock.service;

import com.benahmed.gestiondestock.DTO.VentesDto;

import java.util.List;

public interface VentesService {
    VentesDto save(VentesDto dto);
    VentesDto findById(Integer id);
    VentesDto findByCodeVente(String code);
    List<VentesDto> findAll();
    void delete(Integer id);
}
