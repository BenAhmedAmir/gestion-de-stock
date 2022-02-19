package com.benahmed.gestiondestock.service;

import com.benahmed.gestiondestock.DTO.MvtStkDto;

import java.util.List;

public interface MvtStkService {
    MvtStkDto save(MvtStkDto dto);
    MvtStkDto findById(Integer id);
    List<MvtStkDto> findAll();
    void delete(Integer id);
}
