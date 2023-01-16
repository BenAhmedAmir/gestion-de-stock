package com.benahmed.gestiondestock.controller;

import com.benahmed.gestiondestock.DTO.CommandeFournisseurDto;
import com.benahmed.gestiondestock.DTO.LigneCommandeFournisseurDto;
import com.benahmed.gestiondestock.controller.api.CommandeFournisseurApi;
import com.benahmed.gestiondestock.model.EtatCommande;
import com.benahmed.gestiondestock.service.CommandeFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
@RestController
public class CommandeFournisseurController implements CommandeFournisseurApi {

    private final CommandeFournisseurService commandeFournisseurService;
    @Autowired
    public CommandeFournisseurController(CommandeFournisseurService commandeFournisseurService) {
        this.commandeFournisseurService = commandeFournisseurService;
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> save(CommandeFournisseurDto dto) {
        return ResponseEntity.ok(commandeFournisseurService.save(dto));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateEtatCommande(Integer id, EtatCommande etatCommande) {
        return ResponseEntity.ok(commandeFournisseurService.updateEtatCommande(id,etatCommande));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateQuantite(Integer id, Integer idLigneCommande, BigDecimal quantite) {
        return ResponseEntity.ok(commandeFournisseurService.updateQuantite(id,idLigneCommande,quantite));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateFournisseur(Integer id, Integer idFournisseur) {
        return ResponseEntity.ok(commandeFournisseurService.updateFournisseur(id,idFournisseur));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> findById(Integer id) {
        return ResponseEntity.ok(commandeFournisseurService.findById(id));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> findByCode(String code) {
        return ResponseEntity.ok(commandeFournisseurService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CommandeFournisseurDto>> findAll() {
        return ResponseEntity.ok(commandeFournisseurService.findAll());
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        commandeFournisseurService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> deleteArticle(Integer idCommande, Integer idLigneCommande) {
        return ResponseEntity.ok(commandeFournisseurService.deleteArticle(idCommande,idLigneCommande));
    }

    @Override
    public ResponseEntity<List<LigneCommandeFournisseurDto>> findAllLigneCommandeFournisseurByCommandeFournisseur(Integer idCommande) {
        return ResponseEntity.ok(commandeFournisseurService.findAllLigneCommandeFournisseurByCommandeFournisseur(idCommande));
    }
}
