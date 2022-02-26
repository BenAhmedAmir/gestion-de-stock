package com.benahmed.gestiondestock.service;

import com.benahmed.gestiondestock.DTO.RolesDto;

import java.util.List;

public interface RolesService {
    RolesDto save(RolesDto dto);
    RolesDto findById(Integer id);
    List<RolesDto> findAll();
    void delete(Integer id);
}
