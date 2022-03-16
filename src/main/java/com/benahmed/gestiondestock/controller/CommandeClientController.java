package com.benahmed.gestiondestock.controller;

import com.benahmed.gestiondestock.DTO.CommandeClientDto;
import com.benahmed.gestiondestock.controller.api.CommandeClientApi;
import com.benahmed.gestiondestock.model.EtatCommande;
import com.benahmed.gestiondestock.service.CommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
@RestController
public class CommandeClientController implements CommandeClientApi {

    CommandeClientService commandeClientService;
    @Autowired
    public CommandeClientController(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    @Override
    public ResponseEntity<CommandeClientDto> save(CommandeClientDto dto) {
        return ResponseEntity.ok(commandeClientService.save(dto));
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateEtatCommande(Integer id, EtatCommande etatCommande) {
        return ResponseEntity.ok(commandeClientService.updateEtatCommande(id,etatCommande));
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateQuantite(Integer id, Integer idLigneCommande, BigDecimal quantite) {
        return ResponseEntity.ok(commandeClientService.updateQuantite(id,idLigneCommande,quantite));
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateClient(Integer id, Integer idClient) {
        return ResponseEntity.ok(commandeClientService.updateClient(id,idClient));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findById(Integer id) {
        return ResponseEntity.ok(commandeClientService.findById(id));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findByCode(String code) {
        return ResponseEntity.ok(commandeClientService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CommandeClientDto>> findAll() {
        return ResponseEntity.ok(commandeClientService.findAll());
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        commandeClientService.delete(id);
        return ResponseEntity.ok().build();
    }
}
