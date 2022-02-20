package com.benahmed.gestiondestock.service.impl;

import com.benahmed.gestiondestock.DTO.FournisseurDto;
import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.InvalidEntityException;
import com.benahmed.gestiondestock.model.Fournisseur;
import com.benahmed.gestiondestock.repository.FournisseurRepository;
import com.benahmed.gestiondestock.service.FournisseurService;
import com.benahmed.gestiondestock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

    private FournisseurRepository fournisseurRepository;
    @Autowired
    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
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
        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(id);
        return Optional.of(FournisseurDto.fromEntity(fournisseur.get())).orElseThrow(()->
                new InvalidEntityException(
                "Aucun fournisseur avec l'id = "+id+" est trouve dans la base",
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
        fournisseurRepository.deleteById(id);
    }
}
