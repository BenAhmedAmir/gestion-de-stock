package com.benahmed.gestiondestock.controller.api;

import com.benahmed.gestiondestock.DTO.RolesDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.benahmed.gestiondestock.utilis.constants.APP_ROOT;
@Api(APP_ROOT + "/roles")
public interface RolesApi {
    @PostMapping(value = APP_ROOT+ "/roles/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregister un role ", notes = "cette method permet d'ajouter ou modifier un role",
            response = RolesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "le role est crée ou modifier")
    })
    RolesDto save(@RequestBody RolesDto dto);

    @GetMapping(value = APP_ROOT+ "/roles/{idRole}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "rechercher un role par ID",  notes = "cette methode permet de trouver un role par " +
            "son ID",
            response = RolesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "le role a ete trouver dans la base"),
            @ApiResponse(code = 404, message = "le role n'existe pas dans la base")
    })
    RolesDto findById(@PathVariable("idRole") Integer id);

    @GetMapping(value = APP_ROOT+ "/roles/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "renvoi la liste de tous les Roles",  notes = "cette methode permet de trouver la liste" +
            "des Roles qui existent dans la base",
            responseContainer = "List<RolesDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la liste des Roles ou liste vide")
    })
    List<RolesDto> findAll();

    @DeleteMapping(APP_ROOT + "/roles/{idRole}")
    @ApiOperation(value = "supprimer un role ",  notes = "cette methode permet de supprimer un role par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "le role a ete supprimer")
    })
    void delete(@PathVariable("idRole") Integer id);
}
