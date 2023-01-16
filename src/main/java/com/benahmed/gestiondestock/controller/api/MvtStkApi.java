package com.benahmed.gestiondestock.controller.api;

import com.benahmed.gestiondestock.DTO.MvtStkDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.benahmed.gestiondestock.utilis.constants.APP_ROOT;
@Api(APP_ROOT + "/mouvementstocks")
public interface MvtStkApi {
    @PostMapping(value = APP_ROOT+ "/mouvementstocks/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregister un mouvement de stock ", notes = "cette method permet d'ajouter ou modifier un mouvement de stock",
            response = MvtStkDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "le mouvement de stock est crée ou modifier")
    })
    MvtStkDto save(@RequestBody MvtStkDto dto);

    @GetMapping(value = APP_ROOT+ "/mouvementstocks/{idMvtstk}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "rechercher un mouvement de stock par ID",  notes = "cette methode permet de trouver un mouvement de stock par " +
            "son ID",
            response = MvtStkDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "le mouvement de stock a ete trouver dans la base"),
            @ApiResponse(code = 404, message = "le mouvement de stock n'existe pas dans la base")
    })
    MvtStkDto findById(@PathVariable("idMvtstk") Integer id);

    @GetMapping(value = APP_ROOT+ "/mouvementstocks/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "renvoi la liste de tous les mouvements de stock",  notes = "cette methode permet de trouver la liste" +
            "des articles qui existent dans la base",
            responseContainer = "List<MvtStkDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la liste des articles ou liste vide")
    })
    List<MvtStkDto> findAll();

    @DeleteMapping(APP_ROOT + "/mouvementstocks/{idMvtstk}")
    @ApiOperation(value = "supprimer un mouvement de stock ",  notes = "cette methode permet de supprimer un mouvement de stock par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "le mouvement de stock a ete supprimer")
    })
    void delete(@PathVariable("idMvtstk") Integer id);
}
