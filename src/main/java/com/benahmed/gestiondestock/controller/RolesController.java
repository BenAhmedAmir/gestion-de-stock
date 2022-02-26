package com.benahmed.gestiondestock.controller;

import com.benahmed.gestiondestock.DTO.RolesDto;
import com.benahmed.gestiondestock.controller.api.RolesApi;
import com.benahmed.gestiondestock.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class RolesController implements RolesApi {
    
    private RolesService rolesService;
    @Autowired
    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @Override
    public RolesDto save(RolesDto dto) {
        return rolesService.save(dto);
    }

    @Override
    public RolesDto findById(Integer id) {
        return rolesService.findById(id);
    }

    @Override
    public List<RolesDto> findAll() {
        return rolesService.findAll();
    }

    @Override
    public void delete(Integer id) {
        rolesService.delete(id);
    }
}
