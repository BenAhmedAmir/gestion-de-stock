package com.benahmed.gestiondestock.controller.api;

import com.benahmed.gestiondestock.DTO.CommandeClientDto;
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

@Api(APP_ROOT + "/commandesclient")
public interface CommandeClientApi {
    @PostMapping(value = constants.APP_ROOT + "/commandesclient/", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregister une commande client ", notes = "cette method permet d'ajouter ou modifier une commande client",
            response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "la command client est crée ou modifier")
    })
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto dto);
    @GetMapping(value = constants.APP_ROOT + "/commandesclient/{idCommandeClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "rechercher une commande client par ID",  notes = "cette methode permet de trouver une commande client par " +
            "son ID",
            response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la command client a ete trouver dans la base"),
            @ApiResponse(code = 404, message = "la command client n'existe pas dans la base")
    })
    ResponseEntity<CommandeClientDto>  findById(@PathVariable("idCommandeClient") Integer id);
    @GetMapping(constants.APP_ROOT + "/commandesclient/{codeCommande}")
    @ApiOperation(value = "rechercher une commande client par code",  notes = "cette methode permet de trouver une commande client par " +
            "son code",
            response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la command client a ete trouver dans la base"),
            @ApiResponse(code = 404, message = "la command client n'existe pas dans la base")
    })
    ResponseEntity<CommandeClientDto>  findByCode(@PathVariable("codeCommande") String code);
    @GetMapping(constants.APP_ROOT + "/commandesclient/")
    @ApiOperation(value = "renvoi la liste de tous les commandes client",  notes = "cette methode permet de trouver la liste" +
            "des commandes client qui existent dans la base",
            responseContainer = "List<CommandeClientDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la liste des commandes client ou liste vide")
    })
    ResponseEntity<List<CommandeClientDto>> findAll();
    @DeleteMapping(value = constants.APP_ROOT + "/commandesclient/{idCommandeClient}")
    @ApiOperation(value = "supprimer une commande client ",  notes = "cette methode permet de supprimer une commande client par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la command client a ete supprimer")
    })
    ResponseEntity<?> delete(@PathVariable("idCommandeClient") Integer id);
}
