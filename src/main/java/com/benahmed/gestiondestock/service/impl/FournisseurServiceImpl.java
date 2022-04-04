package com.benahmed.gestiondestock.service.impl;

import com.benahmed.gestiondestock.DTO.FournisseurDto;
import com.benahmed.gestiondestock.exception.EntityNotFoundException;
import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.InvalidEntityException;
import com.benahmed.gestiondestock.exception.InvalidOperationException;
import com.benahmed.gestiondestock.model.CommandeFournisseur;
import com.benahmed.gestiondestock.repository.CommandeFournisseurRepository;
import com.benahmed.gestiondestock.repository.FournisseurRepository;
import com.benahmed.gestiondestock.service.FournisseurService;
import com.benahmed.gestiondestock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

    private final FournisseurRepository fournisseurRepository;
    private final CommandeFournisseurRepository commandeFournisseurRepository;
    @Autowired
    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository, CommandeFournisseurRepository commandeFournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
        this.commandeFournisseurRepository = commandeFournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto dto) {
        List<String> errors = FournisseurValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("le fournisseur est nest pas valide {}", dto);
            throw new InvalidEntityException("le fournisseur nest pas valide",
                    ErrorCodes.FOURNISSEUR_NOT_VALID);
        }
        return FournisseurDto.fromEntity(fournisseurRepository
                .save(FournisseurDto.toEntity(dto)));
    }

    @Override
    public FournisseurDto findById(Integer id) {
        if(id == null){
            log.error("l'id est null");
            return null;
        }
        return fournisseurRepository.findById(id)
                .map(FournisseurDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Aucun fournisseur est trouvé avec le code = " + id + "dans la base",
                        ErrorCodes.FOURNISSEUR_NOT_FOUND
                ));
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll().stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if(id == null){
            log.error("l'id est null");
            return;
        }
        List<CommandeFournisseur> commandeFournisseurs = commandeFournisseurRepository.findAllByFournisseurId(id);
        if(!commandeFournisseurs.isEmpty()){
            throw new InvalidOperationException("impossible de supprimer un fournisseur qui a deja des commandes fournisseur",
                    ErrorCodes.CLIENT_ALREADY_IN_USE);
        }
        fournisseurRepository.deleteById(id);
    }
}
