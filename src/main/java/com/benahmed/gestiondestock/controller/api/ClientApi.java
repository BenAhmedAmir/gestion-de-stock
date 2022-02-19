package com.benahmed.gestiondestock.controller.api;

import com.benahmed.gestiondestock.DTO.ClientDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.benahmed.gestiondestock.utilis.constants.APP_ROOT;

public interface ClientApi {
    @PostMapping(value = APP_ROOT +"/clients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto save(@RequestBody ClientDto dto);

    @GetMapping(value = APP_ROOT + "/clients/{idClient}",produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto findById(@PathVariable("idClient") Integer id);

    @GetMapping(value = APP_ROOT + "/clients/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ClientDto> findAll();

    @DeleteMapping(APP_ROOT + "clients/{idClient}")
    void delete(@PathVariable("idClient") Integer id);
}
