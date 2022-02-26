package com.benahmed.gestiondestock.service.impl;

import com.benahmed.gestiondestock.DTO.ClientDto;
import com.benahmed.gestiondestock.exception.EntityNotFoundException;
import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.InvalidEntityException;
import com.benahmed.gestiondestock.model.Client;
import com.benahmed.gestiondestock.repository.ClientRepository;
import com.benahmed.gestiondestock.service.ClientService;
import com.benahmed.gestiondestock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    ClientRepository clientRepository;
    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto save(ClientDto dto) {
        List<String> errors = ClientValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Client nest pas valide {}", dto);
            throw new InvalidEntityException("client nest pas valide",
                    ErrorCodes.CLIENT_NOT_FOUND, errors);
        }
        return ClientDto.fromEntity(
                clientRepository.save(ClientDto.toEntity(dto))
        );
    }

    @Override
    public ClientDto findById(Integer id) {
        if (id == null){
            log.error("l'id est null");
            return null;
        }
        return clientRepository.findById(id)
                .map(ClientDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Aucun client est trouvé avec l'id = " + id + "dans la base",
                        ErrorCodes.CLIENT_NOT_FOUND
                ));
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("l'id est null");
            return;
        }
        clientRepository.deleteById(id);
    }
}
