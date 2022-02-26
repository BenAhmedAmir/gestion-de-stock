package com.benahmed.gestiondestock.controller.api;

import com.benahmed.gestiondestock.DTO.RolesDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.benahmed.gestiondestock.utilis.constants.APP_ROOT;

public interface RolesApi {
    @PostMapping(value = APP_ROOT+ "/roles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    RolesDto save(@RequestBody RolesDto dto);

    @GetMapping(value = APP_ROOT+ "/roles/{idRole}", produces = MediaType.APPLICATION_JSON_VALUE)
    RolesDto findById(@PathVariable("idRole") Integer id);

    @GetMapping(value = APP_ROOT+ "/roles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<RolesDto> findAll();

    @DeleteMapping(APP_ROOT + "/roles/{idRole}")
    void delete(@PathVariable("idRole") Integer id);
}
