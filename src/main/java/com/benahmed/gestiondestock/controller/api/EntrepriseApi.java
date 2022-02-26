package com.benahmed.gestiondestock.controller.api;

import com.benahmed.gestiondestock.DTO.EntrepriseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.benahmed.gestiondestock.utilis.constants.APP_ROOT;
@Api(APP_ROOT + "/entreprises")
public interface EntrepriseApi {

    @PostMapping(value = APP_ROOT + "/entreprises/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregister  une entreprise ", notes = "cette method permet d'ajouter ou modifier  une entreprise",
            response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "l'entreprise est crée ou modifier")
    })
    EntrepriseDto save(@RequestBody EntrepriseDto dto);

    @GetMapping(value = APP_ROOT + "/entreprises/{idEntreprise}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "rechercher  une entreprise par ID",  notes = "cette methode permet de trouver  une entreprise par " +
            "son ID",
            response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "l'entreprise a ete trouver dans la base"),
            @ApiResponse(code = 404, message = "l'entreprise n'existe pas dans la base")
    })
    EntrepriseDto findById(@PathVariable("idEntreprise") Integer id);

    @GetMapping(value = APP_ROOT + "/entreprises/{codeFiscal}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "rechercher  une entreprise par code",  notes = "cette methode permet de trouver  une entreprise par " +
            "son code",
            response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "l'entreprise a ete trouver dans la base"),
            @ApiResponse(code = 404, message = "l'entreprise n'existe pas dans la base")
    })
    EntrepriseDto findByCodeFiscal(@PathVariable("codeFiscal") String code);

    @GetMapping(value = APP_ROOT + "/entreprises/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "renvoi la liste de tous les entreprises",  notes = "cette methode permet de trouver la liste" +
            "des entreprises qui existent dans la base",
            responseContainer = "List<EntrepriseDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la liste des entreprises ou liste vide")
    })
    List<EntrepriseDto> findAll();

    @DeleteMapping(APP_ROOT + "/entreprise/{idEntreprise}")
    @ApiOperation(value = "supprimer  une entreprise ",  notes = "cette methode permet de supprimer  une entreprise par son ID",
            response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "l'entreprise a ete supprimer")
    })
    void delete(@PathVariable("idEntreprise") Integer id);
}
