package com.benahmed.gestiondestock.controller;

import com.benahmed.gestiondestock.DTO.CommandeClientDto;
import com.benahmed.gestiondestock.controller.api.CommandeClientApi;
import com.benahmed.gestiondestock.service.CommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommandeClientController implements CommandeClientApi {

    CommandeClientService commandeClientService;
    @Autowired
    public CommandeClientController(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {
        return commandeClientService.save(dto);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        return commandeClientService.findById(id);
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        return commandeClientService.findByCode(code);
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientService.findAll();
    }

    @Override
    public void delete(Integer id) {
        commandeClientService.delete(id);
    }
}
