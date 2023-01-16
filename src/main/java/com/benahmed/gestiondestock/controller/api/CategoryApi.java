package com.benahmed.gestiondestock.controller.api;
import com.benahmed.gestiondestock.DTO.CategoryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.benahmed.gestiondestock.utilis.constants.APP_ROOT;
@Api(APP_ROOT + "/categories")
public interface CategoryApi {
    @PostMapping(value = APP_ROOT + "/categories/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregister une categorie ", notes = "cette method permet d'ajouter ou modifier une categorie",
            response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "la categorie est crée ou modifier")
    })
    CategoryDto save(@RequestBody CategoryDto dto);

    @GetMapping(value = APP_ROOT + "/categories/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "rechercher une categorie par ID",  notes = "cette methode permet de trouver une categorie par " +
            "son ID",
            response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la categorie trouver dans la base"),
            @ApiResponse(code = 404, message = "la categorie n'existe pas dans la base")
    })
    CategoryDto findById(@PathVariable("idCategory") Integer id);

    @GetMapping(value = APP_ROOT + "/categories/{codeCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "rechercher une categorie par code",  notes = "cette methode permet de trouver une categorie par " +
            "son code",
            response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la categorie a ete trouver dans la base"),
            @ApiResponse(code = 404, message = "la categorie n'existe pas dans la base")
    })
    CategoryDto findByCategoryCode(@PathVariable("codeCategory") String code);

    @GetMapping(value = APP_ROOT + "/categories/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "renvoi la liste de tous les categories",  notes = "cette methode permet de trouver la liste" +
            "des categories qui existent dans la base",
            responseContainer = "List<CategoryDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la liste des categories ou liste vide")
    })
    List<CategoryDto> findAll();

    @DeleteMapping(APP_ROOT + "/categories/{idCategory}")
    @ApiOperation(value = "supprimer une categorie ",  notes = "cette methode permet de supprimer une categorie par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la categorie a ete supprimer")
    })
    void delete(@PathVariable("idCategory") Integer id);
}
