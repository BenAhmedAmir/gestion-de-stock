package com.benahmed.gestiondestock.service;

import com.benahmed.gestiondestock.DTO.CommandeFournisseurDto;

import java.util.List;

public interface CommandeFournisseurService {
    CommandeFournisseurDto save(CommandeFournisseurDto dto);
    CommandeFournisseurDto findById(Integer id);
    CommandeFournisseurDto findByCode(String code);
    List<CommandeFournisseurDto> findAll();
    void delete(Integer id);
}
