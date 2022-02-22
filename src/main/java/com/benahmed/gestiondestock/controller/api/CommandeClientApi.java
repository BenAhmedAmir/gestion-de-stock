package com.benahmed.gestiondestock.controller.api;

import com.benahmed.gestiondestock.DTO.CommandeClientDto;
import com.benahmed.gestiondestock.utilis.constants;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CommandeClientApi {
    @PostMapping(value = constants.APP_ROOT + "/commandeclient/create", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto save(@RequestBody CommandeClientDto dto);
    @GetMapping(value = constants.APP_ROOT + "/commandeclient/{idCommandeClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto findById(@PathVariable("idCommandeClient") Integer id);
    @GetMapping(constants.APP_ROOT + "/commandeclient/{codeCommande}")
    CommandeClientDto findByCode(@PathVariable("codeCommande") String code);
    @GetMapping(constants.APP_ROOT + "/commandeclient/all")
    List<CommandeClientDto> findAll();
    @DeleteMapping(value = constants.APP_ROOT + "/commandeclient/{idCommandeClient}")
    void delete(@PathVariable("idCommandeClient") Integer id);
}
