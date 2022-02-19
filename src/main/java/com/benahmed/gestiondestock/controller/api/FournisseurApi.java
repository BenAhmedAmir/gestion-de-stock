package com.benahmed.gestiondestock.controller.api;

import com.benahmed.gestiondestock.DTO.FournisseurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.benahmed.gestiondestock.utilis.constants.APP_ROOT;

public interface FournisseurApi {
    @PostMapping(value = APP_ROOT +"/fournisseurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto save(@RequestBody FournisseurDto dto);

    @GetMapping(value = APP_ROOT + "/clients/{idFournisseur}",produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto findById(@PathVariable("idFournisseur") Integer id);

    @GetMapping(value = APP_ROOT + "/clients/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<FournisseurDto> findAll();

    @GetMapping(value = APP_ROOT + "/clients/{idFournisseur}",produces = MediaType.APPLICATION_JSON_VALUE)
    void delete(@PathVariable("idFournisseur") Integer id);
}
