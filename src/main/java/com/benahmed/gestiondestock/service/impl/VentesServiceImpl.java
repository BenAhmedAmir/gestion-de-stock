package com.benahmed.gestiondestock.service.impl;

import com.benahmed.gestiondestock.DTO.VentesDto;
import com.benahmed.gestiondestock.exception.EntityNotFoundException;
import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.InvalidEntityException;
import com.benahmed.gestiondestock.model.Ventes;
import com.benahmed.gestiondestock.repository.VentesRepository;
import com.benahmed.gestiondestock.service.VentesService;
import com.benahmed.gestiondestock.validator.VentesValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VentesServiceImpl implements VentesService {

    private VentesRepository ventesRepository;

    public VentesServiceImpl(VentesRepository ventesRepository) {
        this.ventesRepository = ventesRepository;
    }

    @Override
    public VentesDto save(VentesDto dto) {
        List<String> errors = VentesValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("le vente est invalide {}" , dto);
            throw new  InvalidEntityException("le vente est invalide ", ErrorCodes.VENTE_NOT_VALID,
                    errors);
        }
        return VentesDto.fromEntity(ventesRepository.save(VentesDto.toEntity(dto)));
    }

    @Override
    public VentesDto findById(Integer id) {
        if(id == null){
            log.error("l'id est null");
            return null;
        }
        Optional<Ventes> ventes = ventesRepository.findById(id);
        return Optional.of(VentesDto.fromEntity(ventes.get())).orElseThrow(()->
                new EntityNotFoundException("Aucun vente avec l'id = " +id+ " est trouvée dans la base",
                        ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public VentesDto findByCodeVente(String code) {
        if(!StringUtils.hasLength(code)){
            log.error("le code vente est null");
            return null;
        }
        Optional<Ventes> ventes = ventesRepository.findByCodeVente(code);
        return Optional.of(VentesDto.fromEntity(ventes.get())).orElseThrow(()->
                new EntityNotFoundException("Aucun vente avec le code = " +code+ " est trouvée dans la base",
                        ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesRepository.findAll().stream()
                .map(VentesDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        ventesRepository.deleteById(id);
    }
}
