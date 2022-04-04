package com.benahmed.gestiondestock.service.impl;

import com.benahmed.gestiondestock.DTO.UpdateUserPasswordDto;
import com.benahmed.gestiondestock.DTO.UtilisateurDto;
import com.benahmed.gestiondestock.exception.EntityNotFoundException;
import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.InvalidEntityException;
import com.benahmed.gestiondestock.exception.InvalidOperationException;
import com.benahmed.gestiondestock.model.Utilisateur;
import com.benahmed.gestiondestock.repository.UtilisateurRepository;
import com.benahmed.gestiondestock.service.UtilisateurService;
import com.benahmed.gestiondestock.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
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
    @Transactional
    public UtilisateurDto findUtilisateurByEmail(String email) {
        return utilisateurRepository.findUtilisateurByEmail(email)
                .map(UtilisateurDto::fromEntity).orElseThrow(()->
                        new EntityNotFoundException("Aucun utilisateur avec l'email " + email +
                                " est trouve dans la base",  ErrorCodes.UTILISATEUR_NOT_FOUND)
        );
    }

    @Override
    public UtilisateurDto updatePassword(UpdateUserPasswordDto userPasswordDto) {
        validate(userPasswordDto);
        Optional<Utilisateur> utilisateurOptional  = utilisateurRepository.findById(userPasswordDto.getId());
        if(utilisateurOptional.isEmpty()){
            log.error("Aucun utilisateur a ete trouve");
            throw new EntityNotFoundException("Aucun utilisateur a ete trouve dans la base " + userPasswordDto.getId(),
                    ErrorCodes.UTILISATEUR_NOT_FOUND);
        }
        Utilisateur utilisateur =  utilisateurOptional.get();
        utilisateur.setPassword(userPasswordDto.getPassword());
        return UtilisateurDto.fromEntity(utilisateurRepository.save(utilisateur));
    }
    private void validate(UpdateUserPasswordDto userPasswordDto){
        if(userPasswordDto == null){
            log.warn("impossible de modifier le mot de pass avec un obj null");
            throw new InvalidOperationException("aucun information n'a ete fournis pour pouvoir changer le mot de passe",
                    ErrorCodes.UTILISATEUR_UPDATE_PASSWORD_NOT_VALID);
        }
        if(userPasswordDto.getId() == null){
            log.warn("impossible de modifier le mot de pass avec ID null");
            throw new InvalidOperationException("impossible de modifier le mot de pass avec ID null",
                    ErrorCodes.UTILISATEUR_UPDATE_PASSWORD_NOT_VALID);
        }
        if(!StringUtils.hasLength(userPasswordDto.getPassword()) || !StringUtils.hasLength(userPasswordDto.getConfirmPassword())){
            log.warn("impossible de modifier le mot de passe avec son champs null");
            throw new InvalidOperationException("impossible de modifier le mot de pass avec mot de passe null",
                    ErrorCodes.UTILISATEUR_UPDATE_PASSWORD_NOT_VALID);
        }
        if(!userPasswordDto.getPassword().equals(userPasswordDto.getConfirmPassword())){
            log.warn("Impossible de modifier le mot de passe avec deux mote de passe differents");
            throw new InvalidOperationException("Mot de passe utilisateur non conformes",
                    ErrorCodes.UTILISATEUR_UPDATE_PASSWORD_NOT_VALID);
        }
    }
}
