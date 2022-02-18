package com.benahmed.gestiondestock.controller;

import com.benahmed.gestiondestock.DTO.FournisseurDto;
import com.benahmed.gestiondestock.controller.api.FournisseurApi;
import com.benahmed.gestiondestock.service.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FournisseurController implements FournisseurApi {

    private FournisseurService fournisseurService;
    @Autowired
    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @Override
    public FournisseurDto save(FournisseurDto dto) {
        return fournisseurService.save(dto);
    }

    @Override
    public FournisseurDto findById(Integer id) {
        return fournisseurService.findById(id);
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurService.findAll();
    }

    @Override
    public void delete(Integer id) {
        fournisseurService.delete(id);
    }
}
