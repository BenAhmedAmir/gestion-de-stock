package com.benahmed.gestiondestock.service.impl;

import com.benahmed.gestiondestock.DTO.ArticleDto;
import com.benahmed.gestiondestock.DTO.EntrepriseDto;
import com.benahmed.gestiondestock.exception.EntityNotFoundException;
import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.InvalidEntityException;
import com.benahmed.gestiondestock.model.Entreprise;
import com.benahmed.gestiondestock.repository.EntrepriseRepository;
import com.benahmed.gestiondestock.service.EntrepriseService;
import com.benahmed.gestiondestock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private EntrepriseRepository entrepriseRepository;
    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        List<String> errors = EntrepriseValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("l'entreprise est invalide {}", dto);
            throw new InvalidEntityException("l'entreprise est invalide ", ErrorCodes.ENTREPRISE_NOT_VALID,
                    errors);
        }
        return EntrepriseDto.fromEntity(entrepriseRepository
                .save(EntrepriseDto.toEntity(dto)));
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        if (id == null){
            log.error("l'id est invalid");
            return null;
        }
        return entrepriseRepository.findById(id)
                .map(EntrepriseDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Aucun entreprise est trouvé avec l'id = " + id + "dans la base",
                        ErrorCodes.ENTREPRISE_NOT_FOUND
                ));
    }

    @Override
    public EntrepriseDto findByCodeFiscal(String code) {
        if(!StringUtils.hasLength(code)){
            log.error("le code est null");
            return null;
        }
        return entrepriseRepository.findByCodeFiscal(code)
                .map(EntrepriseDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Aucun entreprise est trouvé avec le code = " + code + "dans la base",
                        ErrorCodes.ENTREPRISE_NOT_FOUND
                ));
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("id est null");
            return;
        }
        entrepriseRepository.deleteById(id);
    }
}
