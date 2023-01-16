package com.benahmed.gestiondestock.controller.api;

import com.benahmed.gestiondestock.DTO.CommandeClientDto;
import com.benahmed.gestiondestock.DTO.LigneCommandeClientDto;
import com.benahmed.gestiondestock.model.EtatCommande;

import com.benahmed.gestiondestock.model.LigneCommandeClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.benahmed.gestiondestock.utilis.constants.APP_ROOT;

@Api(APP_ROOT + "/commandesclient")
public interface CommandeClientApi {
    @PostMapping(value = APP_ROOT + "/commandesclient/", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregister une commande client ",
            notes = "cette method permet d'ajouter ou modifier une commande client",
            response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "la command client est crée ou modifier")
    })
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto dto);

    @PatchMapping(value = APP_ROOT + "/commandesclient/update/etat/{idCommande}/{etatCommande}",
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un etat d'une commande ",
            notes = "cette methode permet modifier un etat commande ",
            response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "l'etat commande est modifier")
    })
    ResponseEntity<CommandeClientDto> updateEtatCommande(@PathVariable("idCommande") Integer id,
                                                         @PathVariable("etatCommande") EtatCommande etatCommande);


    @PatchMapping(value = APP_ROOT + "/commandesclient/update/article/{idCommande}/{idLigneCommande}/{idArticle}",
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un article d'une commande ",
            notes = "cette methode permet modifier un article de commande ",
            response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "l'article de commande est modifier")
    })
    ResponseEntity<CommandeClientDto> updateArticle(@PathVariable("idCommande") Integer idCommande
            ,@PathVariable("idLigneCommande") Integer idLigneCommande,@PathVariable("idArticle") Integer idArticle);

    @DeleteMapping(value = APP_ROOT + "/commandesclient/{idCommande}/{idLigneCommande}",
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "supprimer une ligne commande  ",
            notes = "cette methode permet de supprimer ligne commande  par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la ligne commande a ete supprimer")
    })
    ResponseEntity<CommandeClientDto> deleteArticle(@PathVariable("idCommande") Integer idCommande
            ,@PathVariable("idLigneCommande") Integer idLigneCommande);


    @PatchMapping(value = APP_ROOT + "/commandesclient/update/quantite/{idCommande}/{idLigneCommande}/{quantite}",
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier la quantite d'une commande ",
            notes = "cette methode permet modifier un la quantite commande ",
            response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "la quantite est modifier")
    })
    ResponseEntity<CommandeClientDto> updateQuantite(@PathVariable("idCommande") Integer id, @PathVariable("idLigneCommande")
            Integer idLigneCommande, @PathVariable("quantite")BigDecimal quantite);

    @PatchMapping(value = APP_ROOT + "/commandesclient/update/client/{idCommande}/{idClient}",
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un client d'une commande ",
            notes = "cette methode permet modifier client de une commande ",
            response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "le client est modifier")
    })
    ResponseEntity<CommandeClientDto> updateClient(@PathVariable("idCommande") Integer id,
                                                   @PathVariable("idClient")Integer idClient);

    @GetMapping(value = APP_ROOT + "/commandesclient/lignesCommande/{idCommande}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "rechercher une ligne Commande client par ID",
            notes = "cette methode permet de trouver une ligne Commande client par " +
                    "son ID",
            response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la ligne Commande client a ete trouver dans la base"),
            @ApiResponse(code = 404, message = "la ligne Commande client n'existe pas dans la base")
    })
    ResponseEntity<List<LigneCommandeClientDto>>  findAllLigneCommandeClientByCommandeClient(Integer idCommande);

    @GetMapping(value = APP_ROOT + "/commandesclient/{idCommandeClient}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "rechercher une commande client par ID",
            notes = "cette methode permet de trouver une commande client par " +
            "son ID",
            response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la command client a ete trouver dans la base"),
            @ApiResponse(code = 404, message = "la command client n'existe pas dans la base")
    })
    ResponseEntity<CommandeClientDto>  findById(@PathVariable("idCommandeClient") Integer id);

    @GetMapping(APP_ROOT + "/commandesclient/{codeCommande}")
    @ApiOperation(value = "rechercher une commande client par code",
            notes = "cette methode permet de trouver une commande client par " +
            "son code",
            response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la command client a ete trouver dans la base"),
            @ApiResponse(code = 404, message = "la command client n'existe pas dans la base")
    })
    ResponseEntity<CommandeClientDto>  findByCode(@PathVariable("codeCommande") String code);
    @GetMapping(APP_ROOT + "/commandesclient/")
    @ApiOperation(value = "renvoi la liste de tous les commandes client",
            notes = "cette methode permet de trouver la liste" + "des commandes client qui existent dans la base",
            responseContainer = "List<CommandeClientDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la liste des commandes client ou liste vide")
    })
    ResponseEntity<List<CommandeClientDto>> findAll();

    @DeleteMapping(value = APP_ROOT + "/commandesclient/{idCommandeClient}")
    @ApiOperation(value = "supprimer une commande client ",
            notes = "cette methode permet de supprimer une commande client par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la command client a ete supprimer")
    })
    ResponseEntity<?> delete(@PathVariable("idCommandeClient") Integer id);
}
