package com.benahmed.gestiondestock.controller.api;

import com.benahmed.gestiondestock.DTO.VentesDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.benahmed.gestiondestock.utilis.constants.APP_ROOT;
@Api(APP_ROOT + "/ventes")
public interface VenteApi {
    @PostMapping(value = APP_ROOT +"/ventes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregister une vente ", notes = "cette method permet d'ajouter ou modifier une vente",
            response = VentesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "la vente est crée ou modifier")
    })
    VentesDto save(@RequestBody VentesDto dto);

    @GetMapping(value = APP_ROOT + "/ventes/{idVente}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "rechercher une vente par ID",  notes = "cette methode permet de trouver une vente par " +
            "son ID",
            response = VentesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la vente a ete trouver dans la base"),
            @ApiResponse(code = 404, message = "la vente n'existe pas dans la base")
    })
    VentesDto findById(@PathVariable("idVente") Integer id);

    @GetMapping(value = APP_ROOT + "/ventes/{codeVente}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "rechercher une vente par code",  notes = "cette methode permet de trouver une vente par " +
            "son code",
            response = VentesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la vente a ete trouver dans la base"),
            @ApiResponse(code = 404, message = "la vente n'existe pas dans la base")
    })
    VentesDto findByVenteCode(@PathVariable("codeVente") String code);

    @GetMapping(value = APP_ROOT + "/ventes/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "renvoi la liste de tous les ventes",  notes = "cette methode permet de trouver la liste" +
            "des ventes qui existent dans la base",
            responseContainer = "List<VentesDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la liste des ventes ou liste vide")
    })
    List<VentesDto> findAll();

    @DeleteMapping(APP_ROOT + "ventes/{idVente}")
    @ApiOperation(value = "supprimer une vente ",  notes = "cette methode permet de supprimer une vente par son ID",
            response = VentesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la vente a ete supprimer")
    })
    void delete(@PathVariable("idVente") Integer id);
}
