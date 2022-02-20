package com.benahmed.gestiondestock.controller;

import com.benahmed.gestiondestock.DTO.VentesDto;
import com.benahmed.gestiondestock.controller.api.VenteApi;
import com.benahmed.gestiondestock.service.VentesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class VenteController implements VenteApi {

    private VentesService ventesService;
    @Autowired
    public VenteController(VentesService ventesService) {
        this.ventesService = ventesService;
    }

    @Override
    public VentesDto save(VentesDto dto) {
        return ventesService.save(dto);
    }

    @Override
    public VentesDto findById(Integer id) {
        return ventesService.findById(id);
    }

    @Override
    public VentesDto findByVenteCode(String code) {
        return ventesService.findByVenteCode(code);
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesService.findAll();
    }

    @Override
    public void delete(Integer id) {
        ventesService.delete(id);
    }
}
