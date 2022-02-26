package com.benahmed.gestiondestock.controller.api;

import com.benahmed.gestiondestock.DTO.CommandeClientDto;
import com.benahmed.gestiondestock.utilis.constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.benahmed.gestiondestock.utilis.constants.APP_ROOT;

@Api(APP_ROOT + "/commandesclient")
public interface CommandeClientApi {
    @PostMapping(value = constants.APP_ROOT + "/commandeclient/create", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregister une commande client ", notes = "cette method permet d'ajouter ou modifier une commande client",
            response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "la command client est crée ou modifier")
    })
    CommandeClientDto save(@RequestBody CommandeClientDto dto);
    @GetMapping(value = constants.APP_ROOT + "/commandeclient/{idCommandeClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "rechercher une commande client par ID",  notes = "cette methode permet de trouver une commande client par " +
            "son ID",
            response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la command client a ete trouver dans la base"),
            @ApiResponse(code = 404, message = "la command client n'existe pas dans la base")
    })
    CommandeClientDto findById(@PathVariable("idCommandeClient") Integer id);
    @GetMapping(constants.APP_ROOT + "/commandeclient/{codeCommande}")
    @ApiOperation(value = "rechercher une commande client par code",  notes = "cette methode permet de trouver une commande client par " +
            "son code",
            response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la command client a ete trouver dans la base"),
            @ApiResponse(code = 404, message = "la command client n'existe pas dans la base")
    })
    CommandeClientDto findByCode(@PathVariable("codeCommande") String code);
    @GetMapping(constants.APP_ROOT + "/commandeclient/all")
    @ApiOperation(value = "renvoi la liste de tous les commandes client",  notes = "cette methode permet de trouver la liste" +
            "des commandes client qui existent dans la base",
            responseContainer = "List<CommandeClientDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la liste des commandes client ou liste vide")
    })
    List<CommandeClientDto> findAll();
    @DeleteMapping(value = constants.APP_ROOT + "/commandeclient/{idCommandeClient}")
    @ApiOperation(value = "supprimer une commande client ",  notes = "cette methode permet de supprimer une commande client par son ID",
            response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la command client a ete supprimer")
    })
    void delete(@PathVariable("idCommandeClient") Integer id);
}
