package com.benahmed.gestiondestock.service.impl;

import com.benahmed.gestiondestock.DTO.UtilisateurDto;
import com.benahmed.gestiondestock.exception.EntityNotFoundException;
import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.InvalidEntityException;
import com.benahmed.gestiondestock.repository.UtilisateurRepository;
import com.benahmed.gestiondestock.service.UtilisateurService;
import com.benahmed.gestiondestock.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        List<String> errors = UtilisateurValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("l'utilisateur est invalide {}", dto);
            throw new InvalidEntityException("l'utlisateur est invalide ", ErrorCodes.UTILISATEUR_NOT_VALID,
                    errors);
        }

        return UtilisateurDto.fromEntity(utilisateurRepository
                .save(UtilisateurDto.toEntity(dto)));
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        if (id == null){
            log.error("l'id est null");
            return null;
        }
        return utilisateurRepository.findById(id)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Aucun utilisateur est trouvé avec le code = " + id + "dans la base",
                        ErrorCodes.UTILISATEUR_NOT_FOUND
                ));
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        utilisateurRepository.deleteById(id);
    }

    @Override
    public UtilisateurDto findUtilisateurByEmail(String email) {
        return utilisateurRepository.findUtilisateurByEmail(email)
                .map(UtilisateurDto::fromEntity).orElseThrow(()->
                        new EntityNotFoundException("Aucun utilisateur avec l'email " + email +
                                " est trouve dans la base",  ErrorCodes.UTILISATEUR_NOT_FOUND)
        );
    }

}
