package com.benahmed.gestiondestock.service;

import com.benahmed.gestiondestock.DTO.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto save(CategoryDto dto);
    CategoryDto findById(Integer id);
    CategoryDto findByCategoryCode(String code);
    List<CategoryDto> findAll();
    void delete(Integer id);
}
