package com.benahmed.gestiondestock.controller.api;

import com.benahmed.gestiondestock.DTO.ArticleDto;
import com.benahmed.gestiondestock.DTO.LigneCommandeClientDto;
import com.benahmed.gestiondestock.DTO.LigneCommandeFournisseurDto;
import com.benahmed.gestiondestock.DTO.LigneVenteDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.benahmed.gestiondestock.utilis.constants.APP_ROOT;
@Api(APP_ROOT + "/articles")
public interface ArticleApi {
    @PostMapping(value = APP_ROOT + "/articles/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregister un article ", notes = "cette method permet d'ajouter ou modifier un article",
    response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "l'article est crée ou modifier")
    })
    ArticleDto save(@RequestBody ArticleDto dto);

    @GetMapping(value = APP_ROOT + "/articles/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "rechercher un article par ID",  notes = "cette methode permet de trouver un article par " +
            "son ID",
    response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "l'article a ete trouver dans la base"),
            @ApiResponse(code = 404, message = "l'article n'existe pas dans la base")
    })
    ArticleDto findById(@PathVariable("idArticle") Integer id);

    @GetMapping(value = APP_ROOT + "/articles/filter/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "rechercher un article par code",  notes = "cette methode permet de trouver un article par " +
            "son code",
            response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "l'article a ete trouver dans la base"),
            @ApiResponse(code = 404, message = "l'article n'existe pas dans la base")
    })
    ArticleDto findByCodeArticle(@PathVariable("codeArticle") String codeArticle);

    @GetMapping(value = APP_ROOT + "/articles/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "renvoi la liste de tous les articles",  notes = "cette methode permet de trouver la liste" +
            "des articles qui existent dans la base",
            responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la liste des articles ou liste vide")
    })
    List<ArticleDto> findAll();

    @DeleteMapping(APP_ROOT + "/articles/{idArticle}")
    @ApiOperation(value = "supprimer un article ",  notes = "cette methode permet de supprimer un article par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "l'article a ete supprimer")
    })
    void delete(@PathVariable("idArticle") Integer id);

    @GetMapping(value = APP_ROOT + "/articles/historique/vente/{idArticle}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "renvoi la liste de historique par ventes",
            notes = "cette methode permet de trouver le historique par ventes",
            responseContainer = "List<LigneVenteDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la liste de historique ou liste vide")
    })
    List<LigneVenteDto> findHistoriqueVentes(@PathVariable("idArticle") Integer idArticle);
    @GetMapping(value = APP_ROOT + "/articles/historique/commandeClient/{idArticle}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "renvoi la liste de historique par commande client",
            notes = "cette methode permet de trouver le historique par commande client",
            responseContainer = "List<LigneCommandeClientDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la liste de historique ou liste vide")
    })
    List<LigneCommandeClientDto> findHistotriqueCommandeClient(@PathVariable("idArticle") Integer idArticle);
    @GetMapping(value = APP_ROOT + "/articles/historique/commandeFournisseur/{idArticle}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "renvoi la liste de historique par commande fournisseur",
            notes = "cette methode permet de trouver le historique par commande fournisseur",
            responseContainer = "List<LigneCommandeFournisseurDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la liste de historique ou liste vide")
    })
    List<LigneCommandeFournisseurDto> findHistotriqueCommandeFournisseur(@PathVariable("idArticle")Integer idArticle);
    @GetMapping(value = APP_ROOT + "/articles/filter/category/{idCategory}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "renvoi la liste de article par idCategory",
            notes = "cette methode permet de trouver les articles par categorie",
            responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la liste de historique ou liste vide")
    })
    List<ArticleDto> findAllArticleByIdCategory(@PathVariable("idCategory")Integer idCategroy);
}
