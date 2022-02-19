package com.benahmed.gestiondestock.controller.api;

import com.benahmed.gestiondestock.DTO.UtilisateurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.benahmed.gestiondestock.utilis.constants.APP_ROOT;

public interface UtilisateurApi {
    @PostMapping(value = APP_ROOT +"/utilisateurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto save(@RequestBody UtilisateurDto dto);

    @GetMapping(value = APP_ROOT + "/utilisateurs/{idUtilisateur}",produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto findById(@PathVariable("idUtilisateur") Integer id);

    @GetMapping(value = APP_ROOT + "/utilisateurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UtilisateurDto> findAll();

    @DeleteMapping(APP_ROOT + "utilisateurs/{idUtilisateur}")
    void delete(@PathVariable("idUtilisateur") Integer id);
}
