package com.benahmed.gestiondestock.controller.api;

import com.benahmed.gestiondestock.DTO.CommandeFournisseurDto;
import com.benahmed.gestiondestock.utilis.constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.benahmed.gestiondestock.utilis.constants.APP_ROOT;

@Api(APP_ROOT + "/commandesfournisseur")
public interface CommandeFournisseurApi {
    @PostMapping(value = APP_ROOT + "/commandesfournisseur/", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregister une commande fournisseur ", notes = "cette method permet d'ajouter ou modifier une commande fournisseur",
            response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201 , message = "la commande fournisseur est crée ou modifier")
    })
    ResponseEntity<CommandeFournisseurDto> save(@RequestBody CommandeFournisseurDto dto);
    @GetMapping(value = APP_ROOT + "/commandesfournisseur/{idCommandefournisseur}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "rechercher une commande fournisseur par son ID", notes = "cette methode permet de trouver des commandes fournisseur par son ID",
    response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la commande fournisseur a ete trouver dans la base"),
            @ApiResponse(code = 404, message = "la commande fournisseur n'existe pas dans la base")
    })
    ResponseEntity<CommandeFournisseurDto> findById(@PathVariable("idCommandefournisseur") Integer id);
    @GetMapping(value = APP_ROOT + "/commandesfournisseur/{codeCommandefournisseur}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "rechercher une commande fournisseur par son code", notes = "cette methode permet de trouver des commandes fournisseur par son code",
            response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la commande fournisseur a ete trouver dans la base"),
            @ApiResponse(code = 404, message = "la commande fournisseur n'existe pas dans la base")
    })
    ResponseEntity<CommandeFournisseurDto> findByCode(@PathVariable("codeCommandefournisseur") String code);
    @GetMapping(APP_ROOT + "/commandesfournisseur/")
    @ApiOperation(value = "renvoi la liste de tous les commandes fournisseur",  notes = "cette methode permet de trouver la liste" +
            "des commandes fournisseur qui existent dans la base",
            responseContainer = "List<CommandeFournisseurDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la liste des commandes fournisseur ou liste vide")
    })
    ResponseEntity<List<CommandeFournisseurDto>> findAll();
    @DeleteMapping(value = APP_ROOT + "/commandesclient/{idCommandefournisseur}")
    @ApiOperation(value = "supprimer une commande fournisseur ",  notes = "cette methode permet de supprimer une commande fournisseur par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la commande fournisseur a ete supprimer")
    })
    ResponseEntity<?> delete(@PathVariable("idCommandefournisseur") Integer id);
}
