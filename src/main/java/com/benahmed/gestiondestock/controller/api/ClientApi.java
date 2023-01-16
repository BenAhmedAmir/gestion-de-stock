package com.benahmed.gestiondestock.controller.api;

import com.benahmed.gestiondestock.DTO.ClientDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.benahmed.gestiondestock.utilis.constants.APP_ROOT;
@Api(APP_ROOT + "/clients")
public interface ClientApi {
    @PostMapping(value = APP_ROOT +"/clients/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregister un client ", notes = "cette method permet d'ajouter ou modifier un article",
            response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "le client est crée ou modifier")
    })
    ClientDto save(@RequestBody ClientDto dto);

    @GetMapping(value = APP_ROOT + "/clients/{idClient}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "rechercher un article par ID",  notes = "cette methode permet de trouver un article par " +
            "son ID",
            response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "le client a ete trouver dans la base"),
            @ApiResponse(code = 404, message = "le client n'existe pas dans la base")
    })
    ClientDto findById(@PathVariable("idClient") Integer id);

    @GetMapping(value = APP_ROOT + "/clients/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "renvoi la liste de tous les clients",  notes = "cette methode permet de trouver la liste" +
            "des clients qui existent dans la base",
            responseContainer = "List<ClientDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la liste des clients ou liste vide")
    })
    List<ClientDto> findAll();

    @DeleteMapping(APP_ROOT + "clients/{idClient}")
    @ApiOperation(value = "supprimer un article ",  notes = "cette methode permet de supprimer un article par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "le client a ete supprimer")
    })
    void delete(@PathVariable("idClient") Integer id);
}
