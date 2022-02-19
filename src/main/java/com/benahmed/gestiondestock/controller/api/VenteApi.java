package com.benahmed.gestiondestock.controller.api;

import com.benahmed.gestiondestock.DTO.VentesDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.benahmed.gestiondestock.utilis.constants.APP_ROOT;

public interface VenteApi {
    @PostMapping(value = APP_ROOT +"/ventes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    VentesDto save(@RequestBody VentesDto dto);

    @GetMapping(value = APP_ROOT + "/ventes/{idVente}",produces = MediaType.APPLICATION_JSON_VALUE)
    VentesDto findById(@PathVariable("idVente") Integer id);

    @GetMapping(value = APP_ROOT + "/ventes/{codeVente}",produces = MediaType.APPLICATION_JSON_VALUE)
    VentesDto findByCodeVente(@PathVariable("codeVente") String code);

    @GetMapping(value = APP_ROOT + "/ventes/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<VentesDto> findAll();

    @DeleteMapping(APP_ROOT + "ventes/{idVente}")
    void delete(@PathVariable("idVente") Integer id);
}
