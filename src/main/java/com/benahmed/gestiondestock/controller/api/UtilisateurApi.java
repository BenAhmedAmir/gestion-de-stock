package com.benahmed.gestiondestock.controller.api;

import com.benahmed.gestiondestock.DTO.UtilisateurDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.benahmed.gestiondestock.utilis.constants.APP_ROOT;
@Api(APP_ROOT + "/utilisateurs")
public interface UtilisateurApi {
    @PostMapping(value = APP_ROOT +"/utilisateurs/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregister un utilisateur ", notes = "cette method permet d'ajouter ou modifier un utilisateur",
            response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "l'utilisateur est crée ou modifier")
    })
    UtilisateurDto save(@RequestBody UtilisateurDto dto);

    @GetMapping(value = APP_ROOT + "/utilisateurs/{idUtilisateur}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "rechercher un utilisateur par ID",  notes = "cette methode permet de trouver un utilisateur par " +
            "son ID",
            response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "l'utilisateur a ete trouver dans la base"),
            @ApiResponse(code = 404, message = "l'utilisateur n'existe pas dans la base")
    })
    UtilisateurDto findById(@PathVariable("idUtilisateur") Integer id);

    @GetMapping(value = APP_ROOT + "/utilisateurs/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "renvoi la liste de tous les utilisateurs",  notes = "cette methode permet de trouver la liste" +
            "des articles qui existent dans la base",
            responseContainer = "List<UtilisateurDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la liste des articles ou liste vide")
    })
    List<UtilisateurDto> findAll();

    @DeleteMapping(APP_ROOT + "utilisateurs/{idUtilisateur}")
    @ApiOperation(value = "supprimer un utilisateur ",  notes = "cette methode permet de supprimer un utilisateur par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "l'utilisateur a ete supprimer")
    })
    void delete(@PathVariable("idUtilisateur") Integer id);
}
