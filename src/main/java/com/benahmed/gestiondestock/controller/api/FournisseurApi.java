package com.benahmed.gestiondestock.controller.api;

import com.benahmed.gestiondestock.DTO.FournisseurDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.benahmed.gestiondestock.utilis.constants.APP_ROOT;
@Api(APP_ROOT + "/fournisseurs")
public interface FournisseurApi {
    @PostMapping(value = APP_ROOT +"/fournisseurs/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregister un fournisseur ", notes = "cette method permet d'ajouter ou modifier un fournisseur",
            response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "le fournisseur est crée ou modifier")
    })
    FournisseurDto save(@RequestBody FournisseurDto dto);

    @GetMapping(value = APP_ROOT + "/fournisseurs/{idFournisseur}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "rechercher un fournisseur par ID",  notes = "cette methode permet de trouver un fournisseur par " +
            "son ID",
            response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "le fournisseur a ete trouver dans la base"),
            @ApiResponse(code = 404, message = "le fournisseur n'existe pas dans la base")
    })
    FournisseurDto findById(@PathVariable("idFournisseur") Integer id);

    @GetMapping(value = APP_ROOT + "/fournisseurs/",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "renvoi la liste de tous les fournisseurs",  notes = "cette methode permet de trouver la liste" +
            "des fournisseurs qui existent dans la base",
            responseContainer = "List<FournisseurDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la liste des fournisseurs ou liste vide")
    })
    List<FournisseurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/fournisseurs/delete/{idFournisseur}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "supprimer un fournisseur ",  notes = "cette methode permet de supprimer un fournisseur par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "le fournisseur a ete supprimer")
    })
    void deleteById(@PathVariable("idFournisseur") Integer id);
}
