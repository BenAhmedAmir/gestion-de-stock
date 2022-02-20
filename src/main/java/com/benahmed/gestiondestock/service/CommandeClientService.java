package com.benahmed.gestiondestock.service;

import com.benahmed.gestiondestock.DTO.CommandeClientDto;

import java.util.List;

public interface CommandeClientService{
    CommandeClientDto save(CommandeClientDto dto);
    CommandeClientDto findById(Integer id);
    CommandeClientDto findByCode(String code);
    List<CommandeClientDto> findAll();
    void delete(Integer id);
}
