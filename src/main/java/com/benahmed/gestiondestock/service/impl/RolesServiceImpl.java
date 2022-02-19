package com.benahmed.gestiondestock.service.impl;

import com.benahmed.gestiondestock.DTO.RolesDto;
import com.benahmed.gestiondestock.exception.EntityNotFoundException;
import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.InvalidEntityException;
import com.benahmed.gestiondestock.model.Roles;
import com.benahmed.gestiondestock.repository.RolesRepository;
import com.benahmed.gestiondestock.service.RolesService;
import com.benahmed.gestiondestock.validator.RolesValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RolesServiceImpl implements RolesService {

    private RolesRepository rolesRepository;
    @Autowired
    public RolesServiceImpl(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public RolesDto save(RolesDto dto) {
        List<String> errors = RolesValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("le role est invalide {}", dto);
            throw new  InvalidEntityException("le role est invalid", ErrorCodes.ROLES_NOT_VALID ,
                    errors);
        }
        return RolesDto.fromEntity(rolesRepository
                .save(RolesDto.toEntity(dto)));
    }

    @Override
    public RolesDto findById(Integer id) {
        if(id == null){
            log.error("l'id est null");
            return null;
        }
        Optional<Roles> roles = rolesRepository.findById(id);
        return Optional.of(RolesDto.fromEntity(roles.get())).orElseThrow(()->
                new EntityNotFoundException(
                        "Aucun role avec l'id = "+ id+ " est trouvé dans la base",
                        ErrorCodes.ROLES_NOT_FOUND
                ));
    }

    @Override
    public List<RolesDto> findAll() {
        return rolesRepository.findAll().stream()
                .map(RolesDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        rolesRepository.deleteById(id);
    }
}
