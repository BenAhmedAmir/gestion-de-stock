package com.benahmed.gestiondestock.service.impl;

import com.benahmed.gestiondestock.DTO.FournisseurDto;
import com.benahmed.gestiondestock.DTO.MvtStkDto;
import com.benahmed.gestiondestock.exception.EntityNotFoundException;
import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.InvalidEntityException;
import com.benahmed.gestiondestock.model.MvtStk;
import com.benahmed.gestiondestock.repository.MvtStkRepository;
import com.benahmed.gestiondestock.service.MvtStkService;
import com.benahmed.gestiondestock.validator.MvtStkValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MvtStkServiceImpl implements MvtStkService {
    private MvtStkRepository mvtStkRepository;
    @Autowired
    public MvtStkServiceImpl(MvtStkRepository mvtStkRepository) {
        this.mvtStkRepository = mvtStkRepository;
    }

    @Override
    public MvtStkDto save(MvtStkDto dto) {
        List<String> errors = MvtStkValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("le mvtStk est invalide {}" ,dto);
            throw new InvalidEntityException("le mvtStk est invalide", ErrorCodes.MVT_STK_NOT_VALID,
                    errors);
        }
        return MvtStkDto.fromEntity(mvtStkRepository
                .save(MvtStkDto.toEntity(dto)));
    }

    @Override
    public MvtStkDto findById(Integer id) {
        if (id == null){
            log.error("l'id est null");
            return null;
        }
        return mvtStkRepository.findById(id)
                .map(MvtStkDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Aucun mouvement de stock est trouvé avec le code = " + id + "dans la base",
                        ErrorCodes.MVT_STK_NOT_FOUND
                ));
    }

    @Override
    public List<MvtStkDto> findAll() {
        return mvtStkRepository.findAll().stream()
                .map(MvtStkDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        mvtStkRepository.deleteById(id);
    }
}
